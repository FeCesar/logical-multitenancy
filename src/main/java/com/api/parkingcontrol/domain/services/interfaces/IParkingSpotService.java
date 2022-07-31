package com.api.parkingcontrol.domain.services.interfaces;

import com.api.parkingcontrol.domain.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.domain.enums.SpotStatus;
import com.api.parkingcontrol.domain.models.ParkingSpotModel;

import java.util.List;
import java.util.UUID;

public interface IParkingSpotService extends DefaultService<ParkingSpotModel, ParkingSpotDTO>{

    @Override
    ParkingSpotDTO save(ParkingSpotModel model);

    @Override
    List<ParkingSpotDTO> findAll(UUID tenantId);

    @Override
    ParkingSpotDTO findById(UUID tenantId, UUID id);

    ParkingSpotDTO findByParkingSpotNumber(UUID tenantId, String parkingSpotNumber);

    List<ParkingSpotDTO> findByStatus(UUID tenantId, SpotStatus status);

    ParkingSpotDTO update(UUID tenantId, UUID spotId, UUID carId, UUID residentId, SpotStatus status);

}
