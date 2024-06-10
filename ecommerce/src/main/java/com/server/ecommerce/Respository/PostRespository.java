package com.server.ecommerce.Respository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.server.ecommerce.Entity.Posts;
import com.server.ecommerce.Entity.User;
import com.server.ecommerce.Entity.Category;

public interface PostRespository extends JpaRepository<Posts, Long> {
    List<Posts> findByUser(User user);

    @Query("SELECT p FROM Posts p WHERE p.status = 'ok'")
    List<Posts> findByStatusOk();

    @Query("SELECT p FROM Posts p WHERE p.status = 'pending'")
    List<Posts> findByStatusPending();

    @Query("SELECT p FROM Posts p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Posts> findByTitle(@Param("title") String title);

    List<Posts> findByCategory(Category category);

    @Query("SELECT p FROM Posts p WHERE p.price BETWEEN :minPrice AND :maxPrice")
    List<Posts> findByPriceRange(@Param("minPrice") Float minPrice, @Param("maxPrice") Float maxPrice);

    @Query("SELECT COUNT(p) FROM Posts p")
    long countPost();

}
