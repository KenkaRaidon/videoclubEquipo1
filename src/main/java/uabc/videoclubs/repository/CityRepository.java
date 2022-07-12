package uabc.videoclubs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import uabc.videoclubs.entities.City;

public interface CityRepository extends JpaRepository<City, Integer>{
    @Query(value ="select * from city c where c.country_id=?1" , nativeQuery=true)
    List<City> findCityByCountryId(Integer countryId);
}
