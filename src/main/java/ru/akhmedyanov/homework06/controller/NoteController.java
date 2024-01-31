package ru.akhmedyanov.homework06.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.akhmedyanov.homework06.model.Note;
import ru.akhmedyanov.homework06.repository.NoteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/note")
public class NoteController {

    private final NoteRepository noteRepository;

    /**
     * Добавление заметки
     * @param note
     * @return
     */
    @PostMapping
    public ResponseEntity<Note> addNote(@RequestBody Note note) {
        note.setCreationDate(LocalDateTime.now());
        return new ResponseEntity<>(noteRepository.save(note), HttpStatus.CREATED);
    }

    /**
     * Получить все заметки
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    /**
     * Получить заметку по Id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        Note noteById = noteRepository.findById(id).orElse(null);
        if (noteById == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Note());
        }
        return new ResponseEntity<>(noteById, HttpStatus.OK);

    }

    /**
     * Изменение данных заметки
     * @param id
     * @param note
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note note) {
        Optional<Note> optionalNote = noteRepository.findById(id);
        if (optionalNote.isPresent()) {
            Note updatedNote = optionalNote.get();
            updatedNote.setTitle(note.getTitle());
            updatedNote.setDescription(note.getDescription());
            updatedNote.setCreationDate(LocalDateTime.now());
            noteRepository.save(updatedNote);
            return ResponseEntity.ok(note);
        } else {
            throw new IllegalArgumentException("Note not found with id: " + id);
        }
    }

    /**
     * Удаление заметки
     * @param id
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNoteById(@PathVariable Long id){
        noteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }



}
