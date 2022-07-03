package uabc.videoclubs.services;

import java.util.Optional;

import uabc.videoclubs.entities.Language;

public interface ILanguageService {

	public Optional<Language> findById(Integer id);
}
