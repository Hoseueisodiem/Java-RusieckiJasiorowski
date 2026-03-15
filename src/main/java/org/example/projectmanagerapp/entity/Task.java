package org.example.projectmanagerapp.entity;

import jakarta.persistence.*;

@Entity 
@Table(name = "tasks")
public class Task {

    @Id // Klucz główny [cite: 14]
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @Column(nullable = false) 
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING) 
    private TaskType taskType;

    @ManyToOne // Relacja do projektu (Zadanie 2) [cite: 66, 90]
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne // Relacja do użytkownika (Zadanie 2) [cite: 66]
    @JoinColumn(name = "user_id")
    private Users user;

    
}