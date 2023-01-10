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
public class ShipmentDTO {
    public String vehicle;
    public ArrayList<RouteDTO> route;
}
