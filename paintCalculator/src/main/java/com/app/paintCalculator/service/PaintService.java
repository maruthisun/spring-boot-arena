package com.app.paintCalculator.service;

import com.app.paintCalculator.records.RoomDetails;
import com.app.paintCalculator.response.PaintResponse;
import org.springframework.stereotype.Service;

@Service
public interface PaintService {
    PaintResponse estimatePaint(RoomDetails roomDetails);
}
