package uabc.videoclubs.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.Country;
import uabc.videoclubs.repository.CountryRepository;

@Service
public class CountryService {
    @Autowired
    private CountryRepository countryRepository;

    public List<Country> findAll(){
        return countryRepository.findAll();
    }
    
}
