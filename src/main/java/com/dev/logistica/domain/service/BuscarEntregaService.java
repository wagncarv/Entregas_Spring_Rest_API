package com.dev.logistica.domain.service;

import org.springframework.stereotype.Service;

import com.dev.logistica.domain.exception.EntidadeNaoEncontradaException;
import com.dev.logistica.domain.model.Entrega;
import com.dev.logistica.domain.repository.EntregaRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BuscarEntregaService {
	private EntregaRepository entregaRepository;

	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Entrega não encontrada"));
	}

}
