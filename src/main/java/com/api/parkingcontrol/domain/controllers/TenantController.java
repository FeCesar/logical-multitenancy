package com.api.parkingcontrol.domain.controllers;

import com.api.parkingcontrol.domain.dtos.TenantDTO;
import com.api.parkingcontrol.domain.forms.TenantForm;
import com.api.parkingcontrol.domain.models.TenantModel;
import com.api.parkingcontrol.domain.services.impl.TenantService;
import com.api.parkingcontrol.domain.services.interfaces.ITenantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/tenant")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TenantController{

    @Autowired
    private ITenantService tenantService;

    @GetMapping("/")
    public ResponseEntity<List<TenantDTO>> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(this.tenantService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@PathVariable UUID id){

        Optional<TenantDTO> tenantDTO = this.tenantService.findById(id);

        if(tenantDTO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found tenant.");
        }

        return ResponseEntity.status(HttpStatus.OK).body(tenantDTO.get());
    }

    @PostMapping("/")
    public ResponseEntity<TenantDTO> save(@RequestBody @Valid TenantForm tenantForm){
        TenantModel tenantModel = new TenantModel();
        tenantForm.setName(tenantForm.getName().toLowerCase());

        BeanUtils.copyProperties(tenantForm, tenantModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(this.tenantService.save(tenantModel));
    }

}
