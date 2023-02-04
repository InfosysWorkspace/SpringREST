package api;

import com.infy.dto.CustomerDTO;
import com.infy.exception.InfyBankException;
import com.infy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/infyBank")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/customers")
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() throws InfyBankException{
        List<CustomerDTO> customerList = customerService.getAllCustomers();
        return new ResponseEntity<>(customerList, HttpStatus.OK);
    }

    @GetMapping(value ="/customers/{customerId}")
    public ResponseEntity<CustomerDTO> getCustomer(@PathVariable Integer customerId) throws InfyBankException{
        CustomerDTO customerDTO = customerService.getCustomer(customerId);
        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }
}