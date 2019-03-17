package com.noteplus.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.noteplus.entity.TodoItem;
@Component
@Transactional
public class TodoItemDAO implements ITodoItemDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public TodoItem getTodoItemById(int todoItemId) {
		return entityManager.find(TodoItem.class, todoItemId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<TodoItem> getAllTodoItems() {
		String hql = "SELECT t FROM TodoItem t ORDER BY t.todoItemId";
		return (List<TodoItem>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addTodoItem(TodoItem todoItem) {
		entityManager.persist(todoItem);
	}
	@Override
	public void updateTodoItem(TodoItem todoItem) {
		TodoItem todo = getTodoItemById(todoItem.getTodoId());
		todo.setName(todo.getName());
		todo.setTodoId(todo.getTodoId());
		entityManager.flush();
	}
	@Override
	public void deleteTodoItem(int todoItemId) {
		entityManager.remove(getTodoItemById(todoItemId));
	}
	@Override
	public boolean todoItemExists(String name) {
		String hql = "SELECT t FROM TodoItem t WHERE t.name = ?";
		int count = entityManager.createQuery(hql).setParameter(1, name).getResultList().size();
		              //.setParameter(2, todoId).getResultList().size();
		return count > 0 ? true : false;
	}
}
