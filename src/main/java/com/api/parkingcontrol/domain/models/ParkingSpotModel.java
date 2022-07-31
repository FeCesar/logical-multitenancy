package com.api.parkingcontrol.domain.models;

import com.api.parkingcontrol.domain.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.domain.enums.SpotStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "parking_spots")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpotModel implements BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 10)
    private String parkingSpotNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SpotStatus status;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "car_id")
    private CarModel car;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id")
    private ResidentModel resident;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private TenantModel tenant;

    public ParkingSpotDTO toDTO(){
        return ParkingSpotDTO.builder()
                .id(this.getId())
                .parkingSpotNumber(this.getParkingSpotNumber())
                .status(this.getStatus())
                .car(this.getCar() != null ? this.getCar().toDTO() : null)
                .resident(this.getResident() != null ? this.getResident().toDTO(): null)
                .build();
    }
}
