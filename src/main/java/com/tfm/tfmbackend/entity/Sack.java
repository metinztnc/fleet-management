package com.tfm.tfmbackend.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Sack extends BaseShipmentEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sack_id")
    private Long sackId;

    @OneToMany
    public List<Package> packages;

}
