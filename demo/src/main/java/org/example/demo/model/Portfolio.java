package org.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "portfolios")
public class Portfolio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "URL изображения не может быть пустым")
    private String imageUrl;

    @Size(max = 200, message = "Описание не должно превышать 200 символов")
    private String workDescription;

    @ManyToOne
    @JoinColumn(name = "specialist_id")
    private User specialist;

    public Portfolio() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    public String getWorkDescription() { return workDescription; }
    public void setWorkDescription(String workDescription) { this.workDescription = workDescription; }

    public User getSpecialist() { return specialist; }
    public void setSpecialist(User specialist) { this.specialist = specialist; }
}
