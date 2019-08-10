package com.cxf.elkcustomerservice.controller;

import com.cxf.common.dto.RespDTO;
import com.cxf.elkcustomerservice.pojo.Customer;
import com.cxf.elkcustomerservice.service.ICustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author always_on_the_way
 * @date 2019-07-22
 */
@RestController //此注解表示统一返回json数据格式
public class CustomerController {

    private ICustomerService customerService;

    public CustomerController(ICustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public RespDTO getCustomerById(String id){
        Customer customer = customerService.selectById(id);
        return RespDTO.onSuc(customer);
    }

    @GetMapping("/customers")
    public RespDTO getCustomerAll(){
        List<Customer> list = customerService.selectAll();
        return RespDTO.onSuc(list);
    }

}
