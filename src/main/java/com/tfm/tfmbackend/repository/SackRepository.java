package com.tfm.tfmbackend.repository;

import com.tfm.tfmbackend.entity.Sack;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SackRepository extends BaseJpaRepository<Sack,Long> {

    List<Sack> findByDeliveryPoint(Integer deliveryPoint_value);

    @Modifying(clearAutomatically = true)
    @Query(value = "update Sack sack set sack.state =:state where sack.barcode in :barcodes")
    void setSacksStateByBarcode(@Param("state") Integer state, @Param("barcodes")List<String> barcodes);

}
