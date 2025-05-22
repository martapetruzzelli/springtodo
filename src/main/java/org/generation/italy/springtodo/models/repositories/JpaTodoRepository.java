package org.generation.italy.springtodo.models.repositories;

import org.generation.italy.springtodo.models.enitites.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface JpaTodoRepository extends JpaRepository<Todo, Integer>, JpaSpecificationExecutor<Todo> {
}
