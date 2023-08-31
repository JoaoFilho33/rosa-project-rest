package com.rosa.rosaRest.models;

import com.rosa.rosaRest.Enum.StatusEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TB_PRODUCTS")
public class ProductModel {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;
    private StatusEnum status;
    private String destination;
    private Integer profitRate;
    private Integer term;
    private Double administrationRate;
    private LocalDate dueDate;
    private Boolean dailyLiquidity;

    public UUID getId() {

        return id;
    }

    public void setId(UUID id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public StatusEnum getStatus() {

        return status;
    }

    public void setStatus(StatusEnum status) {

        this.status = status;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Integer getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(Integer profitRate) {
        this.profitRate = profitRate;
    }

    public Integer getTerm() {
        return term;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public Double getAdministrationRate() {
        return administrationRate;
    }

    public void setAdministrationRate(Double administrationRate) {
        this.administrationRate = administrationRate;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public Boolean getDailyLiquidity() {
        return dailyLiquidity;
    }

    public void setDailyLiquidity(Boolean dailyLiquidity) {
        this.dailyLiquidity = dailyLiquidity;
    }
}