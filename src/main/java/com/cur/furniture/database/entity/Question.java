package com.cur.furniture.database.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@Table(name = "questions")
@Entity
public class Question extends BaseEntity {

    private String phone;

    private String text;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private Answer answer;

    public Question(String phone, String text) {
        this.phone = phone;
        this.text = text;
    }

    public boolean isClosed() {
        return answer != null;
    }

}
