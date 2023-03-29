package com.example.onlinestorebackend.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Category model
 *
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = true)
public class Category extends Auditable<String> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private boolean isActive;

}
