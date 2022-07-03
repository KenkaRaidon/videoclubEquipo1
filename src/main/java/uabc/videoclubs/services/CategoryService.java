package uabc.videoclubs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.Category;
import uabc.videoclubs.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	
	public Optional<Category> findById(Integer id){
		return categoryRepository.findById(id);
	}
}
