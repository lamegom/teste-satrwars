package br.com.spring.boot.teste.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.spring.boot.teste.domain.Planeta;
import br.com.spring.boot.teste.domain.Result;
import br.com.spring.boot.teste.domain.StarWars;
import br.com.spring.boot.teste.repository.PlanetaRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PlanetaService {
	
	@Autowired
	private PlanetaRepository planetaRepository;
	
	@Autowired
	private StarWarsService starWarsService;
	
	public Planeta savePlaneta(Planeta planeta) {
		
		StarWars starwars = starWarsService.getStarWarsByName(planeta.getNome());
		
		if (Objects.isNull(starwars)) {
			log.error("Movies quantity not found");
			planeta.setQtdeFilmes(0);
			
		} else {
		
			planeta.setQtdeFilmes(starwars.getResults().get(0).getFilms().size());
		}
		
		return planetaRepository.save(planeta);
		

		
	}
	
	public List<Planeta> getPlanetas(){
		
		return planetaRepository.findAll();
	}
	
	public List<Result> getPlanetasStarWars(){
		
		return starWarsService.getStarWars();
	}
	
	public List<Planeta> getPlanetaByNome(String nome){
		
		return planetaRepository.findByNome(nome);
	}
	
	public Planeta getPlanetaById(String id){
		
		Optional<Planeta> dbPlaneta = planetaRepository.findById(id);
		
		Planeta existingPlaneta = null;

		if(dbPlaneta.isPresent()) {
			existingPlaneta = dbPlaneta.get();

		}

		
		return existingPlaneta;
	}
	
	public void removePlanetaById(String id) throws Exception {
			
		planetaRepository.deleteById(id);

	}
	
	

}
