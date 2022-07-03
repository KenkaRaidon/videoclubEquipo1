package uabc.videoclubs.services;

import java.util.List;
import java.util.Optional;

import uabc.videoclubs.entities.Rental;
import uabc.videoclubs.entities.ReturnIndex;

public interface IRentalService {
	public List<ReturnIndex> returns(Integer id);
	public Optional<Rental> findById(Integer rentalId);
}
