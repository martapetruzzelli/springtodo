package org.generation.italy.springtodo.models.repositories;

import org.generation.italy.springtodo.models.enitites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCategoryRepository extends JpaRepository<Category, Integer> {
}
