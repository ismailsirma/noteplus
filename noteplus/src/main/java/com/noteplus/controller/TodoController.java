package com.noteplus.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.noteplus.entity.TodoItem;
import com.noteplus.service.ITodoService;

@CrossOrigin(origins = "*",maxAge=2000,allowedHeaders="*",allowCredentials= "false")
@RestController
@RequestMapping("user")
public class TodoController {
	@Autowired
	private ITodoService TodoService;
	 
	@GetMapping("/todo/{id}")
	public ResponseEntity<TodoItem> getTodoItemById(@PathVariable("id") Integer id) {
		TodoItem TodoItem = TodoService.getTodoItemById(id);
		return new ResponseEntity<TodoItem>(TodoItem, HttpStatus.OK);
	}
	@GetMapping("/todos")
	public ResponseEntity<List<TodoItem>> getAllTodoItems() {
		List<TodoItem> list = TodoService.getAllTodoItems();
		return new ResponseEntity<List<TodoItem>>(list, HttpStatus.OK);
	}
	@PostMapping("/todo")
	public ResponseEntity<Void> addTodoItem(@RequestBody TodoItem TodoItem, UriComponentsBuilder builder) {
        boolean flag = TodoService.addTodoItem(TodoItem);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/todo/{id}").buildAndExpand(TodoItem.getTodoId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("/todo")
	public ResponseEntity<TodoItem> updateTodoItem(@RequestBody TodoItem TodoItem) {
		TodoService.updateTodoItem(TodoItem);
		return new ResponseEntity<TodoItem>(TodoItem, HttpStatus.OK);
	}
	@DeleteMapping("/todo/{id}")
	public ResponseEntity<Void> deleteTodoItem(@PathVariable("id") Integer id) {
		TodoService.deleteTodoItem(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 