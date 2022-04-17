package sd.a2.server.service;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.a2.server.repository.CustomerRepository;
import sd.a2.server.dto.CustomerDto;
import sd.a2.server.dto.NewCustomerDto;
import sd.a2.server.mapper.CustomerMapper;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    private boolean isValidEmail(String str) {
        return str.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    }

    public CustomerDto addCustomer(NewCustomerDto newCustomer) throws Exception {
        if (newCustomer.getEmail().isEmpty() ||
                newCustomer.getPassword().isEmpty() ||
                !isValidEmail(newCustomer.getEmail())) {
            throw new Exception("Invalid user credentials");
        }
        if (customerRepository.findCustomerByEmail(newCustomer.getEmail()).isPresent()) {
            throw new Exception("There is already an account with this email");
        }
        var entity = CustomerMapper.toEntity(newCustomer);

        entity.setPasswordHash(BCrypt.hashpw(entity.getPasswordHash(), BCrypt.gensalt()));
        var response = customerRepository.save(entity);
        return CustomerMapper.toDto(response);
    }
}
