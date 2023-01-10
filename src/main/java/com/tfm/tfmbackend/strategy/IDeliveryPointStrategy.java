package com.tfm.tfmbackend.strategy;

import com.tfm.tfmbackend.dto.DeliveryDTO;
import com.tfm.tfmbackend.enumeration.GeneralEnumeration;

import java.util.ArrayList;

public interface IDeliveryPointStrategy {

  void distribute(ArrayList<DeliveryDTO> deliveries);

  GeneralEnumeration.DeliveryPoint getDeliveryPointType();

}
