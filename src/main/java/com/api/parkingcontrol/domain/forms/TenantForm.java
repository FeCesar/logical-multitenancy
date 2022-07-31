package com.api.parkingcontrol.domain.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TenantForm implements BaseForm{
    private static final long serialVersionUID = 1L;

    public String name;

}
