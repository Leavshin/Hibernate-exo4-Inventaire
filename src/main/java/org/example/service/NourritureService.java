package org.example.service;

import org.example.entity.Nourriture;
import org.example.repository.NourritureRepository;

import java.util.List;

public class NourritureService {
    private final NourritureRepository nourritureRepository;

    public NourritureService() {
        this.nourritureRepository = new NourritureRepository();
    }

    public void addNourriture(Nourriture nourriture) {
        nourritureRepository.save(nourriture);
    }

    public void updateNourriture(Nourriture nourriture) {
        nourritureRepository.update(nourriture);
    }

    public void deleteNourriture(Long id) {
        Nourriture nourriture = nourritureRepository.findById(id);
        if (nourriture != null) {
            nourritureRepository.delete(nourriture);
        }
    }

    public Nourriture findNourritureById(Long id) {
        return nourritureRepository.findById(id);
    }

    public List<Nourriture> findAllNourritures() {
        return nourritureRepository.findAll();
    }
}
