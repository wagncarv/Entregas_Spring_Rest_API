package com.dev.logistica.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dev.logistica.api.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class RegistroOcorrenciaService {

	private BuscarEntregaService buscarEntregaService;

	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		var entrega = buscarEntregaService.buscar(entregaId);
		return entrega.adicionarOcorrencia(descricao);
	}
}
