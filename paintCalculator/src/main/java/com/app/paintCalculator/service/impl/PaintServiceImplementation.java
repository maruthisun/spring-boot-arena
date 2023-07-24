package com.app.paintCalculator.service.impl;

import com.app.paintCalculator.PaintCalculatorApplication;
import com.app.paintCalculator.entities.PaintDetails;
import com.app.paintCalculator.records.RoomDetails;
import com.app.paintCalculator.repositories.PaintRepository;
import com.app.paintCalculator.response.PaintResponse;
import com.app.paintCalculator.service.PaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;

@Component
public class PaintServiceImplementation implements PaintService {

    private static final int NUMBER_OF_WALLS = 4;
    private static final int GALLON_PER_SQ_FEET = 400;
    private static final float PRICE_PER_GALLON = 80.25f;
    private static final String YES = "Y";
    private static final String NO = "N";

    Logger logger = LoggerFactory.getLogger(PaintCalculatorApplication.class);

    @Autowired
    private PaintRepository paintRepository;

    @Override
    public PaintResponse estimatePaint(RoomDetails roomDetails) {

        logger.info("Calculating room details");

        // Room Area
        var totalRoomArea = roomDetails.length() * roomDetails.height() * NUMBER_OF_WALLS;

        // Doors Area
        var totalDoorsArea = 0;
        if (Objects.nonNull(roomDetails.doorDetails())) {

            totalDoorsArea = roomDetails.doorDetails().length() * roomDetails.doorDetails().width()
                    * roomDetails.doorDetails().numberOfDoors();
        }

        //Windows Area
        var totalWindowsArea = 0;
        if (Objects.nonNull(roomDetails.windowDetails())) {

            totalWindowsArea = roomDetails.windowDetails().length() * roomDetails.windowDetails().width()
                    * roomDetails.windowDetails().numberOfWindows();
        }


        String trimRequired = Optional.ofNullable(roomDetails.is_trim_required()).orElse(NO);

        float paintableArea;

        if (trimRequired.equalsIgnoreCase(YES)) {
            paintableArea = (totalRoomArea - totalDoorsArea - totalWindowsArea) + roomDetails.trim_size();
        } else {
            paintableArea = totalRoomArea - totalDoorsArea - totalWindowsArea;
        }

        float paintQuantity = paintableArea / GALLON_PER_SQ_FEET;

        float price = paintQuantity * PRICE_PER_GALLON;

        PaintResponse paintResponse = new PaintResponse(paintQuantity + " Gallon(s)", "$ " + price);

        try {
            persistToDatabase(roomDetails, paintQuantity, price, totalDoorsArea, totalWindowsArea, trimRequired);
        } catch (SQLException sqlException) {
            logger.error("Error while saving to database for User ID {}", roomDetails.userId());
        }

        return paintResponse;
    }

    private void persistToDatabase(RoomDetails roomDetails, float paintQuantity, float price, int totalDoorsArea,
                                   int totalWindowsArea, String trimRequired) throws SQLException {

        PaintDetails paintDetails = new PaintDetails();

        paintDetails.setUser_id(roomDetails.userId());
        paintDetails.setRoom_length(roomDetails.length());
        paintDetails.setRoom_height(roomDetails.height());
        paintDetails.setDoor_size(totalDoorsArea);
        paintDetails.setNumber_of_doors(roomDetails.doorDetails() != null ? roomDetails.doorDetails().numberOfDoors() : 0);
        paintDetails.setWindow_size(totalWindowsArea);
        paintDetails.setNumber_of_windows(roomDetails.doorDetails() != null ? roomDetails.windowDetails().numberOfWindows() : 0);
        paintDetails.setIs_trim_required(trimRequired);
        paintDetails.setTrim_size(Optional.of(roomDetails.trim_size()).orElse(0));
        paintDetails.setPaint_required(paintQuantity);
        paintDetails.setPrice(price);

        paintRepository.save(paintDetails);

        logger.info("Details of User ID {} persisted to database successfully", roomDetails.userId());

    }


}
