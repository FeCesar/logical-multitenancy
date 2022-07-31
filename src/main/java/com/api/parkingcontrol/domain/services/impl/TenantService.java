package com.api.parkingcontrol.domain.services.impl;

import com.api.parkingcontrol.domain.dtos.TenantDTO;
import com.api.parkingcontrol.domain.models.TenantModel;
import com.api.parkingcontrol.domain.repositories.TenantRepository;
import com.api.parkingcontrol.domain.services.interfaces.ITenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TenantService implements ITenantService {

    @Autowired
    private TenantRepository tenantRepository;

    @Override
    @Transactional
    public TenantDTO save(TenantModel model) {
        return Optional.of(this.tenantRepository.save(model))
                .map(TenantModel::toDTO)
                .get();
    }

    @Override
    public List<TenantDTO> findAll() {
        return this.tenantRepository.findAll()
                .stream()
                .map(TenantModel::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TenantDTO> findById(UUID id) {
        return this.tenantRepository.findById(id).map(TenantModel::toDTO);
    }

    @Override
    public TenantDTO findByName(String name){
        return Optional.ofNullable(this.tenantRepository.findByName(name))
                .map(TenantModel::toDTO)
                .orElse(null);
    }
}
