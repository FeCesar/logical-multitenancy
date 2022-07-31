package com.api.parkingcontrol.domain.repositories;

import com.api.parkingcontrol.domain.dtos.CarDTO;
import com.api.parkingcontrol.domain.models.CarModel;
import com.api.parkingcontrol.domain.models.ResidentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CarRepository extends JpaRepository<CarModel, UUID> {

    @Query(value = "SELECT * FROM cars WHERE tenant_id = :tenantId", nativeQuery = true)
    List<CarModel> findAllByTenantId(UUID tenantId);

    @Query(value = "SELECT * FROM cars WHERE tenant_id = :tenantId AND id = :id", nativeQuery = true)
    Optional<CarModel> findById(UUID tenantId, UUID id);

    @Query(value = "SELECT * FROM cars WHERE tenant_id = :tenantId AND resident_id = :residentId", nativeQuery = true)
    List<CarModel>  findAllByResident(UUID tenantId, UUID residentId);

    @Query(value = "SELECT * FROM cars WHERE tenant_id = :tenantId AND license_plate_car = :licensePlateCarId", nativeQuery = true)
    Optional<CarModel> findByLicensePlateCar(UUID tenantId, String licensePlateCarId);
}
