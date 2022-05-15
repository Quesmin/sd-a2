package sd.a2.server.service;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sd.a2.server.repository.CustomerRepository;
import sd.a2.server.dto.CustomerDto;
import sd.a2.server.dto.NewCustomerDto;
import sd.a2.server.mapper.CustomerMapper;

@Service
public class CustomerService {
    private final CustomerRepository customerRepository;
    private Logger logger = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    public CustomerService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    /**
     * This is an utility function that checks whether the input string is a valid email or not.
     * @param str
     * @return
     */
    private boolean isValidEmail(String str) {
        logger.info("Checking if email is valid...");
        return str.matches("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");
    }

    /**
     * This method is trying to add a new customer to the current database.
     * @param newCustomer
     * @return
     * @throws Exception
     */
    public CustomerDto addCustomer(NewCustomerDto newCustomer) throws Exception {
        if (newCustomer.getEmail().isEmpty() ||
                newCustomer.getPassword().isEmpty() ||
                !isValidEmail(newCustomer.getEmail())) {
            logger.error("Invalid user credentials");
            throw new Exception("Invalid user credentials");
        }
        if (customerRepository.findCustomerByEmail(newCustomer.getEmail()).isPresent()) {
            logger.error("There is already an account with this email");
            throw new Exception("There is already an account with this email");
        }
        var entity = CustomerMapper.toEntity(newCustomer);

        entity.setPasswordHash(BCrypt.hashpw(entity.getPasswordHash(), BCrypt.gensalt()));
        var response = customerRepository.save(entity);
        logger.info("New customer was created.");
        return CustomerMapper.toDto(response);
    }

    public static void main(String[] args) {
        System.out.println(BCrypt.hashpw("123", BCrypt.gensalt()));
    }
}

