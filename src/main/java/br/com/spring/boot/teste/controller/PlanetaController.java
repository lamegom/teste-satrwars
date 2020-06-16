package br.com.spring.boot.teste.controller;

import static org.springframework.web.servlet.support.ServletUriComponentsBuilder.fromCurrentRequest;

import java.io.IOException;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.spring.boot.teste.domain.Planeta;
import br.com.spring.boot.teste.domain.Result;
import br.com.spring.boot.teste.service.PlanetaService;

@RequestMapping("/api/v1")
@RestController
public class PlanetaController {



		@Autowired
		PlanetaService planetaService;

		@PostMapping("/planetas")
		public ResponseEntity<?> createPlaneta(@RequestBody Planeta request) throws IOException {
			try {
				final Planeta response = planetaService.savePlaneta(request);
				final URI location = fromCurrentRequest().path("/{id}").buildAndExpand(response.getId()).toUri();
				return ResponseEntity.created(location).body(response);

			} catch(Exception x) {
				x.printStackTrace();
				return ResponseEntity.status(500).build();
			}
		}
		
		@GetMapping("/planetas")
		public ResponseEntity<?> getPlanetas() throws IOException {
			try {
				final List<Planeta> response = planetaService.getPlanetas();

				return ResponseEntity.ok(response);

			} catch(Exception x) {
				x.printStackTrace();
				return ResponseEntity.status(500).build();
			}
		}
		
		@GetMapping("/planetas-starwars")
		public ResponseEntity<?> getPlanetasStarWars() throws IOException {
			try {
				final List<Result> response = planetaService.getPlanetasStarWars();

				return ResponseEntity.ok(response);

			} catch(Exception x) {
				x.printStackTrace();
				return ResponseEntity.status(500).build();
			}
		}
		
		@GetMapping("/planetas/nome/{nome}")
		public ResponseEntity<?> getPlanetasByNome(@PathVariable("nome") String nome) throws IOException {
			try {
				final List<Planeta> response = planetaService.getPlanetaByNome(nome);

				return ResponseEntity.ok(response);

			} catch(Exception x) {
				x.printStackTrace();
				return ResponseEntity.status(500).build();
			}
		}
		
		@GetMapping("/planetas/id/{id}")
		public ResponseEntity<?> getPlanetasById(@PathVariable("id") String id) throws IOException {
			try {
				final Planeta response = planetaService.getPlanetaById(id);

				return ResponseEntity.ok(response);

			} catch(Exception x) {
				x.printStackTrace();
				return ResponseEntity.status(500).build();
			}
		}
		
		@PostMapping("/planetas/{id}")
		public ResponseEntity<?> removePlanetasById(@PathVariable("id") String id) throws IOException {
			try {
				planetaService.removePlanetaById(id);

				return ResponseEntity.accepted().build();

			} catch(Exception x) {
				x.printStackTrace();
				return ResponseEntity.status(500).build();
			}
		}

	}

