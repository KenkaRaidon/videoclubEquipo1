package uabc.videoclubs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.City;
import uabc.videoclubs.repository.CityRepository;

@Service
public class CityService {
    @Autowired
    CityRepository cityRepository;
    
    public List<City> findCityByCountryId(Integer countryId){
        return cityRepository.findCityByCountryId(countryId);
    }
}
