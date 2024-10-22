package com.fullcycle.admin.catalago.infrastructure.category.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryJpaEntity, String> {
}