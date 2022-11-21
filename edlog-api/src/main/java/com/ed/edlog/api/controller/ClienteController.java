package com.ed.edlog.api.controller;

import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ed.edlog.api.domain.model.Cliente;
import com.ed.edlog.api.repository.ClienteRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private ClienteRepository clienteRepository;


	@GetMapping
	public List<Cliente> listar() {
		return clienteRepository.findAll();
	}

	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId){
	return clienteRepository.findById(clienteId)
			.map(cliente -> ResponseEntity.ok(cliente))
			.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Cliente adicionar(@RequestBody Cliente cliente){
		return clienteRepository.save(cliente);
	}

	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId, @RequestBody Cliente cliente){
		if (!clienteRepository.existsById(clienteId)){
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente = clienteRepository.save(cliente);
		return ResponseEntity.ok(cliente);
	}

	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		if (!clienteRepository.existsById(clienteId)){
			return  ResponseEntity.notFound().build();
		}
		clienteRepository.deleteById(clienteId);
		return ResponseEntity.noContent().build();

	}

}
