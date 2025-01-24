package com.example.myApp.repository;
import com.example.myApp.model.VirtualNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VirtualNodeRepository extends JpaRepository<VirtualNode, Long> {

    VirtualNode findByNodeName(String nodeName);
}
