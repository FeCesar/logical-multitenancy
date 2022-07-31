package com.api.parkingcontrol.domain.repositories;

import com.api.parkingcontrol.domain.models.ResidentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ResidentRepository extends JpaRepository<ResidentModel, UUID> {

    @Query(value = "SELECT * FROM residents WHERE tenant_id = :tenantId", nativeQuery = true)
    List<ResidentModel> findAllByTenantId(UUID tenantId);

    @Query(value = "SELECT * FROM residents WHERE tenant_id = :tenantId AND id = :id", nativeQuery = true)
    Optional<ResidentModel> findById(UUID tenantId, UUID id);



}
