package com.cxf.elkcustomerservice.dao;

import com.cxf.elkcustomerservice.pojo.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author always_on_the_way
 * @date 2019-07-22
 */
@Repository
public interface CustomerDao extends MongoRepository<Customer,String> {
}
