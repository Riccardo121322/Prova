package com.project.gestione_eventi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // Indica che questa classe gestisce richieste HTTP REST
@RequestMapping("/ospiti") // Tutti gli endpoint inizieranno con /ospiti (es: /ospiti/aggiungi)
public class OspiteController {

    @Autowired // Dice a Spring di creare automaticamente un'istanza di OspiteRepository
    private OspiteRepository ospiteRepository;

    // 🟡 Aggiunge un nuovo ospite (es. POST /ospiti/aggiungi)
    @PostMapping("/aggiungi")
    public Ospite aggiungiOspite(@RequestBody Ospite ospite) {
        return ospiteRepository.save(ospite); // Salva l'oggetto nel database
    }

    // 🟢 Restituisce tutti gli ospiti (es. GET /ospiti/lista)
    @GetMapping("/lista")
    public List<Ospite> getListaOspiti() {
        return ospiteRepository.findAll(); // Restituisce tutti gli ospiti presenti
    }

    // 🔵 Restituisce un singolo ospite per ID (es. GET /ospiti/dettaglio?id=1)
    @GetMapping("/dettaglio")
    public Ospite getOspite(@RequestParam Long id) {
        return ospiteRepository.findById(id).orElse(null); // Cerca l'ospite con quell'ID
    }

    // 🟠 Modifica un ospite esistente (es. PUT /ospiti/modifica?id=1)
    @PutMapping("/modifica")
    public Ospite modificaOspite(@RequestParam Long id, @RequestBody Ospite datiModificati) {
        Optional<Ospite> ospiteOptional = ospiteRepository.findById(id);

        if (ospiteOptional.isPresent()) {
            Ospite ospite = ospiteOptional.get();
            ospite.setNome(datiModificati.getNome());
            ospite.setEmail(datiModificati.getEmail());
            ospite.setEta(datiModificati.getEta());

            return ospiteRepository.save(ospite);
        } else {
            return null; // Oppure potremmo lanciare un'eccezione o restituire una risposta con errore
        }
    }

    // 🔴 Elimina un ospite (es. DELETE /ospiti/elimina?id=1)
    @DeleteMapping("/elimina")
    public String eliminaOspite(@RequestParam Long id) {
        if (ospiteRepository.existsById(id)) {
            ospiteRepository.deleteById(id);
            return "Ospite eliminato con successo";
        } else {
            return "Ospite non trovato";
        }
    }
}