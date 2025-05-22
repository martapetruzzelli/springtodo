package org.generation.italy.springtodo.models.services;

import org.generation.italy.springtodo.models.enitites.Category;
import org.generation.italy.springtodo.models.enitites.Todo;
import org.generation.italy.springtodo.models.exceptions.DataException;
import org.generation.italy.springtodo.models.searchCriteria.TodoFilterCriteria;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    Optional<Todo> findTodoById(int id);
    Optional<Category> findCategoryById(int id);
    List<Todo> findAllTodos();
    List<Category> findAllCategories();
    boolean deleteTodoById(int id) throws DataException;
    Todo saveTodo(Todo t, int categoryId) throws DataException;
    Todo updateTodo(Todo t, int categoryId) throws DataException;
    List<Todo> searchTodos(TodoFilterCriteria filters) throws DataException;
}
