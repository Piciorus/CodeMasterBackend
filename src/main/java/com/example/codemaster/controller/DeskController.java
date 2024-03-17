package com.example.codemaster.controller;

import com.example.codemaster.model.DTO.AIDeskRequestDTO;
import com.example.codemaster.model.Desk;
import com.example.codemaster.service.BookingDeskService;
import com.example.codemaster.service.DeskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/desk")
public class DeskController {
    @Autowired
    private DeskService deskService;

    @Autowired
    private BookingDeskService bookingDeskService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/desks/left")
    public ResponseEntity<List<Desk>> getDesksInRange() {
        List<Desk> desks = deskService.getDesksByLeft();
        return new ResponseEntity<>(desks, HttpStatus.OK);
    }

    @GetMapping("/desks/roomCapacity/{id}")
    public ResponseEntity<Long> getRoomCapacity(@PathVariable("id") String id) {
        Long cap = deskService.getRoomCapacity(id);
        return new ResponseEntity<Long>(cap, HttpStatus.OK);
    }

    @PostMapping(value = "/desk-prediction")
    public String getProductList(@RequestBody AIDeskRequestDTO aiDeskRequestDTO) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<AIDeskRequestDTO> entity = new HttpEntity<>(aiDeskRequestDTO, headers);

        return restTemplate.exchange("http://localhost:5000/desk-prediction", HttpMethod.POST, entity, String.class).getBody();
    }

}
