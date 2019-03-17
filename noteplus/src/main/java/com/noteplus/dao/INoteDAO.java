package com.noteplus.dao;
import java.util.List;

import org.springframework.stereotype.Component;

import com.noteplus.entity.Note;
@Component
public interface INoteDAO {
    List<Note> getAllNotes();
    Note getNoteById(int noteId);
    void addNote(Note note);
    void updateNote(Note note);
    void deleteNote(int noteId);
    boolean noteExists(String title);
}
 