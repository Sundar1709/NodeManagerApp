package com.example.myApp;

import com.example.myApp.model.ConnectionGroup;
import com.example.myApp.model.VirtualNode;
import com.example.myApp.repository.ConnectionGroupRepository;
import com.example.myApp.repository.VirtualNodeRepository;
import com.example.myApp.service.ConnectionGroupService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class MyAppApplicationTests {
@Mock
private ConnectionGroupRepository connectionGroupRepository;
@Mock
private VirtualNodeRepository virtualNodeRepository;
@InjectMocks
private ConnectionGroupService connectionGroupService;
@BeforeEach
void setup(){
	MockitoAnnotations.openMocks(this);
}
@Test
void createConnectionGroup(){
	ConnectionGroup group=new ConnectionGroup();
	when(connectionGroupRepository.save(group)).thenReturn(group);
	ConnectionGroup createdGroup= connectionGroupService.createConnectionGroup(group);
	assertNotNull(createdGroup);
	verify(connectionGroupRepository,times(1)).save(group);
}
	@Test
	void addNodeToGroup_GroupNotFound(){
	when(connectionGroupRepository.findByGroupName("group1")).thenReturn(null);
	Exception exception =assertThrows(RuntimeException.class,()->{connectionGroupService.addNodeToGroup("group1","node1");});
	assertEquals("Connection group not found", exception.getMessage());
	}
	@Test
	void addNodeToGroup_NodeAlreadyInAnotherGroup(){
	ConnectionGroup group=new ConnectionGroup();
		VirtualNode node =new VirtualNode();
		node.setConnectionGroup(new ConnectionGroup());
		when(connectionGroupRepository.findByGroupName("group1")).thenReturn(group);
		when(virtualNodeRepository.findByNodeName("node1")).thenReturn(node);
		Exception exception=assertThrows(RuntimeException.class,()->{connectionGroupService.addNodeToGroup("group1","node1");});
		assertEquals("Node already belongs to another group", exception.getMessage());
	}
	@Test
	void addNodeToGroup_Success(){
	ConnectionGroup group= new ConnectionGroup();
	VirtualNode node=new VirtualNode();

	when(connectionGroupRepository.findByGroupName("group1")).thenReturn(group);
	when(virtualNodeRepository.findByNodeName("node1")).thenReturn(null);
	when(virtualNodeRepository.save(any(VirtualNode.class))).thenReturn(node);
	ConnectionGroup updatedGroup= connectionGroupService.addNodeToGroup("group1","node1");
	assertNotNull(updatedGroup);
	verify(virtualNodeRepository,times(1)).save(any(VirtualNode.class));

	}

}
