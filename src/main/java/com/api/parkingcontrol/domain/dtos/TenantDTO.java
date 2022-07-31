package com.api.parkingcontrol.domain.dtos;

import com.api.parkingcontrol.domain.models.TenantModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TenantDTO implements BaseDTO{
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;

    public TenantModel toModel(){
        return TenantModel.builder()
                .id(this.getId())
                .name(this.getName())
                .build();
    }

}
