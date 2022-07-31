package com.api.parkingcontrol.domain.dtos;

import com.api.parkingcontrol.domain.enums.SpotStatus;
import com.api.parkingcontrol.domain.models.ParkingSpotModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotDTO implements BaseDTO {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String parkingSpotNumber;
    private SpotStatus status;

    private CarDTO car;
    private ResidentDTO resident;

    public ParkingSpotModel toModel(){
        return ParkingSpotModel.builder()
                .id(this.getId())
                .parkingSpotNumber(this.getParkingSpotNumber())
                .status(this.getStatus())
                .car(this.getCar() != null ? this.getCar().toModel() : null)
                .resident(this.getResident() != null ? this.getResident().toModel(): null)
                .build();
    }
}
