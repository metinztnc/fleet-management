package com.tfm.tfmbackend.utils;

import com.tfm.tfmbackend.entity.BaseShipmentEntity;
import com.tfm.tfmbackend.entity.Package;
import com.tfm.tfmbackend.enumeration.GeneralEnumeration;

import java.util.Objects;

public class ShipmentUtils {

    public static Boolean isSack(String barcode){
        if (Objects.nonNull(barcode))
            return GeneralEnumeration.ShipmentType.SACK.getShortCode().equals(String.valueOf(barcode.charAt(0)));
        return false;
    }

    public static Boolean sackUnloadedCheck(Package pack) {
        return pack.getSack().getPackages().stream()
                .map(BaseShipmentEntity::getState)
                .allMatch(state-> state.equals(GeneralEnumeration.PackageState.UNLOADED.getValue()));
    }
}
