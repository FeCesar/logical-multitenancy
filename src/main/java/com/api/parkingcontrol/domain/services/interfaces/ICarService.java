package com.api.parkingcontrol.domain.services.interfaces;

import com.api.parkingcontrol.domain.dtos.CarDTO;
import com.api.parkingcontrol.domain.models.CarModel;

import java.util.List;
import java.util.UUID;

public interface ICarService extends DefaultService<CarModel, CarDTO>{

    @Override
    CarDTO save(CarModel model);

    @Override
    List<CarDTO> findAll(UUID tenantId);

    @Override
    CarDTO findById(UUID tenantId, UUID id);

    List<CarDTO> findAllByResident(UUID tenantId, UUID residentId);

    CarDTO findByLicensePlateCar(UUID tenantId, String licensePlateCarId);

}
