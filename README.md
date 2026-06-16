# Практическая работа №7 (Spring Boot)

Данный проект представляет собой CRM-систему для барбершопа (BarberShop CRM), разработанную в рамках **практической работы №7**.

## О проекте

Приложение реализовано на стеке:
- **Core**: Java 17+, Spring Boot 4
- **Security**: Spring Security 6.x (BCrypt, SecurityFilterChain)
- **ORM / Database**: Spring Data JPA, Hibernate, PostgreSQL
- **UI / Templates**: Thymeleaf + Thymeleaf Security Extras, HTML5, CSS3

## Функционал

### Основной CRUD
- **Клиенты/Пользователи** (`User`)
- **Услуги** (`Service`)
- **Портфолио специалистов** (`Portfolio`)
- **Категории услуг** (`Category`)
- **Записи на стрижку** (`Appointment`)

### Система безопасности (Практика №7)
- **Регистрация** новых пользователей (`/register`)
- **Аутентификация** через форму логина (`/login`)
- **BCrypt** — пароли хранятся только в виде хэша, никогда в открытом виде
- **Три роли**: `ROLE_USER`, `ROLE_MODERATOR`, `ROLE_ADMIN`
- **Разграничение прав доступа** через `SecurityFilterChain`:
  - `ROLE_USER` — просмотр услуг, категорий, портфолио, записей
  - `ROLE_MODERATOR` — всё выше + CRUD операции
  - `ROLE_ADMIN` — всё выше + панель администратора
- **Кастомная страница 403** при попытке доступа без прав
- **Панель администратора** (`/admin/users`) — управление ролями пользователей
- **Защита от самоблокировки**: администратор не может лишить себя роли ADMIN
- **Выход из системы** (`/logout`) с инвалидацией сессии

## Запуск

1. Настройте подключение к СУБД PostgreSQL в `src/main/resources/application.properties`.
2. Запустите проект:
   ```bash
   ./mvnw spring-boot:run
   ```
3. Откройте в браузере: `http://localhost:8080`
4. Зарегистрируйтесь через `/register`, затем вручную назначьте роль `ROLE_ADMIN` в БД:
   ```sql
   UPDATE users SET role = 'ROLE_ADMIN' WHERE email = 'your@email.com';
   ```
