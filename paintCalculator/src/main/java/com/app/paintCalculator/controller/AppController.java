package com.app.paintCalculator.controller;

import com.app.paintCalculator.records.RoomDetails;
import com.app.paintCalculator.response.PaintResponse;
import com.app.paintCalculator.service.PaintService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @Autowired
    private PaintService paintService;

    @Operation(summary = "Greet User", description = "Just welcoming the User while loading the app")
    @GetMapping
    public String greeting(){
        return "Hello Painter";
    }

    @Operation(summary = "Estimate Paint Quantity and Cost", description = "POST API to estimate the amount of paint required for a room and its associated cost")
    @PostMapping("/paintEstimate")
    public ResponseEntity<PaintResponse> estimatePaint(@Valid @RequestBody RoomDetails roomDetails){
        return new ResponseEntity<>(paintService.estimatePaint(roomDetails), HttpStatus.OK);
    }
}
