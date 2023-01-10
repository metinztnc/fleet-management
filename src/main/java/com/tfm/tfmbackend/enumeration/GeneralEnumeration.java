package com.tfm.tfmbackend.enumeration;

import lombok.Getter;

import java.util.stream.Stream;

public class GeneralEnumeration {
    public enum PackageState {
        CREATED(1),
        LOADED_INTO_SACK(2),
        LOADED(3),
        UNLOADED(4);
        @Getter
        private final int value;

        PackageState(int value) {
            this.value = value;
        }

    }

    public enum SackState {
        CREATED(1),
        LOADED(3),
        UNLOADED(4);
        @Getter
        private final int value;

        SackState(int value) {
            this.value = value;
        }
    }

    public enum DeliveryPoint{
        BRANCH(1),
        DISTRIBUTION_CENTRE(2),
        TRANSFER_CENTRE(3);
        @Getter
        private final int value;

        DeliveryPoint(int value) {
            this.value = value;
        }

        public static DeliveryPoint of(Integer value) {
            return Stream.of(DeliveryPoint.values())
                    .filter(deliveryPoint -> value.equals(deliveryPoint.value)).findFirst().orElseThrow();
        }
    }

    public enum ShipmentType{
        PACKAGE("P"),
        SACK("C");
        @Getter
        private final String shortCode;
        ShipmentType(String shortCode) {
            this.shortCode = shortCode;
        }
    }
}
