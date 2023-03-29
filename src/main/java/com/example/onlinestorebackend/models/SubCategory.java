package com.example.onlinestorebackend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Bahadir Tasli
 * @Date 3/23/2023
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class SubCategory extends Auditable<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private boolean isActive;

    @OneToOne(cascade = CascadeType.MERGE)
    private Category category;

}
