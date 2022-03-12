package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Cobradores;
import com.tutorial.crud.entity.Rutas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CobradoresRepository extends CrudRepository<Cobradores, Integer> {
    Optional<List<Cobradores>> findAllByRutas(Rutas rutas);
    Optional<List<Cobradores>> findByNombreContainsOrCedulaContains(String codigo, String nombre);

}
