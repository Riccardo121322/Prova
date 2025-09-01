package com.project.gestione_eventi;

import jakarta.persistence.Entity;                // ← annotazione per indicare che questa è una "entità"
import jakarta.persistence.Id;                   // ← annotazione per la chiave primaria
import jakarta.persistence.GeneratedValue;       // ← per generare automaticamente l'id
import jakarta.persistence.GenerationType;       // ← specifica il tipo di generazione (IDENTITY usa l'auto-incremento del DB)

@Entity
public class Ospite {

    @Id // ← indica che questo campo è la chiave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ← auto-generazione dell'id dal DB
    private Long id;

    private String nome;   // ← nome dell'ospite
    private String email;  // ← email dell'ospite
    private int eta;       // ← età dell'ospite

    // Costruttore vuoto obbligatorio per JPA
    public Ospite() {
    }

    // GETTER E SETTER (servono per leggere e modificare i campi privati)

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEta() {
        return eta;
    }

    public void setEta(int eta) {
        this.eta = eta;
    }
    
}
