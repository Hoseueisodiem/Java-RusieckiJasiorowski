package org.example.projectmanagerapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.edu.pk.nazwisko.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}