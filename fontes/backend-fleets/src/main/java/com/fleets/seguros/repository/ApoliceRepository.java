package com.fleets.seguros.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fleets.seguros.model.Apolice;

@Repository
public interface ApoliceRepository extends JpaRepository<Apolice, Long> {

}
