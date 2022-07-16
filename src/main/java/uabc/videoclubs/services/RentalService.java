package uabc.videoclubs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.ReturnIndex;

import uabc.videoclubs.entities.Rental;
import uabc.videoclubs.repository.RentalRepository;
import java.util.List;

@Service
public class RentalService implements IRentalService {
	
	@Autowired
	private RentalRepository rentalRepository;
	
	@Override
	public List<ReturnIndex> returns(Integer id){
		return rentalRepository.returns(id);
	}
	
	@Override
	public Optional<Rental> findById(Integer id){
		return rentalRepository.findById(id);
	}
	public List<Rental> findAll(){
        return rentalRepository.findAll();
    }
	
	
	public Rental getbyinventory(Integer inv) {
		List<Rental> Lista=rentalRepository.getbyInv(inv);
		for(Rental var:Lista) {
			if(var.getReturnDate()==null && var.getInventoryId()!=null)
				return var;
			
		}
		return null;
		
	}
	
	public Rental saveRental(Rental inv) {
		return rentalRepository.save(inv);
	}
}
