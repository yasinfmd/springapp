package com.myworks.mywork.repository;

import com.myworks.mywork.models.Todo;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Repository
public interface TodoRepository extends JpaRepository<Todo, UUID> {
    List<Todo> findByTextContaining(String searchText);

    Page<Todo> findAll(Specification<Todo> query, Pageable pageable);

    /*
    @Query("SELECT t FROM todo t WHERE t.title = :title")
    Page<Todo> findByTitle(String title, Pageable pageable);

     */


  //  List<Todo>  findByUserId(UUID userId);

}
