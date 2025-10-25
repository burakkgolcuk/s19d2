package com.workintech.s18d4.controller;

import com.workintech.s18d4.dto.CustomerResponse;
import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService){
        this.customerService = customerService;
    }

    @PostMapping
    public CustomerResponse save(@RequestBody Customer customer){
        Customer saved = customerService.save(customer);
        return new CustomerResponse(
                saved.getId(),
                saved.getEmail(),
                saved.getSalary()
        );
    }

    // Bonus olarak diğer CRUD'ları da ekleyelim (görev bunu istiyor ama test şimdilik kullanmıyor)

    @GetMapping
    public java.util.List<Customer> findAll(){
        return customerService.findAll();
    }

    @GetMapping("/{id}")
    public Customer find(@PathVariable Long id){
        return customerService.find(id);
    }

    @DeleteMapping("/{id}")
    public Customer remove(@PathVariable Long id){
        return customerService.delete(id);
    }
}
