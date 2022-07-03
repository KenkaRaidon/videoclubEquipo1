package uabc.videoclubs.services;

import java.util.List;
import java.util.Optional;

import uabc.videoclubs.entities.Customer;
import uabc.videoclubs.entities.CustomerIndex;

public interface ICustomerService {
    public List<CustomerIndex> getCustomers();
    public Optional<Customer> findById(Integer id);
}
