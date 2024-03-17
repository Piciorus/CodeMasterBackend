package com.example.codemaster.service;

import com.example.codemaster.model.Desk;
import com.example.codemaster.repository.DeskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeskService {
    @Autowired
    private DeskRepository deskRepository;

    public List<Desk> getDesksByLeft() {
        return deskRepository.findLeftDesks();
    }

    public Long getRoomCapacity(String id) {
        return this.deskRepository.getRoomCapacity(id);
    }
}
