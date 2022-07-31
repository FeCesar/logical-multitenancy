package com.api.parkingcontrol.domain.services.interfaces;

import java.util.List;
import java.util.UUID;

public interface DefaultService <ModelType, DTOType> {

    DTOType save(ModelType model);

    List<DTOType> findAll(UUID tenantId);

    DTOType findById(UUID tenantId, UUID id);

}
