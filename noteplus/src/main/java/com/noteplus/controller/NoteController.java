package com.noteplus.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.noteplus.entity.Note;
import com.noteplus.service.INoteService;

@CrossOrigin(origins = "*",maxAge=2000,allowedHeaders="*",allowCredentials= "false")
@RestController
@RequestMapping("user")
public class NoteController {
	@Autowired
	private INoteService NoteService;
	 
	@GetMapping("/note/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable("id") Integer id) {
		Note Note = NoteService.getNoteById(id);
		return new ResponseEntity<Note>(Note, HttpStatus.OK);
	}
	@GetMapping("/notes")
	public ResponseEntity<List<Note>> getAllNotes() {
		List<Note> list = NoteService.getAllNotes();
		return new ResponseEntity<List<Note>>(list, HttpStatus.OK);
	}
	@PostMapping("/note")
	public ResponseEntity<Void> addNote(@RequestBody Note Note, UriComponentsBuilder builder) {
        boolean flag = NoteService.addNote(Note);
        if (flag == false) {
        	return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(builder.path("/note/{id}").buildAndExpand(Note.getNoteId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	@PutMapping("/note")
	public ResponseEntity<Note> updateNote(@RequestBody Note Note) {
		NoteService.updateNote(Note);
		return new ResponseEntity<Note>(Note, HttpStatus.OK);
	}
	@DeleteMapping("/note/{id}")
	public ResponseEntity<Void> deleteNote(@PathVariable("id") Integer id) {
		NoteService.deleteNote(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}	
} 