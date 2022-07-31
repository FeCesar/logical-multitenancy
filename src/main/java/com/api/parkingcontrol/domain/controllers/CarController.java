package com.api.parkingcontrol.domain.controllers;

import com.api.parkingcontrol.domain.dtos.CarDTO;
import com.api.parkingcontrol.domain.dtos.ResidentDTO;
import com.api.parkingcontrol.domain.dtos.TenantDTO;
import com.api.parkingcontrol.domain.forms.CarForm;
import com.api.parkingcontrol.domain.models.CarModel;
import com.api.parkingcontrol.domain.services.interfaces.ICarService;
import com.api.parkingcontrol.domain.services.interfaces.IResidentService;
import com.api.parkingcontrol.domain.services.interfaces.ITenantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/car")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CarController {

    @Autowired
    private ICarService carService;

    @Autowired
    private ITenantService tenantService;

    @Autowired
    private IResidentService residentService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestAttribute String tenantName, @RequestBody @Valid CarForm carForm){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);
        if(tenantDTO == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        ResidentDTO residentDTO = this.residentService.findById(tenantDTO.getId(), carForm.getResidentId());
        if(residentDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not Exists");
        }

        CarDTO carDTO = this.carService.findByLicensePlateCar(tenantDTO.getId(), carForm.getLicensePlateCar());
        if(carDTO != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already exists a same license plate car!");
        }

        CarModel carModel = new CarModel();
        BeanUtils.copyProperties(carForm, carModel);

        carModel.setTenant(tenantDTO.toModel());
        carModel.setResident(residentDTO.toModel());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.carService.save(carModel));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAll(@RequestAttribute String tenantName){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);

        if(tenantDTO == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.carService.findAll(tenantDTO.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@RequestAttribute String tenantName, @PathVariable UUID id){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);
        if(tenantDTO == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        CarDTO carDTO = this.carService.findById(tenantDTO.getId(), id);
        if(carDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not Exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(carDTO);
    }

    @GetMapping("/by/{residentId}")
    public ResponseEntity<Object> getCarsByResident(@RequestAttribute String tenantName, @PathVariable UUID residentId){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);
        if(tenantDTO == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        ResidentDTO residentDTO = this.residentService.findById(tenantDTO.getId(), residentId);
        if(residentDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resident not Exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.carService.findAllByResident(tenantDTO.getId(), residentDTO.getId()));
    }

}
