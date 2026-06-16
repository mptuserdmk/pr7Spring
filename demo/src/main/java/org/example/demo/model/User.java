package org.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email не может быть пустым")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.ROLE_USER;

    @PositiveOrZero(message = "Баланс не может быть отрицательным")
    private BigDecimal bonusBalance = BigDecimal.ZERO;

    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL)
    private List<Portfolio> portfolios;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<Appointment> clientAppointments;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private List<Appointment> masterAppointments;

    public User() {}

    // -------- UserDetails implementation --------
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired()  { return true; }
    @Override
    public boolean isAccountNonLocked()   { return true; }
    @Override
    public boolean isCredentialsNonExpired() { return true; }
    @Override
    public boolean isEnabled()            { return true; }

    // -------- Getters / Setters --------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public BigDecimal getBonusBalance() { return bonusBalance; }
    public void setBonusBalance(BigDecimal bonusBalance) { this.bonusBalance = bonusBalance; }

    public List<Portfolio> getPortfolios() { return portfolios; }
    public void setPortfolios(List<Portfolio> portfolios) { this.portfolios = portfolios; }

    public List<Appointment> getClientAppointments() { return clientAppointments; }
    public void setClientAppointments(List<Appointment> clientAppointments) { this.clientAppointments = clientAppointments; }

    public List<Appointment> getMasterAppointments() { return masterAppointments; }
    public void setMasterAppointments(List<Appointment> masterAppointments) { this.masterAppointments = masterAppointments; }
}
