package com.api.parkingcontrol.domain.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarForm implements BaseForm{
    private static final long serialVersionUID = 1L;

    private String licensePlateCar;
    private String modelCar;
    private String color;
    private UUID residentId;

}
