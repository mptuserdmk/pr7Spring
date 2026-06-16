package org.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Некорректный email")
    @NotBlank(message = "Email не может быть пустым")
    private String email;

    @Size(min = 8, message = "Пароль должен быть не менее 8 символов")
    @NotBlank(message = "Пароль не может быть пустым")
    private String password;

    private String role;

    @PositiveOrZero(message = "Баланс не может быть отрицательным")
    private BigDecimal bonusBalance;

    @OneToMany(mappedBy = "specialist", cascade = CascadeType.ALL)
    private java.util.List<Portfolio> portfolios;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private java.util.List<Appointment> clientAppointments;

    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL)
    private java.util.List<Appointment> masterAppointments;

    public User() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
    public BigDecimal getBonusBalance() { return bonusBalance; }
    public void setBonusBalance(BigDecimal bonusBalance) { this.bonusBalance = bonusBalance; }

    public java.util.List<Portfolio> getPortfolios() { return portfolios; }
    public void setPortfolios(java.util.List<Portfolio> portfolios) { this.portfolios = portfolios; }
    public java.util.List<Appointment> getClientAppointments() { return clientAppointments; }
    public void setClientAppointments(java.util.List<Appointment> clientAppointments) { this.clientAppointments = clientAppointments; }
    public java.util.List<Appointment> getMasterAppointments() { return masterAppointments; }
    public void setMasterAppointments(java.util.List<Appointment> masterAppointments) { this.masterAppointments = masterAppointments; }
}
