package com.api.parkingcontrol.domain.controllers;

import com.api.parkingcontrol.domain.dtos.CarDTO;
import com.api.parkingcontrol.domain.dtos.ParkingSpotDTO;
import com.api.parkingcontrol.domain.dtos.ResidentDTO;
import com.api.parkingcontrol.domain.dtos.TenantDTO;
import com.api.parkingcontrol.domain.enums.SpotStatus;
import com.api.parkingcontrol.domain.forms.ParkingSpotForm;
import com.api.parkingcontrol.domain.forms.ParkingSpotUpdateForm;
import com.api.parkingcontrol.domain.models.ParkingSpotModel;
import com.api.parkingcontrol.domain.services.interfaces.ICarService;
import com.api.parkingcontrol.domain.services.interfaces.IParkingSpotService;
import com.api.parkingcontrol.domain.services.interfaces.ITenantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/spot")
public class ParkingSpotController {

    @Autowired
    private IParkingSpotService parkingSpotService;

    @Autowired
    private ICarService carService;

    @Autowired
    private ITenantService tenantService;

    @PostMapping("/")
    public ResponseEntity<Object> save(@RequestAttribute String tenantName, @RequestBody @Valid ParkingSpotForm parkingSpotForm){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);
        if(tenantDTO == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        ParkingSpotDTO parkingSpotDTO = this.parkingSpotService.findByParkingSpotNumber(tenantDTO.getId(), parkingSpotForm.getParkingSpotNumber());
        if(parkingSpotDTO != null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Already exists spot number!");
        }

        ParkingSpotModel parkingSpotModel = new ParkingSpotModel();
        BeanUtils.copyProperties(parkingSpotForm, parkingSpotModel);
        parkingSpotModel.setTenant(tenantDTO.toModel());

        return ResponseEntity.status(HttpStatus.CREATED).body(this.parkingSpotService.save(parkingSpotModel));
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAll(@RequestAttribute String tenantName){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);
        if(tenantDTO == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.parkingSpotService.findAll(tenantDTO.getId()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById(@RequestAttribute String tenantName, @PathVariable UUID id){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);
        if(tenantDTO == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        ParkingSpotDTO parkingSpotDTO = this.parkingSpotService.findById(tenantDTO.getId(), id);
        if(parkingSpotDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Spot not exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(parkingSpotDTO);
    }

    @GetMapping("/by/{status}")
    public ResponseEntity<Object> getByStatus(@RequestAttribute String tenantName, @PathVariable String status){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);
        if(tenantDTO == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        try{
            SpotStatus.valueOf(status);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Status not Exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.parkingSpotService.findByStatus(tenantDTO.getId(), SpotStatus.valueOf(status)));
    }

    @PatchMapping("/")
    public ResponseEntity<Object> update(@RequestAttribute String tenantName, @RequestBody ParkingSpotUpdateForm parkingSpotUpdateForm){
        TenantDTO tenantDTO = this.tenantService.findByName(tenantName);
        if(tenantDTO == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Tenant not Exists");
        }

        ParkingSpotDTO parkingSpotDTO = this.parkingSpotService.findByParkingSpotNumber(tenantDTO.getId(), parkingSpotUpdateForm.getParkingSpotNumber());
        if(parkingSpotDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Spot not exists");
        }

        CarDTO carDTO = this.carService.findById(tenantDTO.getId(), parkingSpotUpdateForm.getCarId());
        if(carDTO == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not exists");
        }

        return ResponseEntity.status(HttpStatus.OK).body(this.parkingSpotService.update(tenantDTO.getId(),
                parkingSpotDTO.getId(), carDTO.getId(), carDTO.getResident().getId(), SpotStatus.BUSY));
    }

}
