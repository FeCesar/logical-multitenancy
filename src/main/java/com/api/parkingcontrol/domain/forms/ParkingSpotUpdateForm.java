package com.api.parkingcontrol.domain.forms;

import com.api.parkingcontrol.domain.enums.SpotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParkingSpotUpdateForm implements BaseForm{
    private static final long serialVersionUID = 1L;

    private String parkingSpotNumber;
    private UUID carId;

}
