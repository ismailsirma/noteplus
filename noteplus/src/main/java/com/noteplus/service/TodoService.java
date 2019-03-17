package com.noteplus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.noteplus.dao.ITodoItemDAO;
import com.noteplus.entity.TodoItem;
@Component
public class TodoService implements ITodoService {
	@Autowired
	private ITodoItemDAO todoItemDAO;
	@Override
	public TodoItem getTodoItemById(int todoItemId) {
		TodoItem obj = todoItemDAO.getTodoItemById(todoItemId);
		return obj;
	}	
	@Override
	public List<TodoItem> getAllTodoItems(){
		return todoItemDAO.getAllTodoItems();
	}
	@Override
	public synchronized boolean addTodoItem(TodoItem todoItem){
       if (todoItemDAO.todoItemExists(todoItem.getName())) {
    	   return false;
       } else {
    	   todoItemDAO.addTodoItem(todoItem);
    	   return true;
       }
	}
	@Override
	public void updateTodoItem(TodoItem todoItem) {
		todoItemDAO.updateTodoItem(todoItem);
	}
	@Override
	public void deleteTodoItem(int todoItemId) {
		todoItemDAO.deleteTodoItem(todoItemId);
	}
}
