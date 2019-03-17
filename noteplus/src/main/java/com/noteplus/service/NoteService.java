package com.noteplus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.noteplus.dao.INoteDAO;
import com.noteplus.entity.Note;
@Component
public class NoteService implements INoteService {
	@Autowired
	private INoteDAO noteDAO;
	@Override
	public Note getNoteById(int noteId) {
		Note obj = noteDAO.getNoteById(noteId);
		return obj;
	}	
	@Override
	public List<Note> getAllNotes(){
		return noteDAO.getAllNotes();
	}
	@Override
	public synchronized boolean addNote(Note note){
		/*
       if (noteDAO.noteExists(note.getTitle())) {
    	   return false;
       } else {
       */
    	   noteDAO.addNote(note);
    	   return true;
       //}
	}
	@Override
	public void updateNote(Note note) {
		noteDAO.updateNote(note);
	}
	@Override
	public void deleteNote(int noteId) {
		noteDAO.deleteNote(noteId);
	}
}
