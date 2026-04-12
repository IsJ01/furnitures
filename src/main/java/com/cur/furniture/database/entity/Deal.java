package com.cur.furniture.database.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "deals")
@Entity
public class Deal extends BaseEntity {

    private String phone;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "deal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DealFurniture> dealFurnitures = new ArrayList<>();

    public Deal(String phone) {
        this.phone = phone;
    }

}
