package org.example.service;

import org.example.entity.Vente;
import org.example.exception.NotFoundException;
import org.example.repository.VenteRepository;

import java.util.List;

public class VenteService {

    private final VenteRepository venteRepository;

    public VenteService() {
        this.venteRepository = new VenteRepository();
    }

    public void addSale(Vente vente) {
        venteRepository.save(vente);
    }

    public void updateSale(Vente vente) {
        venteRepository.update(vente);
    }

    public void cancelSale(Long venteId) {
        Vente vente = venteRepository.findById(venteId);
        if (vente == null) {
            throw new NotFoundException("Vente non trouvée avec l'ID : " + venteId);
        }

        venteRepository.delete(vente);
    }

    public List<Vente> getSales() {
        return venteRepository.findAll();
    }

    public Vente getSaleById(Long venteId) {
        Vente vente = venteRepository.findById(venteId);
        if (vente == null) {
            throw new NotFoundException("Vente non trouvée avec l'ID : " + venteId);
        }
        return vente;
    }
}
