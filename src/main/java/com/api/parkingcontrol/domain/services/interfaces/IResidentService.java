package com.api.parkingcontrol.domain.services.interfaces;

import com.api.parkingcontrol.domain.dtos.ResidentDTO;
import com.api.parkingcontrol.domain.models.ResidentModel;

import java.util.List;
import java.util.UUID;

public interface IResidentService extends DefaultService<ResidentModel, ResidentDTO>{

    @Override
    ResidentDTO save(ResidentModel model);

    @Override
    List<ResidentDTO> findAll(UUID tenantId);

    @Override
    ResidentDTO findById(UUID tenantId, UUID id);

}
