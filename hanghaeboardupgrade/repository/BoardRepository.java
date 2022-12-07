package com.example.hanghaeboardupgrade.repository;

import com.example.hanghaeboardupgrade.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByOrderByCreatedAtDesc();
    Optional<Board> findByUsername(String username);
}
