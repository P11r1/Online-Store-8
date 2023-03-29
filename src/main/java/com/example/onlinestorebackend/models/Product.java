package com.example.onlinestorebackend.models;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


/**
 * Product model
 *
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */
@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Product extends Auditable<String> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private String description;

    private float inventory;
    private String thumbnailUrl;
    @OneToOne(cascade = CascadeType.MERGE)
    private Category category;

    private float price;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @OneToMany(cascade = CascadeType.MERGE)
    List<Author> authorities;

    private boolean isActive;

}
