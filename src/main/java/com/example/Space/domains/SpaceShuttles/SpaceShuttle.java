package com.example.Space.domains.SpaceShuttles;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "SpaceShuttles")
public class SpaceShuttle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;

    private String name;

    private String description;

    private BigDecimal amount;

    private LocalDate release;

    public SpaceShuttle(Boolean active, String name, String description, BigDecimal amount, LocalDate release) {
        this.active = active;
        this.name = name;
        this.description = description;
        this.amount = amount;
        this.release = release;
    }

    public SpaceShuttle() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getRelease() {
        return release;
    }

    public void setRelease(LocalDate release) {
        this.release = release;
    }

    @Override
    public String toString() {
        return "SpaceShuttle{" +
                "id=" + id +
                ", active=" + active +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                ", release=" + release +
                '}';
    }
}
