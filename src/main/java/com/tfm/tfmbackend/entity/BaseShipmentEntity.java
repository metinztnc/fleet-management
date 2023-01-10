package com.tfm.tfmbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseShipmentEntity extends BaseEntity {

    public String barcode;
    public Integer state;
    public Integer deliveryPoint;


}
