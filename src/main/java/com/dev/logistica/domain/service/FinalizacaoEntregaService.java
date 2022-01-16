package com.dev.logistica.domain.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.dev.logistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class FinalizacaoEntregaService {

	private EntregaRepository entregaRepository;
	private BuscarEntregaService buscarEntregaService;
	
	@Transactional
	public void finalizar(Long entregaId) {
		var entrega = buscarEntregaService.buscar(entregaId);
		entrega.finalizar();
		entregaRepository.save(entrega);
	}
}
