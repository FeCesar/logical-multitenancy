package com.api.parkingcontrol.domain.dtos;

import com.api.parkingcontrol.domain.models.CarModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDTO implements BaseDTO {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String licensePlateCar;
    private String modelCar;
    private String color;

    private ResidentDTO resident;

    public CarModel toModel(){
        return CarModel.builder()
                .id(this.getId())
                .licensePlateCar(this.getLicensePlateCar())
                .modelCar(this.getModelCar())
                .color(this.getColor())
                .resident(this.resident.toModel())
                .build();
    }

}
