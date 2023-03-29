package com.example.onlinestorebackend.services;

import com.example.onlinestorebackend.exceptions.OrderLineNotFoundException;
import com.example.onlinestorebackend.models.OrderLine;

import java.util.List;

/**
 * @author Bahadir Tasli
 * @Date 3/21/2023
 */
public interface OrderLineService {

    /**
     * To create a new orderLine
     *
     * @param orderLine OrderLine
     */
    void createOrderLine(OrderLine orderLine);

    /**
     * To find a orderLine by ID
     *
     * @param id OrderLine ID
     * @return OrderLine
     */
    OrderLine findOrderLineById(Long id) throws OrderLineNotFoundException;

    /**
     * To find all OrderLines
     *
     * @return a list of OrderLines
     */
    List<OrderLine> findAllOrderLines();

    /**
     * To delete a OrderLine by ID
     *
     * @param id OrderLine id
     */
    void deleteOrderLineById(Long id) throws OrderLineNotFoundException;

    /**
     * To restore a OrderLine by ID
     *
     * @param id OrderLine id
     */
    void restoreOrderLineById(Long id) throws OrderLineNotFoundException;
}
