package ru.akhmedyanov.homework06.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.akhmedyanov.homework06.model.Note;

import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    /**
     * Поиск заметки по Id
     * @param id
     * @return
     */
    Optional<Note> findById(Long id);
}
