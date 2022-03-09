package de.michaelryborsch.bewerberverwaltung.backend.repository;

import de.michaelryborsch.bewerberverwaltung.backend.model.Bewerber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BewerberRepo extends JpaRepository<Bewerber, Long> {
}
