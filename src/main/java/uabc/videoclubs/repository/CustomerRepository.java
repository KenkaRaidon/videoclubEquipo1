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
    
    @Query(value = "select * from customer c where c.customer_id=?1", nativeQuery = true)
    public Customer findMyCustomer(Integer id);
    
    
    @Query(value= "update customer c set c.activebool=?2 where c.customer_id=?1",nativeQuery = true)
    public Customer UpdateActive(Integer id,Boolean activo);
}
