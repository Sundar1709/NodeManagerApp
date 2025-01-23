package com.example.myApp.service;


import com.example.myApp.model.VirtualNode;
import com.example.myApp.repository.VirtualNodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

    @Service
    public class VirtualNodeService {
        @Autowired
        private VirtualNodeRepository virtualNodeRepository;

        public VirtualNode createVirtualNode(VirtualNode virtualNode) {
            return virtualNodeRepository.save(virtualNode);
        }

        public VirtualNode findVirtualNodeByName(String nodeName) {
            return virtualNodeRepository.findByNodeName(nodeName);
        }
    }
