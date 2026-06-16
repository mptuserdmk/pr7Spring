package org.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Future(message = "Можно записаться только на будущее время")
    @NotNull(message = "Укажите дату и время")
    private LocalDateTime dateTime;

    @NotBlank(message = "Статус не может быть пустым")
    private String status;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "master_id")
    private User master;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    public Appointment() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDateTime getDateTime() { return dateTime; }
    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public User getClient() { return client; }
    public void setClient(User client) { this.client = client; }
    public User getMaster() { return master; }
    public void setMaster(User master) { this.master = master; }
    public Service getService() { return service; }
    public void setService(Service service) { this.service = service; }
}
