package com.api.parkingcontrol.domain.forms;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResidentForm implements BaseForm {
    private static final long serialVersionUID = 1L;

    private String fullName;
    private LocalDate birthDate;

}
