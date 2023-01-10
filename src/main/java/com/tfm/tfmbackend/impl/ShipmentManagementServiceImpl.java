package com.tfm.tfmbackend.impl;

import com.tfm.tfmbackend.dto.DeliveryDTO;
import com.tfm.tfmbackend.dto.RouteDTO;
import com.tfm.tfmbackend.dto.ShipmentDTO;
import com.tfm.tfmbackend.factory.DeliveryPointFactory;
import com.tfm.tfmbackend.service.IShipmentManagementService;
import com.tfm.tfmbackend.utils.ShipmentUtils;
import com.tfm.tfmbackend.enumeration.GeneralEnumeration;
import com.tfm.tfmbackend.repository.PackageRepository;
import com.tfm.tfmbackend.repository.SackRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShipmentManagementServiceImpl implements IShipmentManagementService {

    private final DeliveryPointFactory deliveryPointFactory;
    private final PackageRepository packageRepository;
    private final SackRepository sackRepository;

    public ShipmentManagementServiceImpl(DeliveryPointFactory deliveryPointFactory, PackageRepository packageRepository, SackRepository sackRepository) {
        this.deliveryPointFactory = deliveryPointFactory;
        this.packageRepository = packageRepository;
        this.sackRepository = sackRepository;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public ShipmentDTO distribute(ShipmentDTO shipmentRequestDTO, String vehiclePlate) {

        ArrayList<RouteDTO> routes = shipmentRequestDTO.getRoute();

        this.loadedAllShipments(routes);

        for (RouteDTO route:routes) {
            deliveryPointFactory.findDeliveryPointType(GeneralEnumeration.DeliveryPoint.of(route.getDeliveryPoint())).distribute(route.getDeliveries());
        }
        shipmentRequestDTO.setVehicle(vehiclePlate);
        return shipmentRequestDTO;
    }

    private void loadedAllShipments(ArrayList<RouteDTO> routes) {
        ArrayList<String> sackBarcodes = new ArrayList<>();
        List<String> barcodes = new ArrayList<>();
        for (RouteDTO route : routes) {
            route.getDeliveries().forEach(deliveryDTO -> deliveryDTO.setState(GeneralEnumeration.PackageState.LOADED.getValue()));
            barcodes.addAll(route.deliveries.stream().map(DeliveryDTO::getBarcode).collect(Collectors.toList()));
        }
        barcodes.stream()
                .filter(ShipmentUtils::isSack)
                .forEach(sackBarcodes::add);
        this.packageRepository.setPackagesStateByBarcode(GeneralEnumeration.PackageState.LOADED.getValue(), barcodes);
        this.sackRepository.setSacksStateByBarcode(GeneralEnumeration.PackageState.LOADED.getValue(),sackBarcodes);
    }
}
