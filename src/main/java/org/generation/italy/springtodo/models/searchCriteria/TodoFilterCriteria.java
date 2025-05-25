package org.generation.italy.springtodo.models.searchCriteria;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoFilterCriteria {
    private Integer categoryId;
    private LocalDate createdAt;
    private LocalDate dueDate;
    private Boolean status;

    public TodoFilterCriteria(Integer categoryId, LocalDate createdAt, LocalDate dueDate, Boolean status) {
        this.categoryId = categoryId;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.status = status;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public Boolean getStatus() {
        return status;
    }
}
