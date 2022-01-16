package com.dev.logistica.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dev.logistica.api.assembler.OcorrenciaAssembler;
import com.dev.logistica.api.model.OcorrenciaModel;
import com.dev.logistica.api.model.input.OcorrenciaInput;
import com.dev.logistica.domain.model.Entrega;
import com.dev.logistica.domain.service.BuscarEntregaService;
import com.dev.logistica.domain.service.RegistroOcorrenciaService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private BuscarEntregaService buscarEntregaService;
	private OcorrenciaAssembler ocorrenciaAssembler;
	private RegistroOcorrenciaService registroOcorrenciaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaModel registrar(@PathVariable Long entregaId,
			@Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		var ocorrencia = registroOcorrenciaService.registrar(entregaId, ocorrenciaInput.getDescricao());

		return ocorrenciaAssembler.toMOdel(ocorrencia);
	}
	
	@GetMapping
	public List<OcorrenciaModel> listar(@PathVariable Long entregaId) {
		Entrega entrega = buscarEntregaService.buscar(entregaId);
		return ocorrenciaAssembler.toCollectionModel(entrega.getOcorrencias());
	}
	
}
