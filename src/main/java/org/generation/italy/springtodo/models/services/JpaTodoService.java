package org.generation.italy.springtodo.models.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceException;
import jakarta.transaction.Transactional;
import org.generation.italy.springtodo.models.enitites.Category;
import org.generation.italy.springtodo.models.enitites.Todo;
import org.generation.italy.springtodo.models.exceptions.DataException;
import org.generation.italy.springtodo.models.repositories.JpaCategoryRepository;
import org.generation.italy.springtodo.models.repositories.JpaTodoRepository;
import org.generation.italy.springtodo.models.repositories.specifications.TodoSpecification;
import org.generation.italy.springtodo.models.searchCriteria.TodoFilterCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JpaTodoService implements TodoService {
    private JpaTodoRepository todoRepo;
    private JpaCategoryRepository categoryRepo;

    @Autowired
    public JpaTodoService(JpaTodoRepository todoRepo, JpaCategoryRepository categoryRepo) {
        this.todoRepo = todoRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Optional<Todo> findTodoById(int id) {
        return todoRepo.findById(id);
    }

    @Override
    public Optional<Category> findCategoryById(int id) {
        return categoryRepo.findById(id);
    }

    @Override
    public List<Todo> findAllTodos() {
        return todoRepo.findAll();
    }

    @Override
    public List<Category> findAllCategories() {
        return categoryRepo.findAll();
    }

    @Override
    public boolean deleteTodoById(int id) throws DataException{
        Optional<Todo> ot = todoRepo.findById(id);
        Todo t = ot.orElseThrow(() -> new DataException(String.format("Il todo con ID %d non esiste", id)));
        todoRepo.deleteById(id);
        return true;
    }

    @Override
    @Transactional
    public Todo saveTodo(Todo t, int categoryId) throws DataException {
        try{
            Optional<Category> oc = categoryRepo.findById(categoryId);
            Category c = oc.orElseThrow(()-> new DataException(String.format("La categoria con ID %d non esiste", categoryId)));
            t.setCategory(c);
            todoRepo.save(t);
            return t;
        } catch (PersistenceException pe) {
            throw new DataException("errore nella creazione di un nuovo todo", pe);
        }
    }

    @Override
    @Transactional
    public Todo updateTodo(Todo t, int categoryId) throws DataException {
        try {
            Optional<Todo> ot = todoRepo.findById(t.getTodoId());
            if(ot.isEmpty()){
                throw new DataException("errore todo non trovato");
            }

            Optional<Category> oc = categoryRepo.findById(categoryId);
            Category c = oc.orElseThrow(()->new DataException(String.format("La categoria con ID %d non esiste", categoryId)));

            t.setCategory(c);

            return todoRepo.save(t);
        } catch (PersistenceException pe) {
            throw new DataException("errore nella modifica di un todo", pe);
        }
    }

    @Override
    public List<Todo> searchTodos(TodoFilterCriteria filters) throws DataException {
        try {
            return todoRepo.findAll(
                    Specification.where(TodoSpecification.hasCategoryId(filters.getCategoryId()))
                            .and(TodoSpecification.isCreatedAfter(filters.getCreatedAt()))
                            .and(TodoSpecification.isDueDateEqualTo(filters.getDueDate()))
                            .and(TodoSpecification.isCompleted(filters.getStatus()))
            );
        } catch (PersistenceException pe) {
            throw new DataException("Errore nella ricerca dei todo", pe);
        }
    }
}
