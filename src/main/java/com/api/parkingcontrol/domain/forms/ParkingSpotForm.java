package com.api.parkingcontrol.domain.forms;

import com.api.parkingcontrol.domain.enums.SpotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotForm implements BaseForm{
    private static final long serialVersionUID = 1L;

    private String parkingSpotNumber;
    private SpotStatus status = SpotStatus.NOT_BUSY;

}
