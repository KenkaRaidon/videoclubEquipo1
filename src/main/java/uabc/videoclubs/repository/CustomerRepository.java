package uabc.videoclubs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uabc.videoclubs.entities.Customer;
import uabc.videoclubs.entities.CustomerIndex;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
    @Query(nativeQuery = true)
    public List<CustomerIndex> getCustomers();
    public Optional<Customer> findById(Integer id);
}
