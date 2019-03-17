package com.noteplus.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="TODO")
public class TodoItem implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="TODO_ID")
    private int todoId;  
	@Column(name="NAME")
    private String name;
	@Column(name="DESCRIPTION")
    private String description;
	@Column(name="DEADLINE")
    private Date deadline;	
	@Column(name="RELATED_TODO_ID")
    private int relatedTodoId;  
	@Column(name="STATUS")	
	private boolean status;
	
	public int getTodoId() {
		return todoId;
	}
	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getDeadline() {
		return deadline;
	}
	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}
	public int getRelatedTodoId() {
		return relatedTodoId;
	}
	public void setRelatedTodoId(int relatedTodoId) {
		this.relatedTodoId = relatedTodoId;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
} 
