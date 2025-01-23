package com.example.myApp.repository;

import com.example.myApp.model.ConnectionGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConnectionGroupRepository extends JpaRepository<ConnectionGroup, Long> {
    ConnectionGroup findByGroupName(String groupName);
}
