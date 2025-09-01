package com.project.gestione_eventi;

import org.springframework.data.jpa.repository.JpaRepository; // ← estensione dell'interfaccia con metodi già pronti

// Questa interfaccia rappresenta il "DAO", cioè il collegamento tra l'applicazione e il database
// Ereditando da JpaRepository avrai automaticamente i metodi:
// save(), findAll(), findById(), deleteById(), existsById(), ecc.

public interface OspiteRepository extends JpaRepository<Ospite, Long> {
    // Ospite = classe che rappresenta la tabella
    // Long = tipo della chiave primaria (il tipo di "id" in Ospite.java)
}