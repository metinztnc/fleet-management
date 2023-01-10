package com.tfm.tfmbackend.utils;


import com.tfm.tfmbackend.dto.DeliveryDTO;
import com.tfm.tfmbackend.dto.LogDTO;
import com.tfm.tfmbackend.entity.Package;
import com.tfm.tfmbackend.entity.Sack;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogUtils {
    public static LogDTO buildLog(DeliveryDTO deliveryDTO, Integer deliveryPoint) {
        return LogDTO.builder()
                .barcode(Objects.nonNull(deliveryDTO) ? deliveryDTO.getBarcode() : "bos")
                .deliveryPoint(deliveryPoint)
                .description(deliveryPoint + "-" + deliveryDTO.getBarcode())
                .build();
    }

    public static List<String> buildLogPackBarcodes(ArrayList<DeliveryDTO> requestDeliveries, List<Package> existDeliveries) {
        return existDeliveries.stream()
                .map(Package::getBarcode)
                .collect(Collectors.toList())
                .stream()
                .filter(requestDeliveries
                        .stream()
                        .map(DeliveryDTO::getBarcode)
                        .collect(Collectors.toList())::contains)
                .collect(Collectors.toList());
    }

    public static List<String> buildLogSackBarcodes(ArrayList<DeliveryDTO> requestDeliveries, List<Sack> existDeliveries) {
        return existDeliveries.stream()
                .map(Sack::getBarcode)
                .collect(Collectors.toList())
                .stream()
                .filter(requestDeliveries
                        .stream()
                        .map(DeliveryDTO::getBarcode)
                        .collect(Collectors.toList())::contains)
                .collect(Collectors.toList());
    }

    public static List<String> allExcludeList(ArrayList<DeliveryDTO> requestDeliveries, List<Sack> existSacks, List<Package> existPackages) {
        return Stream.concat(buildLogPackBarcodes(requestDeliveries, existPackages).stream(), buildLogSackBarcodes(requestDeliveries, existSacks).stream())
                .collect(Collectors.toList());
    }


}
