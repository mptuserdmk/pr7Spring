package org.example.demo.service;

import org.example.demo.model.Role;
import org.example.demo.model.User;
import org.example.demo.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public Page<User> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Page<User> searchByEmail(String email, Pageable pageable) {
        if (email == null || email.trim().isEmpty()) {
            return repository.findAll(pageable);
        }
        return repository.findByEmailContainingIgnoreCase(email.trim(), pageable);
    }

    /** Регистрация нового пользователя: хэширует пароль, назначает ROLE_USER */
    public User registerUser(String email, String rawPassword) {
        if (repository.existsByEmail(email)) {
            throw new IllegalArgumentException("Пользователь с таким email уже существует");
        }
        User user = new User();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(rawPassword));
        user.setRole(Role.ROLE_USER);
        user.setBonusBalance(BigDecimal.ZERO);
        return repository.save(user);
    }

    /** Сохранение/обновление пользователя через формы CRM.
     *  Если пароль не пустой — хэшируется; если пустой — пароль не меняется. */
    public User save(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        // При создании нового пользователя (id == null) — хэшируем пароль
        if (user.getId() == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // При редактировании — не трогаем пароль если пустой
            User existing = repository.findById(user.getId()).orElse(null);
            if (existing != null && (user.getPassword() == null || user.getPassword().isBlank())) {
                user.setPassword(existing.getPassword());
            } else if (user.getPassword() != null && !user.getPassword().isBlank()
                    && !user.getPassword().startsWith("$2a$")) {
                user.setPassword(passwordEncoder.encode(user.getPassword()));
            }
        }
        return repository.save(user);
    }

    public User findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    /**
     * Смена роли пользователя.
     * Защита от самоблокировки: ADMIN не может лишить себя роли ADMIN.
     *
     * @param targetUserId  ID пользователя, которому меняем роль
     * @param newRole       Новая роль
     * @param currentEmail  Email текущего администратора (из SecurityContext)
     * @throws IllegalStateException если администратор пытается лишить себя прав
     */
    public User changeRole(Long targetUserId, Role newRole, String currentEmail) {
        User target = repository.findById(targetUserId)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        // Защита от самоблокировки
        if (target.getEmail().equals(currentEmail) && newRole != Role.ROLE_ADMIN) {
            throw new IllegalStateException("Администратор не может лишить себя прав ADMIN");
        }

        target.setRole(newRole);
        return repository.save(target);
    }
}
