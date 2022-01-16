package com.dev.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.logistica.api.assembler.EntregaAssembler;
import com.dev.logistica.api.model.EntregaModel;
import com.dev.logistica.api.model.input.EntregaInput;
import com.dev.logistica.domain.repository.EntregaRepository;
import com.dev.logistica.domain.service.FinalizacaoEntregaService;
import com.dev.logistica.domain.service.SolicitacaoEntregaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas")
public class EntregaController {

	private EntregaAssembler EntregaAssembler;
	private EntregaRepository entregaRepository;
	private FinalizacaoEntregaService finalizacaoEntregaService;
	private SolicitacaoEntregaService solicitacaoEntregaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		var novaEntrega = EntregaAssembler.toEntity(entregaInput);
		var entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);
		return EntregaAssembler.toModel(entregaSolicitada);
	}

	@GetMapping
	public List<EntregaModel> listar() {
		var entregas = entregaRepository.findAll();
		return EntregaAssembler.toCollectionModel(entregas);
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId)
				.map(entrega -> ResponseEntity.ok(EntregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);
	}

}
