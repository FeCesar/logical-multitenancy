package com.api.parkingcontrol.domain.services.interfaces;

import com.api.parkingcontrol.domain.dtos.TenantDTO;
import com.api.parkingcontrol.domain.models.TenantModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITenantService{

    TenantDTO save(TenantModel model);

    List<TenantDTO> findAll();

    Optional<TenantDTO> findById(UUID id);

    TenantDTO findByName(String name);
}
