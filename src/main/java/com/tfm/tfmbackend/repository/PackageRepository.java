package com.tfm.tfmbackend.repository;

import com.tfm.tfmbackend.entity.Package;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackageRepository extends BaseJpaRepository<Package,Long> {

    List<Package> findByDeliveryPoint(Integer deliveryPoint);
    @Modifying(clearAutomatically = true)
    @Query(value = "update Package package set package.state =:state where package.barcode in :barcodes")
    void setPackagesStateByBarcode(@Param("state") Integer state, @Param("barcodes")List<String> barcodes);
}
