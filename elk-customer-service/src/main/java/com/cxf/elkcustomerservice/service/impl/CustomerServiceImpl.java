package com.cxf.elkcustomerservice.service.impl;

import com.cxf.common.service.impl.MongodDBBaseServiceImpl;
import com.cxf.elkcustomerservice.dao.CustomerDao;
import com.cxf.elkcustomerservice.pojo.Customer;
import com.cxf.elkcustomerservice.service.ICustomerService;
import org.springframework.stereotype.Service;

/**
 * @author always_on_the_way
 * @date 2019-07-22
 */
@Service
public class CustomerServiceImpl extends MongodDBBaseServiceImpl<CustomerDao,Customer,String> implements ICustomerService {
}
