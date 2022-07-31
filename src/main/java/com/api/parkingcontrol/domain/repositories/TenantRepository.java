package com.api.parkingcontrol.domain.repositories;

import com.api.parkingcontrol.domain.models.TenantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TenantRepository extends JpaRepository<TenantModel, UUID> {

    TenantModel findByName(String name);

}
