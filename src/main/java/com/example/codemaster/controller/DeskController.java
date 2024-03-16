package com.example.codemaster.controller;

import com.example.codemaster.model.Desk;
import com.example.codemaster.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/desk")
public class DeskController {
    @Autowired
    private DeskService deskService;

    @GetMapping("/desks/left")
    public ResponseEntity<List<Desk>> getDesksInRange() {
        List<Desk> desks = deskService.getDesksByLeft();
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }

}
