package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Clientes;
import com.tutorial.crud.entity.Cobradores;
import com.tutorial.crud.entity.PagosClientes;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface PagosClientesRepository extends CrudRepository<PagosClientes, Integer> {
    Optional<List<PagosClientes>> findAllByMovimientoIsNotOrderByFchPagoDesc(String movimeinto);
    Optional<List<PagosClientes>> findByClienteOrderByFchPagoDesc(Clientes codigo);
    Optional<List<PagosClientes>> findByCobrador(String codigo);
    Optional<List<PagosClientes>> findAllByFchPagoBetween(Date desde, Date hasta);
    Optional<List<PagosClientes>> findAllByFchPagoBeforeAndCobrador(Date desde, Cobradores cobradores);
    Optional<List<PagosClientes>> findAllByFchPagoBetweenAndCobrador(Date desde, Date hasta, Cobradores cobradores);
    Optional<List<PagosClientes>> findAllByFchPagoAndCobrador(Date desde, Cobradores cobradores);
    Optional<List<PagosClientes>> findByCobradorAndMovimiento(Cobradores cobradores, String movimiento);
}
