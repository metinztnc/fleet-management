package com.tfm.tfmbackend.controller;

import com.tfm.tfmbackend.dto.ShipmentDTO;
import com.tfm.tfmbackend.provider.UrlProvider;
import com.tfm.tfmbackend.service.IShipmentManagementService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(UrlProvider.URL_V1_VEHICLES)
public class ShipmentManagementController {

    private final IShipmentManagementService shipmentManagementService;

    public ShipmentManagementController(IShipmentManagementService shipmentManagementService) {
        this.shipmentManagementService = shipmentManagementService;
    }

    @PostMapping(value = UrlProvider.DISTRIBUTE)
    @Operation(summary = "Distribute the shipment to the delivery points")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully Shipment distributed"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    public ResponseEntity<ShipmentDTO> distribute(@RequestBody ShipmentDTO shipmentRequestDTO, @PathVariable String vehiclePlate) {
        return new ResponseEntity<>(shipmentManagementService.distribute(shipmentRequestDTO,vehiclePlate), HttpStatus.OK);
    }
}
