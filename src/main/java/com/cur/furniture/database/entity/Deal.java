package com.cur.furniture.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "furniture_id")
    private Furniture furniture;

}
