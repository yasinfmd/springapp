package com.myworks.mywork.repository;

import com.myworks.mywork.models.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {
    List<Todo> findByTextContaining(String searchText);
}
