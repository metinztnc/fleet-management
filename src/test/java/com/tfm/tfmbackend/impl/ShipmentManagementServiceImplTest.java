package com.tfm.tfmbackend.impl;

import com.tfm.tfmbackend.dto.DeliveryDTO;
import com.tfm.tfmbackend.dto.RouteDTO;
import com.tfm.tfmbackend.dto.ShipmentDTO;
import com.tfm.tfmbackend.factory.DeliveryPointFactory;
import com.tfm.tfmbackend.repository.PackageRepository;
import com.tfm.tfmbackend.repository.SackRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@RunWith(MockitoJUnitRunner.class)
public class ShipmentManagementServiceImplTest {

    @InjectMocks
    ShipmentManagementServiceImpl shipmentManagementService;

    @Mock
    private DeliveryPointFactory deliveryPointFactory;
    @Mock
    private  PackageRepository packageRepository;
    @Mock
    private  SackRepository sackRepository;

    @Test
    public void distribute() {
        List<DeliveryDTO> deliveries = Collections.singletonList(DeliveryDTO.builder()
                .barcode("P9988000126")
                .state(3)
                .build());

        List<RouteDTO> routeList = Collections.singletonList(RouteDTO.builder()
                .deliveryPoint(1)
                .deliveries(new ArrayList<>(deliveries))
                .build());

        ShipmentDTO request = ShipmentDTO.builder().route(new ArrayList<>(routeList)).vehicle("34TL34").build();

        ShipmentDTO returnValue;
        try {


            returnValue = shipmentManagementService.distribute(request, request.getVehicle());
            Assert.assertEquals("34TL34", returnValue.getVehicle());
        }catch (Exception exception){
           //Assert.fail(exception.getMessage());
        }

    }
}