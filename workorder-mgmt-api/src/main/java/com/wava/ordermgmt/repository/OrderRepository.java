package com.wava.ordermgmt.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.wava.ordermgmt.model.Order;

@Repository
public interface OrderRepository extends CrudRepository<Order,Long>{

}
