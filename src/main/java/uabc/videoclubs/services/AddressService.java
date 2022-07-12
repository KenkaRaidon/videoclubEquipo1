package uabc.videoclubs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.EAddress;
import uabc.videoclubs.repository.AddressRepository;

@Service
public class AddressService {
    @Autowired
    AddressRepository addressRepository;

    public EAddress Save(EAddress address){
        return addressRepository.save(address);
    }
    public Optional<EAddress> getAll(Integer id){
        return addressRepository.findById(id);
    }
}
