package com.cur.furniture.database.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Table(name = "categories")
@Entity
public class Category extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Category parent;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "parent")
    private List<Category> children;

    private String name;

    public Category(Category parent, String name) {
        this.parent = parent;
        this.name = name;
    }

}
