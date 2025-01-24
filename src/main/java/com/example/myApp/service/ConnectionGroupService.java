package com.example.myApp.service;

import com.example.myApp.model.ConnectionGroup;
import com.example.myApp.model.VirtualNode;
import com.example.myApp.repository.ConnectionGroupRepository;
import com.example.myApp.repository.VirtualNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

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
            Optional<ConnectionGroup> group = connectionGroupRepository.findByGroupName(groupName);
            if(group.isEmpty()) throw new RuntimeException("Connection group not found");
            VirtualNode node = virtualNodeRepository.findByNodeName(nodeName);
            if(node!=null && node.getConnectionGroup()!=null) throw new RuntimeException("Node already belongs to another group");
            if(node==null){
                node=new VirtualNode();
                node.setNodeName(nodeName);
            }
            node.setConnectionGroup(group.get());
            virtualNodeRepository.save(node);
            return group.get();
        }

        public ConnectionGroup findConnectionGroupByNodeName(String nodeName) {
            VirtualNode node = virtualNodeRepository.findByNodeName(nodeName);
            if (node == null) {
                throw new RuntimeException("Node not found");
            }
            return node.getConnectionGroup();
        }
    }
