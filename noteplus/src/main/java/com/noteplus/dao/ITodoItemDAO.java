package com.noteplus.dao;
import java.util.List;
import org.springframework.stereotype.Component;
import com.noteplus.entity.TodoItem;
@Component
public interface ITodoItemDAO {
    List<TodoItem> getAllTodoItems();
    TodoItem getTodoItemById(int todoItemId);
    void addTodoItem(TodoItem todoItem);
    void updateTodoItem(TodoItem todoItem);
    void deleteTodoItem(int todoItemId);
    boolean todoItemExists(String name);
}
 