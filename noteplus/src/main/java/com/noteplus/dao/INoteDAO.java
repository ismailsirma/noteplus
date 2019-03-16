package com.noteplus.dao;
import java.util.List;
import com.noteplus.entity.Note;
public interface INoteDAO {
    List<Note> getAllNotes();
    Note getNoteById(int noteId);
    void addNote(Note note);
    void updateNote(Note note);
    void deleteNote(int noteId);
    boolean noteExists(String title);
}
 