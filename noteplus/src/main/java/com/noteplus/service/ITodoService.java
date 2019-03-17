package com.noteplus.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.noteplus.entity.TodoItem;
@Component
public interface ITodoService {
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     List<TodoItem> getAllTodoItems();
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
	 TodoItem getTodoItemById(int TodoItemId);
	 @Secured ({"ROLE_ADMIN"})
     boolean addTodoItem(TodoItem TodoItem);
	 @Secured ({"ROLE_ADMIN"})
     void updateTodoItem(TodoItem TodoItem);
	 @Secured ({"ROLE_ADMIN"})
     void deleteTodoItem(int TodoItemId);
}
