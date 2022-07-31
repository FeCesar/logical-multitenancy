package com.api.parkingcontrol.domain.models;

import com.api.parkingcontrol.domain.dtos.TenantDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Entity
@Table(name = "tenants")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantModel implements BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    public TenantDTO toDTO(){
        return TenantDTO.builder()
                .id(this.getId())
                .name(this.getName())
                .build();
    }

}
