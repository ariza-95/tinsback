package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Vendedor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface VendedorRepository extends CrudRepository<Vendedor, Integer> {
    boolean existsByIdentificacion(String nombre);
    Optional<List<Vendedor>> findByNombreContainsOrIdentificacionContains(String empleado, String desc);
}
