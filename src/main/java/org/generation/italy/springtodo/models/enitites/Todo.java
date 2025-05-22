package org.generation.italy.springtodo.models.enitites;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name="todos")
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="todo_id")
    private int todoId;
    private String title;
    private String description;
    @Column(name ="created_at")
    private LocalDateTime createdAt;
    @Column(name ="due_date")
    private LocalDate dueDate;
    @Column(name ="completed_at")
    private LocalDateTime completedAt;
    private boolean status;
    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    public Todo() {
    }

    public Todo(int todoId, String title, String description, LocalDateTime createdAt, LocalDate dueDate,
                LocalDateTime completedAt, boolean status, Category category) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.completedAt = completedAt;
        this.status = status;
        this.category = category;
    }

    public int getTodoId() {
        return todoId;
    }
    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }
    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }
    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public boolean getStatus() {
        return status;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
}
