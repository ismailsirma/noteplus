package com.noteplus.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.noteplus.entity.Note;
@Component
@Transactional
public class NoteDAO implements INoteDAO {
	@PersistenceContext	
	private EntityManager entityManager;	
	@Override
	public Note getNoteById(int noteId) {
		return entityManager.find(Note.class, noteId);
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<Note> getAllNotes() {
		String hql = "SELECT n FROM Note n ORDER BY n.noteId";
		return (List<Note>) entityManager.createQuery(hql).getResultList();
	}	
	@Override
	public void addNote(Note note) {
		entityManager.persist(note);
	}
	@Override
	public void updateNote(Note note) {
		Note nt = getNoteById(note.getNoteId());
		nt.setTitle(note.getTitle());
		nt.setTodoId(note.getTodoId());
		entityManager.flush();
	}
	@Override
	public void deleteNote(int noteId) {
		entityManager.remove(getNoteById(noteId));
	}
	@Override
	public boolean noteExists(String title) {
		String hql = "SELECT n FROM Note n WHERE n.title = ?";
		int count = entityManager.createQuery(hql).setParameter(1, title).getResultList().size();
		              //.setParameter(2, todoId).getResultList().size();
		return count > 0 ? true : false;
	}
}
