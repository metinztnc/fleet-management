package com.tfm.tfmbackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Package extends BaseShipmentEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private Long packageId;

    @ManyToOne
    @JoinColumn(name = "sack_id", referencedColumnName = "sack_id", insertable = false, updatable = false)
    public Sack sack;

    public short desi;


}
