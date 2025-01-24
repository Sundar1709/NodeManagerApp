package com.example.myApp.repository;

import com.example.myApp.model.ConnectionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConnectionGroupRepository extends JpaRepository<ConnectionGroup, Long> {
    Optional<ConnectionGroup> findByGroupName(String groupName);
}
