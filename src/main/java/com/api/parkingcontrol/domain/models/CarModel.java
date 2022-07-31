package com.api.parkingcontrol.domain.models;

import com.api.parkingcontrol.domain.dtos.CarDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "cars")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarModel implements BaseEntity{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, unique = true, length = 7)
    private String licensePlateCar;

    @Column(nullable = false, length = 70)
    private String modelCar;

    @Column(nullable = false, length = 70)
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "resident_id")
    private ResidentModel resident;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private TenantModel tenant;

    public CarDTO toDTO(){
        return CarDTO.builder()
                .id(this.getId())
                .color(this.getColor())
                .licensePlateCar(this.getLicensePlateCar())
                .modelCar(this.getModelCar())
                .resident(this.getResident().toDTO())
                .build();
    }

}
