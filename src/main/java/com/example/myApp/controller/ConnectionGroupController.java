package com.example.myApp.controller;

import com.example.myApp.model.ConnectionGroup;
import com.example.myApp.service.ConnectionGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

    @Controller
    public class ConnectionGroupController {

        @Autowired
        private ConnectionGroupService connectionGroupService;

        @GetMapping("/")
        public String index() {
            return "index";
        }

        @GetMapping("/create-group")
        public String showCreateGroupForm(Model model) {
            model.addAttribute("connectionGroup", new ConnectionGroup());
            return "create-group";
        }

        @PostMapping("/create-group")
        public String createGroup(@ModelAttribute ConnectionGroup connectionGroup) {
            connectionGroupService.createConnectionGroup(connectionGroup);
            return "redirect:/";
        }

        @GetMapping("/add-node")
        public String showAddNodeForm(Model model) {
            model.addAttribute("groupName", "");
            model.addAttribute("nodeName", "");
            return "add-node";
        }

        @PostMapping("/add-node")
        public String addNode(@RequestParam String groupName, @RequestParam String nodeName) {
            try {
                connectionGroupService.addNodeToGroup(groupName, nodeName);
                return "redirect:/";
            } catch (Exception e) {
                return "error";
            }
        }

        @GetMapping("/find-group")
        public String showFindGroupForm(Model model) {
            model.addAttribute("nodeName", "");
            return "find-group";
        }

        @PostMapping("/find-group")
        public String findGroup(@RequestParam String nodeName, Model model) {
            try {
                ConnectionGroup group = connectionGroupService.findConnectionGroupByNodeName(nodeName);
                model.addAttribute("connectionGroup", group);
                return "group-details";
            } catch (Exception e) {
                return "error";
            }
        }
    }
