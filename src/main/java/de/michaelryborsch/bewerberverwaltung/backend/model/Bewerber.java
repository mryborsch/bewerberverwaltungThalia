package de.michaelryborsch.bewerberverwaltung.backend.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Bewerber {

    //@Embeddable
    public enum Arbeitszeit {
        Vollzeit("Vollzeit"), Teilzeit("Teilzeit"), Azubi("Azubi"), Werkstudent("Werkstudent");

        String name;

        Arbeitszeit(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public enum Bewerberstatus {
        Neu("Neu"), Erstgespräch("Erstgespräch"), Zweitgespräch("Zweitgespräch"),
        Angebot("Angebot-Gemacht"), Eingestellt("Eingestellt");

        String name;

        Bewerberstatus(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;
    @NotNull
    private String vorname;
    @NotNull
    private String email;


    @Enumerated(EnumType.STRING)
    @NotNull
    private Arbeitszeit arbeitszeit;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Bewerberstatus bewerberStatus;
    @NotNull
    private String gewuenschtePosition;
    @NotNull
    private double gehaltswunsch;
    @NotNull
    private int telefonNr;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Arbeitszeit getArbeitszeit() {
        return arbeitszeit;
    }

    public void setArbeitszeit(Arbeitszeit arbeitszeit) {
        this.arbeitszeit = arbeitszeit;
    }

    public Bewerberstatus getBewerberStatus() {
        return bewerberStatus;
    }

    public void setBewerberStatus(Bewerberstatus bewerberStatus) {
        this.bewerberStatus = bewerberStatus;
    }

    public String getGewuenschtePosition() {
        return gewuenschtePosition;
    }

    public void setGewuenschtePosition(String gewuenschtePosition) {
        this.gewuenschtePosition = gewuenschtePosition;
    }

    public double getGehaltswunsch() {
        return gehaltswunsch;
    }

    public void setGehaltswunsch(double gehaltswunsch) {
        this.gehaltswunsch = gehaltswunsch;
    }

    public int getTelefonNr() {
        return telefonNr;
    }

    public void setTelefonNr(int telefonNr) {
        this.telefonNr = telefonNr;
    }
}
