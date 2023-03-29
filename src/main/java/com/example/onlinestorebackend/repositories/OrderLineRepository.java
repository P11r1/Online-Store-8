package com.example.onlinestorebackend.repositories;

import com.example.onlinestorebackend.models.Order;
import com.example.onlinestorebackend.models.OrderLine;
import com.example.onlinestorebackend.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */
@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine,Long> {

    Optional<OrderLine> findById(Long id);


}
