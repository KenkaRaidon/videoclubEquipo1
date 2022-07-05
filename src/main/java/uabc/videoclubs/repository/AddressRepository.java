package uabc.videoclubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uabc.videoclubs.entities.EAddress;

@Repository
public interface AddressRepository extends JpaRepository<EAddress, Integer>{
    
}
