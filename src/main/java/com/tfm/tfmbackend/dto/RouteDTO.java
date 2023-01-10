package com.tfm.tfmbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RouteDTO {

    public int deliveryPoint;
    public ArrayList<DeliveryDTO> deliveries;
}
