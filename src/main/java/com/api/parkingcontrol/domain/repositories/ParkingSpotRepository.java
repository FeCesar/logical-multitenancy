package com.api.parkingcontrol.domain.repositories;

import com.api.parkingcontrol.domain.enums.SpotStatus;
import com.api.parkingcontrol.domain.models.ParkingSpotModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpotModel, UUID> {

    @Query(value = "SELECT * FROM parking_spots WHERE tenant_id = :tenantId", nativeQuery = true)
    List<ParkingSpotModel> findAllByTenantId(UUID tenantId);

    @Query(value = "SELECT * FROM parking_spots WHERE tenant_id = :tenantId AND id = :id", nativeQuery = true)
    Optional<ParkingSpotModel> findById(UUID tenantId, UUID id);

    @Query(value = "SELECT * FROM parking_spots WHERE tenant_id = :tenantId AND parking_spot_number = :parkingSpotNumber", nativeQuery = true)
    Optional<ParkingSpotModel> findByParkingSpotNumber(UUID tenantId, String parkingSpotNumber);

    @Query(value = "SELECT * FROM parking_spots WHERE tenant_id = :tenantId AND status = :status", nativeQuery = true)
    List<ParkingSpotModel> findByStatus(UUID tenantId, String status);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE parking_spots SET car_id = :carId, resident_id = :residentId, status = :status WHERE tenant_id = :tenantId AND id = :spotId", nativeQuery = true)
    void update(UUID tenantId, UUID spotId, UUID carId, UUID residentId, String status);

}
