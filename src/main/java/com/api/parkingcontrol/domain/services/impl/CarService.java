package com.api.parkingcontrol.domain.services.impl;

import com.api.parkingcontrol.domain.dtos.CarDTO;
import com.api.parkingcontrol.domain.models.CarModel;
import com.api.parkingcontrol.domain.repositories.CarRepository;
import com.api.parkingcontrol.domain.services.interfaces.ICarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarService implements ICarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public CarDTO save(CarModel model) {
        return this.carRepository.save(model).toDTO();
    }

    @Override
    public List<CarDTO> findAll(UUID tenantId) {
        return this.carRepository.findAllByTenantId(tenantId)
                .stream()
                .map(CarModel::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO findById(UUID tenantId, UUID id) {
        return this.carRepository.findById(tenantId, id)
                .map(CarModel::toDTO)
                .orElse(null);
    }

    @Override
    public List<CarDTO> findAllByResident(UUID tenantId, UUID residentId) {
        return this.carRepository.findAllByResident(tenantId, residentId)
                .stream()
                .map(CarModel::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CarDTO findByLicensePlateCar(UUID tenantId, String licensePlateCarId) {
        return this.carRepository.findByLicensePlateCar(tenantId, licensePlateCarId)
                .map(CarModel::toDTO)
                .orElse(null);
    }
}
