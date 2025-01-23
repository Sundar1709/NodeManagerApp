package com.example.myApp.model;


import jakarta.persistence.*;

import java.util.Set;

@Entity
    public class ConnectionGroup {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String groupName;

        @OneToMany(mappedBy = "connectionGroup")
        private Set<VirtualNode> virtualNodes;
// getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Set<VirtualNode> getVirtualNodes() {
        return virtualNodes;
    }

    public void setVirtualNodes(Set<VirtualNode> virtualNodes) {
        this.virtualNodes = virtualNodes;
    }

    }
