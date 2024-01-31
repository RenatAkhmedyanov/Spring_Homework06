package ru.akhmedyanov.homework06.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Note {

    /**
     * Id заметки
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Название
     */
    @Column(nullable = false)
    private String title;
    /**
     * Содержимое
     */
    @Column(nullable = false)
    private String description;
    /**
     * Дата создания
     */
    @GeneratedValue(strategy = GenerationType.AUTO)
    private LocalDateTime creationDate;
}
