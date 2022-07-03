package uabc.videoclubs.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uabc.videoclubs.entities.Language;
import uabc.videoclubs.repository.LanguageRepository;

@Service
public class LanguageService implements ILanguageService{

	@Autowired
	private LanguageRepository languageRepository;
	
	@Override
	public Optional<Language> findById(Integer id) {
		return languageRepository.findById(id);
	}

}
