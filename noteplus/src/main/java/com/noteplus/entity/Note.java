package com.noteplus.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="NOTE")
public class Note implements Serializable { 
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="NOTE_ID")
    private int noteId;  
	@Column(name="TITLE")
    private String title;
	@Column(name="TODO_ID")	
	private int todoId;
	public int getNoteId() {
		return noteId;
	}
	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getTodoId() {
		return todoId;
	}
	public void setTodoId(int todoId) {
		this.todoId = todoId;
	}
} 