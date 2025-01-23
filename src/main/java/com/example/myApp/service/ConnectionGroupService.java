package com.example.myApp.service;

import com.example.myApp.model.ConnectionGroup;
import com.example.myApp.model.VirtualNode;
import com.example.myApp.repository.ConnectionGroupRepository;
import com.example.myApp.repository.VirtualNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class ConnectionGroupService {
        @Autowired
        private ConnectionGroupRepository connectionGroupRepository;

        @Autowired
        private VirtualNodeRepository virtualNodeRepository;

        public ConnectionGroup createConnectionGroup(ConnectionGroup connectionGroup) {
            return connectionGroupRepository.save(connectionGroup);
        }

        public ConnectionGroup addNodeToGroup(String groupName, String nodeName) {
            ConnectionGroup group = connectionGroupRepository.findByGroupName(groupName);
            VirtualNode node = virtualNodeRepository.findByNodeName(nodeName);

            if (group == null) {
                throw new RuntimeException("Connection group not found");
            }

            if (node != null && node.getConnectionGroup() != null) {
                throw new RuntimeException("Node already belongs to another group");
            }

            if (node == null) {
                node = new VirtualNode();
                node.setNodeName(nodeName);
            }

            node.setConnectionGroup(group);
            virtualNodeRepository.save(node);

            return group;
        }

        public ConnectionGroup findConnectionGroupByNodeName(String nodeName) {
            VirtualNode node = virtualNodeRepository.findByNodeName(nodeName);
            if (node == null) {
                throw new RuntimeException("Node not found");
            }
            return node.getConnectionGroup();
        }
    }
