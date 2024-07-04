package org.example.service;

import org.example.entity.Mode;
import org.example.repository.ModeRepository;

import java.util.List;

public class ModeService {
    private final ModeRepository modeRepository;

    public ModeService() {
        this.modeRepository = new ModeRepository();
    }

    public void addMode(Mode mode) {
        modeRepository.save(mode);
    }

    public void updateMode(Mode mode) {
        modeRepository.update(mode);
    }

    public void deleteMode(Long id) {
        Mode mode = modeRepository.findById(id);
        if (mode != null) {
            modeRepository.delete(mode);
        }
    }

    public Mode findModeById(Long id) {
        return modeRepository.findById(id);
    }

    public List<Mode> findAllModes() {
        return modeRepository.findAll();
    }
}
