package com.tfm.tfmbackend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class BaseEntity implements Serializable {

    public Date createDate;
    public Date updateDate;

    @PrePersist
    public void initialPrePersist() {
        this.createDate = new Date();
    }

    @PreUpdate
    public void initialPreUpdate() {
        this.updateDate = new Date();
    }
}
