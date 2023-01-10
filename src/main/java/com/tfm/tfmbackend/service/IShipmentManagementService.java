package com.tfm.tfmbackend.service;

import com.tfm.tfmbackend.dto.ShipmentDTO;

public interface IShipmentManagementService {

    ShipmentDTO distribute(ShipmentDTO shipmentRequestDTO, String vehiclePlate);
}
