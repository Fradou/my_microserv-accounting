package com.fradou.accounting.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    @Column(unique = true, length = 50, nullable = false)
    private String label;

    @Column(nullable = false)
    private Boolean deleted;

    @Column
    private LocalDate deletionDate;

    @OneToMany(mappedBy = "category")
    private List<Operation> operations;
}
