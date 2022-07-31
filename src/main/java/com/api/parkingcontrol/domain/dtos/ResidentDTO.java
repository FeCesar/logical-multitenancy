package com.api.parkingcontrol.domain.dtos;

import com.api.parkingcontrol.domain.models.ResidentModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResidentDTO implements BaseDTO {
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String fullName;
    private LocalDate birthDate;

    public ResidentModel toModel(){
        return ResidentModel.builder()
                .id(this.getId())
                .fullName(this.getFullName())
                .birthDate(this.getBirthDate())
                .build();
    }

}
