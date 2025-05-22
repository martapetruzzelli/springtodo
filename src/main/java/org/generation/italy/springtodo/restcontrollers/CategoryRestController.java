package org.generation.italy.springtodo.restcontrollers;

import org.generation.italy.springtodo.models.enitites.Category;
import org.generation.italy.springtodo.models.services.TodoService;
import org.generation.italy.springtodo.restdtos.CategoryRestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/categories")
public class CategoryRestController {
    private TodoService todoService;

    @Autowired
    public CategoryRestController(TodoService todoService){
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<?> findCategories(){
        List<CategoryRestDto> categories = todoService.findAllCategories().stream().map(CategoryRestDto::toDto).toList();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategoryById(@PathVariable Integer id){
        Optional<Category> oc = todoService.findCategoryById(id);
        if(oc.isPresent()){
            var categoryDto = CategoryRestDto.toDto(oc.get());
            return ResponseEntity.ok(categoryDto);
        }
        return ResponseEntity.notFound().build();
    }
}
