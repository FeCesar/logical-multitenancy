package com.api.parkingcontrol.domain.controllers;

import com.api.parkingcontrol.domain.dtos.ResidentDTO;
import com.api.parkingcontrol.domain.dtos.TenantDTO;
import com.api.parkingcontrol.domain.forms.ResidentForm;
import com.api.parkingcontrol.domain.models.ResidentModel;
import com.api.parkingcontrol.domain.services.impl.ResidentService;
import com.api.parkingcontrol.domain.services.impl.TenantService;
import com.api.parkingcontrol.domain.services.interfaces.IResidentService;
import com.api.parkingcontrol.domain.services.interfaces.ITenantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/resident")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ResidentController {

    @Autowired
    private IResidentService residentService;

    @Autowired
    private ITenantService tenantService;

    @PostMapping("/")
    public ResponseEntity<Object> saveResident(@RequestAttribute String tenantName, @RequestBody @Valid ResidentForm residentForm){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);

        if(Objects.isNull(tenantDTO)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not exists");
        }

        ResidentModel residentModel = new ResidentModel();
        BeanUtils.copyProperties(residentForm, residentModel);
        residentModel.setTenant(tenantDTO.toModel());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.residentService.save(residentModel));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAll(@RequestAttribute String tenantName){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);

        if(Objects.isNull(tenantDTO)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.residentService.findAll(tenantDTO.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id, @RequestAttribute String tenantName){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);

        if(Objects.isNull(tenantDTO)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        ResidentDTO residentDTO = this.residentService.findById(tenantDTO.getId(), id);

        if(Objects.isNull(residentDTO)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not Exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(residentDTO);
    }

}
