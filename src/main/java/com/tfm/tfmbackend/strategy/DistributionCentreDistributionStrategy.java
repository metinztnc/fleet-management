package com.tfm.tfmbackend.strategy;

import com.tfm.tfmbackend.dto.DeliveryDTO;
import com.tfm.tfmbackend.entity.Package;
import com.tfm.tfmbackend.entity.Sack;
import com.tfm.tfmbackend.enumeration.GeneralEnumeration;
import com.tfm.tfmbackend.mapper.LogMapper;
import com.tfm.tfmbackend.utils.LogUtils;
import com.tfm.tfmbackend.utils.ShipmentUtils;
import com.tfm.tfmbackend.repository.LogRepository;
import com.tfm.tfmbackend.repository.PackageRepository;
import com.tfm.tfmbackend.repository.SackRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class DistributionCentreDistributionStrategy implements IDeliveryPointStrategy {

    private final PackageRepository packageRepository;
    private final SackRepository sackRepository;
    private final LogRepository logRepository;
    private final LogMapper logMapper;

    public DistributionCentreDistributionStrategy(PackageRepository packageRepository, SackRepository sackRepository, LogRepository logRepository, LogMapper logMapper) {
        this.packageRepository = packageRepository;
        this.sackRepository = sackRepository;
        this.logRepository = logRepository;
        this.logMapper = logMapper;
    }

    @Override
    public void distribute(ArrayList<DeliveryDTO> deliveries) {
        List<Package> distributionCentrePacks = this.packageRepository.findByDeliveryPoint(GeneralEnumeration.DeliveryPoint.DISTRIBUTION_CENTRE.getValue());
        List<Sack> distributionCentreSacks = this.sackRepository.findByDeliveryPoint(GeneralEnumeration.DeliveryPoint.DISTRIBUTION_CENTRE.getValue());

        List<String> allExcludeList = LogUtils.allExcludeList(deliveries, distributionCentreSacks, distributionCentrePacks);

        Set<String> loggedBarcodes = new HashSet<>();

        for (DeliveryDTO deliveryDTO : deliveries) {
            for (Package pack : distributionCentrePacks) {
                if (deliveryDTO.getBarcode().equals(pack.getBarcode())) {
                    deliveryDTO.setState(GeneralEnumeration.PackageState.UNLOADED.getValue());
                    pack.setState(GeneralEnumeration.PackageState.UNLOADED.getValue());
                    this.packageRepository.saveAndFlush(pack);
                } else if (!(allExcludeList).contains(deliveryDTO.getBarcode())) {
                    if (!loggedBarcodes.contains(deliveryDTO.getBarcode())) {
                        this.logRepository.saveAndFlush(this.logMapper.toEntity(LogUtils.buildLog(deliveryDTO, GeneralEnumeration.DeliveryPoint.DISTRIBUTION_CENTRE.getValue())));
                    }
                    loggedBarcodes.add(deliveryDTO.getBarcode());
                }
            }
            if (ShipmentUtils.isSack(deliveryDTO.getBarcode())) {
                for (Sack sack : distributionCentreSacks) {
                    if (deliveryDTO.getBarcode().equals(sack.getBarcode())) {
                        deliveryDTO.setState(GeneralEnumeration.PackageState.UNLOADED.getValue());
                        sack.setState(GeneralEnumeration.PackageState.UNLOADED.getValue());
                        sack.getPackages().forEach(packInSack -> packInSack.setState(GeneralEnumeration.PackageState.UNLOADED.getValue()));
                        this.sackRepository.saveAndFlush(sack);
                    } else if (!(allExcludeList).contains(deliveryDTO.getBarcode())) {
                        if (!loggedBarcodes.contains(deliveryDTO.getBarcode())) {
                            this.logRepository.saveAndFlush(this.logMapper.toEntity(LogUtils.buildLog(deliveryDTO, GeneralEnumeration.DeliveryPoint.DISTRIBUTION_CENTRE.getValue())));
                        }
                        loggedBarcodes.add(deliveryDTO.getBarcode());
                    }
                }
            }
        }
    }

    @Override
    public GeneralEnumeration.DeliveryPoint getDeliveryPointType() {
        return GeneralEnumeration.DeliveryPoint.DISTRIBUTION_CENTRE;
    }
}
