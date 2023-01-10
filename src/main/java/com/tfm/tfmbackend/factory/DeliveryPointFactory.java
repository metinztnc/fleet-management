package com.tfm.tfmbackend.factory;

import com.tfm.tfmbackend.enumeration.GeneralEnumeration.DeliveryPoint;
import com.tfm.tfmbackend.strategy.IDeliveryPointStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DeliveryPointFactory {

    Map<DeliveryPoint, IDeliveryPointStrategy> map;

    @Autowired
    public DeliveryPointFactory(Set<IDeliveryPointStrategy> deliveryPointStrategySet) {
        createStrategy(deliveryPointStrategySet);
    }

    private void createStrategy(Set<IDeliveryPointStrategy> deliveryPointStrategySet) {
        map = new HashMap<DeliveryPoint, IDeliveryPointStrategy>();
        deliveryPointStrategySet.forEach(deliveryPointStrategy -> map.put(deliveryPointStrategy.getDeliveryPointType(),deliveryPointStrategy));
    }

    public IDeliveryPointStrategy findDeliveryPointType(DeliveryPoint deliveryPointEnum){
        return map.get(deliveryPointEnum);
    }
}
