package sd.a2.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sd.a2.server.dto.CustomerDto;
import sd.a2.server.dto.NewCustomerDto;
import sd.a2.server.service.CustomerService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService service) {
        this.customerService = service;
    }

    @PostMapping
    public ResponseEntity<CustomerDto> addCustomer(@RequestBody NewCustomerDto newCustomer) {
        try {
            CustomerDto dto = customerService.addCustomer(newCustomer);
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
