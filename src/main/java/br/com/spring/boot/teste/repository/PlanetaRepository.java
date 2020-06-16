package br.com.spring.boot.teste.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.spring.boot.teste.domain.Planeta;

public interface PlanetaRepository extends MongoRepository<Planeta, String> {

	List<Planeta> findByNome(String nome);

}
