package com.api.parkingcontrol.domain.services.impl;

import com.api.parkingcontrol.domain.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.domain.enums.SpotStatus;
import com.api.parkingcontrol.domain.models.ParkingSpotModel;
import com.api.parkingcontrol.domain.repositories.ParkingSpotRepository;
import com.api.parkingcontrol.domain.services.interfaces.IParkingSpotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingSpotService implements IParkingSpotService {

    @Autowired
    private ParkingSpotRepository parkingSpotRepository;

    @Override
    public ParkingSpotDTO save(ParkingSpotModel model) {
        return this.parkingSpotRepository.save(model).toDTO();
    }

    @Override
    public List<ParkingSpotDTO> findAll(UUID tenantId) {
        return this.parkingSpotRepository.findAllByTenantId(tenantId)
                .stream()
                .map(ParkingSpotModel::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ParkingSpotDTO findById(UUID tenantId, UUID id) {
        return this.parkingSpotRepository.findById(tenantId, id)
                .map(ParkingSpotModel::toDTO)
                .orElse(null);
    }

    @Override
    public ParkingSpotDTO findByParkingSpotNumber(UUID tenantId, String parkingSpotNumber) {
        return this.parkingSpotRepository.findByParkingSpotNumber(tenantId, parkingSpotNumber)
                .map(ParkingSpotModel::toDTO)
                .orElse(null);
    }

    @Override
    public List<ParkingSpotDTO> findByStatus(UUID tenantId, SpotStatus status) {
        return this.parkingSpotRepository.findByStatus(tenantId, status.toString())
                .stream()
                .map(ParkingSpotModel::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public ParkingSpotDTO update(UUID tenantId, UUID spotId, UUID carId, UUID residentId, SpotStatus status) {
        this.parkingSpotRepository.update(tenantId, spotId, carId, residentId, status.toString());
        return this.findById(tenantId, spotId);
    }
}
