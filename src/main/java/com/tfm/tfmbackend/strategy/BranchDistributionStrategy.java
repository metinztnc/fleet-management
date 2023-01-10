package com.tfm.tfmbackend.strategy;

import com.tfm.tfmbackend.dto.DeliveryDTO;
import com.tfm.tfmbackend.entity.Package;
import com.tfm.tfmbackend.enumeration.GeneralEnumeration;
import com.tfm.tfmbackend.mapper.LogMapper;
import com.tfm.tfmbackend.utils.LogUtils;
import com.tfm.tfmbackend.repository.LogRepository;
import com.tfm.tfmbackend.repository.PackageRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class BranchDistributionStrategy implements IDeliveryPointStrategy {

    private final PackageRepository packageRepository;
    private final LogRepository logRepository;
    private final LogMapper logMapper;

    public BranchDistributionStrategy(PackageRepository packageRepository, LogRepository logRepository, LogMapper logMapper) {
        this.packageRepository = packageRepository;
        this.logRepository = logRepository;
        this.logMapper = logMapper;
    }

    @Override
    public void distribute(ArrayList<DeliveryDTO> deliveries) {
        List<Package> branchDeliveries = this.packageRepository.findByDeliveryPoint(GeneralEnumeration.DeliveryPoint.BRANCH.getValue());
        List<String> logExcludeBarcodes = LogUtils.buildLogPackBarcodes(deliveries, branchDeliveries);
        Set<String> loggedBarcodes = new HashSet<>();

        for (DeliveryDTO deliveryDTO : deliveries) {
            for (Package pack : branchDeliveries) {
                if (deliveryDTO.getBarcode().equals(pack.getBarcode())) {
                    deliveryDTO.setState(GeneralEnumeration.PackageState.UNLOADED.getValue());
                    pack.setState(GeneralEnumeration.PackageState.UNLOADED.getValue());
                    this.packageRepository.saveAndFlush(pack);
                } else if (!logExcludeBarcodes.contains(deliveryDTO.getBarcode())) {
                    if (!loggedBarcodes.contains(deliveryDTO.getBarcode())) {
                        this.logRepository.saveAndFlush(this.logMapper.toEntity(LogUtils.buildLog(deliveryDTO, GeneralEnumeration.DeliveryPoint.BRANCH.getValue())));
                    }
                    loggedBarcodes.add(deliveryDTO.getBarcode());
                }
            }
        }

    }


    @Override
    public GeneralEnumeration.DeliveryPoint getDeliveryPointType() {
        return GeneralEnumeration.DeliveryPoint.BRANCH;
    }
}
