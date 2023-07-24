package com.app.paintCalculator.records;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record RoomDetails(

        @NotNull(message = "UserId can't be null or empty")
        String userId,

        @Min(value = 1, message = "Length must be greater than 0")
        int length,
        @Min(value = 1, message = "Height must be greater than 0")
        int height,

        DoorDetails doorDetails,

        WindowDetails windowDetails,

        String is_trim_required,

        int trim_size) {
}
