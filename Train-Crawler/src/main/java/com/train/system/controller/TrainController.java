package com.train.system.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.train.system.service.TrainService;

@RestController
public class TrainController {

    @Autowired
    private TrainService trainService;

    @GetMapping("/try")
    public ResponseEntity<?> getData(@RequestParam String apiKey, @RequestParam String trainId) throws IOException {
        return new ResponseEntity<List<?>>(trainService.getTrains(apiKey, trainId), HttpStatus.OK);
    }

}
