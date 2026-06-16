package org.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Название не может быть пустым")
    @Size(max = 100, message = "Название не должно превышать 100 символов")
    private String name;

    @Size(max = 500, message = "Описание не должно превышать 500 символов")
    private String description;

    @Positive(message = "Цена должна быть больше нуля")
    @NotNull(message = "Укажите цену")
    private BigDecimal price;

    @Min(value = 15, message = "Продолжительность должна быть от 15 минут")
    @NotNull(message = "Укажите продолжительность")
    private Integer duration;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL)
    private java.util.List<Appointment> appointments;

    public Service() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
    public java.util.List<Appointment> getAppointments() { return appointments; }
    public void setAppointments(java.util.List<Appointment> appointments) { this.appointments = appointments; }
}
