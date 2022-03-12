package com.devatila.ms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.devatila.ms.model.Service;
import com.devatila.ms.repository.ServiceRepository;

import net.bytebuddy.asm.Advice.Return;

@RestController
@RequestMapping("api")
public class ServiceController {
	
	@Autowired
	ServiceRepository sRepository;
	
	@GetMapping("/servico")
	public List<Service> servicos() {
		return sRepository.findAll();
	}
	
	@GetMapping({"/servico/{id}"})
	public Service buscarServico(@PathVariable long id) {
		return sRepository.findById(id).orElseThrow( ()-> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@PostMapping("/servico")
	public Service criarServico(@RequestBody Service servico) {
		return sRepository.save(servico);
	}
	
	@PutMapping({"/servico/{id}"})
	public void atualizar(@PathVariable Long id, @RequestBody Service servicoAtualizado) {
		sRepository.findById(id)
		.map( servico -> {
			servicoAtualizado.setId(servico.getId());
			return sRepository.save(servicoAtualizado); //Atualiza o resgistro se já existir
		})
		.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));
	}
	
	@DeleteMapping({"/servico/{id}"})
	public void deletar(@PathVariable long id) {
		sRepository.findById(id)
			.map( servico -> {
				sRepository.delete(servico);
				return Void.TYPE;
			})
			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Serviço não encontrado"));
		
	}

}
