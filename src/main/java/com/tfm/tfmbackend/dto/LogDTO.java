package com.tfm.tfmbackend.dto;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class LogDTO {

    private Long logId;
    public Integer deliveryPoint;
    public String barcode;
    public String description;
}
