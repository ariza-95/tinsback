package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    Optional<Producto> findByNombre(String nombre);
    Optional<Producto> findByDescripcion(String nombre);
    List<Producto> findByNombreContainsOrDescripcionContains(String nombre, String descripcion);

    boolean existsByNombre(String nombre);
    boolean existsByDescripcion(String desc);
}
