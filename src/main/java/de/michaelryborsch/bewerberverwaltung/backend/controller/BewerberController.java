package de.michaelryborsch.bewerberverwaltung.backend.controller;

import de.michaelryborsch.bewerberverwaltung.backend.model.Bewerber;
import de.michaelryborsch.bewerberverwaltung.backend.repository.BewerberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(("/bewerber"))
public class BewerberController {

    @Autowired
    BewerberRepo bewerberRepo;

    @GetMapping("")
    public List<Bewerber> index() {
        return bewerberRepo.findAll();
    }

    @PostMapping("")
    public void createBewerber(@RequestBody Bewerber bewerber) {
        bewerberRepo.save(bewerber);
    }

    @PutMapping("/{id}")
    public void updateBewerber(@PathVariable long id, @RequestBody Bewerber bewerberUpdate) {
        Optional<Bewerber> bewerber = bewerberRepo.findById(id);
        if (!bewerber.isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bewerber mit dieser id wurde nicht gefunden");
        }

        Bewerber bewerberInstance = bewerber.get();
        bewerberInstance.setArbeitszeit(bewerberUpdate.getArbeitszeit());
        bewerberInstance.setBewerberStatus(bewerberUpdate.getBewerberStatus());
        bewerberInstance.setEmail(bewerberUpdate.getEmail());
        bewerberInstance.setGehaltswunsch(bewerberUpdate.getGehaltswunsch());
        bewerberInstance.setGewuenschtePosition(bewerberUpdate.getGewuenschtePosition());
        bewerberInstance.setName(bewerberUpdate.getName());
        bewerberInstance.setTelefonNr(bewerberUpdate.getTelefonNr());
        bewerberInstance.setVorname(bewerberUpdate.getVorname());
        bewerberRepo.save(bewerberInstance);
    }

    @DeleteMapping("/{id}")
    public void deleteBewerber(@PathVariable long id) {
        Optional<Bewerber> bewerber = bewerberRepo.findById(id);
        if (bewerber.isPresent()) {
            bewerberRepo.deleteById(id);
        }
    }


}
