package com.noteplus.service;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import com.noteplus.entity.Note;
@Component
public interface INoteService {
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     List<Note> getAllNotes();
	 @Secured ({"ROLE_ADMIN", "ROLE_USER"})
     Note getNoteById(int NoteId);
	 @Secured ({"ROLE_ADMIN"})
     boolean addNote(Note Note);
	 @Secured ({"ROLE_ADMIN"})
     void updateNote(Note Note);
	 @Secured ({"ROLE_ADMIN"})
     void deleteNote(int NoteId);
}
