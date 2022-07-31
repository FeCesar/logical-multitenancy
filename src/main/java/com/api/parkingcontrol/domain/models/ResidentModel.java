package com.api.parkingcontrol.domain.models;

import com.api.parkingcontrol.domain.dtos.ResidentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "residents")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResidentModel implements BaseEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(nullable = false)
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tenant_id")
    private TenantModel tenant;

    public ResidentDTO toDTO(){
        return ResidentDTO.builder()
                .id(this.getId())
                .birthDate(this.getBirthDate())
                .fullName(this.getFullName())
                .build();
    }

}
