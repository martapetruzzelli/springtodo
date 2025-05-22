package org.generation.italy.springtodo.restdtos;

import org.generation.italy.springtodo.models.enitites.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoRestDto {
    private int todoId;
    private String title;
    private String description;
    private LocalDateTime createdAt;
    private LocalDate dueDate;
    private LocalDateTime completedAt;
    private boolean status;
    private int categoryId;

    public TodoRestDto() {
    }

    public TodoRestDto(int todoId, String title, String description, LocalDateTime createdAt, LocalDate dueDate,
                       LocalDateTime completedAt, boolean status, int categoryId) {
        this.todoId = todoId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.completedAt = completedAt;
        this.status = status;
        this.categoryId = categoryId;
    }

    public Todo toTodo(){
        Todo t = new Todo(todoId, title, description, createdAt, dueDate, completedAt, status, null);
        return t;
    }

    public static TodoRestDto toDto(Todo t){
        return new TodoRestDto(t.getTodoId(), t.getTitle(), t.getDescription(), t.getCreatedAt(), t.getDueDate(),
                t.getCompletedAt(), t.getStatus(), t.getCategory().getCategoryId());
    }

    @Override
    public String toString() {
        return "TodoRestDto{" +
                "todoId=" + todoId +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", dueDate=" + dueDate +
                ", completedAt=" + completedAt +
                ", status=" + status +
                ", categoryId=" + categoryId +
                '}';
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

    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
}
