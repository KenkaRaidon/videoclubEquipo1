package uabc.videoclubs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import uabc.videoclubs.entities.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{
    
}
