package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Inventario;
import com.tutorial.crud.entity.Vendedor;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface InventarioRepository extends CrudRepository<Inventario, Integer> {
    Optional<List<Inventario>> findAllByVendedorAndEstado(Vendedor vendedor, String estado);
    Optional<List<Inventario>> findAllByVendedorAndTipoMovimientoIsNot(Vendedor vendedor, String estado);
    Optional<List<Inventario>> findAllByEstado(String estado);
    Optional<List<Inventario>> findAllByTipoMovimiento(String movimiento);
    Optional<List<Inventario>> findAllByFchEntregaBetween(Date desde, Date hasta);
    Optional<List<Inventario>> findAllByFchEntregaBetweenAndVendedor(Date desde, Date hasta, Vendedor vendedor);
    Optional<List<Inventario>> findAllByFchEntregaBeforeAndVendedor(Date desde,  Vendedor vendedor);
}
