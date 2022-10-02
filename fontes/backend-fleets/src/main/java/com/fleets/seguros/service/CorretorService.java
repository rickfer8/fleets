package com.fleets.seguros.service;

import org.springframework.stereotype.Service;

import com.fleets.seguros.repository.CorretorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@RequiredArgsConstructor
public class CorretorService {
	
	private final CorretorRepository repository;

}
