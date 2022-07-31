package com.api.parkingcontrol.domain.services.impl;

import com.api.parkingcontrol.domain.dtos.ResidentDTO;
import com.api.parkingcontrol.domain.models.ResidentModel;
import com.api.parkingcontrol.domain.repositories.ResidentRepository;
import com.api.parkingcontrol.domain.services.interfaces.IResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ResidentService implements IResidentService {

    @Autowired
    private ResidentRepository residentRepository;

    @Override
    @Transactional
    public ResidentDTO save(ResidentModel model) {
        return this.residentRepository.save(model).toDTO();
    }


    @Override
    public List<ResidentDTO> findAll(UUID tenantId) {
        return this.residentRepository.findAllByTenantId(tenantId)
                .stream()
                .map(ResidentModel::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResidentDTO findById(UUID tenantId, UUID id) {
        return this.residentRepository.findById(tenantId, id)
                .map(ResidentModel::toDTO)
                .orElse(null);
    }
}
