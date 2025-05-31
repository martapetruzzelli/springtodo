package org.generation.italy.springtodo.restcontrollers;

import org.generation.italy.springtodo.models.enitites.Todo;
import org.generation.italy.springtodo.models.exceptions.DataException;
import org.generation.italy.springtodo.models.searchCriteria.TodoFilterCriteria;
import org.generation.italy.springtodo.models.services.TodoService;
import org.generation.italy.springtodo.restdtos.TodoRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/todos")
public class TodoRestController {
    private TodoService todoService;

    @Autowired
    public TodoRestController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<?> findTodos(@RequestParam(required = false) Integer category,
                                       @RequestParam(required = false)@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate createdAt,
                                       @RequestParam(required = false) LocalDate dueDate,
                                       @RequestParam(required = false) Boolean status) throws DataException{

        TodoFilterCriteria filters = new TodoFilterCriteria(category, createdAt, dueDate, status);
        List<TodoRestDto> todos = todoService.searchTodos(filters).stream().map(TodoRestDto::toDto).toList();
        return ResponseEntity.ok(todos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTodoById(@PathVariable Integer id){
        Optional<Todo> ot = todoService.findTodoById(id);
        if(ot.isPresent()){
            var todoDto = TodoRestDto.toDto(ot.get());
            return ResponseEntity.ok(todoDto);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTodoById(@PathVariable Integer id) throws DataException {
        boolean deleted = todoService.deleteTodoById(id);
        if(deleted){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<TodoRestDto> createTodo(@RequestBody TodoRestDto dto) throws DataException {
        Todo t = dto.toTodo();
        t.setCreatedAt(LocalDateTime.now());
        todoService.saveTodo(t, dto.getCategoryId());
        TodoRestDto saved = TodoRestDto.toDto(t);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getTodoId())
                .toUri();
        //  return ResponseEntity.created(URI.create("/api/todos/" + saved.getTodoId())).body(saved);
        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable Integer id, @RequestBody TodoRestDto dto) throws DataException {
        if(id != dto.getTodoId()){
            return ResponseEntity.badRequest().body("L'id del path non corrisponde all'id del dto");
        }
        Optional<Todo> ot = todoService.findTodoById(id);
        if(ot.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Todo t = dto.toTodo();
        Todo updated = todoService.updateTodo(t, dto.getCategoryId());
        return ResponseEntity.ok(TodoRestDto.toDto(updated));
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> toggleComplete(@PathVariable Integer id) throws DataException {
        Optional<Todo> ot = todoService.findTodoById(id);
        if(ot.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Todo t = ot.get();
        t.setStatus(!t.getStatus());
        if(t.getCompletedAt() == null){
            t.setCompletedAt(LocalDateTime.now());
        } else {
            t.setCompletedAt(null);
        }
        Todo updated = todoService.updateTodo(t, t.getCategory().getCategoryId());
        return ResponseEntity.ok(TodoRestDto.toDto(updated));
    }
}
