package org.example.service;

import org.example.entity.Electronique;
import org.example.repository.ElectroniqueRepository;

import java.util.List;

public class ElectroniqueService {
    private final ElectroniqueRepository electroniqueRepository;

    public ElectroniqueService() {
        this.electroniqueRepository = new ElectroniqueRepository();
    }

    public void addElectronique(Electronique electronique) {
        electroniqueRepository.save(electronique);
    }

    public void updateElectronique(Electronique electronique) {
        electroniqueRepository.update(electronique);
    }

    public void deleteElectronique(Long id) {
        Electronique electronique = electroniqueRepository.findById(id);
        if (electronique != null) {
            electroniqueRepository.delete(electronique);
        }
    }

    public Electronique findElectroniqueById(Long id) {
        return electroniqueRepository.findById(id);
    }

    public List<Electronique> findAllElectroniques() {
        return electroniqueRepository.findAll();
    }
}
