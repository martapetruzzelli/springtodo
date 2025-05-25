package org.generation.italy.springtodo.models.repositories.specifications;

import jakarta.persistence.criteria.Join;
import org.generation.italy.springtodo.models.enitites.Category;
import org.generation.italy.springtodo.models.enitites.Todo;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TodoSpecification {
    public static Specification<Todo> hasCategoryId(Integer categoryId){
        return(root, query, builder) -> {
            if(categoryId == null) {
                return builder.conjunction();
            }
            Join<Todo, Category> categoryJoin = root.join("category");
            return builder.equal(categoryJoin.get("categoryId"), categoryId);
        };
    }

    public static Specification<Todo> isCreatedAfter(LocalDate createdAt){
        return(root, query, builder) -> {
            if(createdAt == null) {
                return builder.conjunction();
            }
            return builder.greaterThanOrEqualTo(root.get("createdAt"), createdAt);
        };
    }

    public static Specification<Todo> isDueDateEqualTo(LocalDate dueDate){
        return(root, query, builder) -> {
            if(dueDate == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("dueDate"), dueDate);
        };
    }
    public static Specification<Todo> isCompleted(Boolean status){
        return (root, query, builder) -> {
            if(status == null) {
                return builder.conjunction();
            }
            return builder.equal(root.get("status"), status);
        };
    }
}
