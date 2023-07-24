package com.app.paintCalculator.service.impl;

import com.app.paintCalculator.entities.PaintDetails;
import com.app.paintCalculator.records.DoorDetails;
import com.app.paintCalculator.records.RoomDetails;
import com.app.paintCalculator.records.WindowDetails;
import com.app.paintCalculator.repositories.PaintRepository;
import com.app.paintCalculator.response.PaintResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

public class PaintServiceImplementationTest {

    @InjectMocks
    PaintServiceImplementation paintServiceImplementation;

    @Mock
    PaintRepository paintRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void estimatePaintQuantityTest() {
        when(paintRepository.save(new PaintDetails())).thenReturn(getPaintDetails());
        PaintResponse paintResponse = paintServiceImplementation.estimatePaint(getRoomDetails());
        assertEquals("2.43 Gallon(s)", paintResponse.estimated_paint_quantity());
    }

    @Test
    public void estimatePaintPriceTest() {
        when(paintRepository.save(new PaintDetails())).thenReturn(getPaintDetails());
        PaintResponse paintResponse = paintServiceImplementation.estimatePaint(getRoomDetails());
        assertEquals("$ 195.0075", paintResponse.estimated_price());
    }

    @Test
    public void persistDBTest() {
        when(paintRepository.save(new PaintDetails())).thenReturn(getPaintDetails());
        assertEquals("UserC", getPaintDetails().getUser_id());
    }

    @Test
    public void dbSaveExceptionTest() {
        assertThrows(Exception.class, () ->
                doThrow(new Exception()).when(paintRepository).save(new PaintDetails()));
    }

    public RoomDetails getRoomDetails() {
        DoorDetails doorDetails = new DoorDetails(7, 3, 2);
        WindowDetails windowDetails = new WindowDetails(5, 3, 2);
        RoomDetails roomDetails = new RoomDetails("SwagA", 15, 17, doorDetails, windowDetails, "Y", 24);

        return roomDetails;

    }

    public PaintDetails getPaintDetails() {
        PaintDetails paintDetails = new PaintDetails();
        paintDetails.setUser_id("UserC");
        paintDetails.setRoom_length(7);
        paintDetails.setRoom_height(8);
        paintDetails.setDoor_size(6);
        paintDetails.setNumber_of_doors(2);
        paintDetails.setWindow_size(3);
        paintDetails.setNumber_of_windows(3);
        paintDetails.setTrim_size(3);
        paintDetails.setPaint_required(4);
        paintDetails.setPrice(30.25f);

        return paintDetails;
    }


}
