package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Clientes;
import com.tutorial.crud.entity.Rutas;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import java.util.Optional;

public interface ClientesRepository  extends CrudRepository<Clientes, Integer> {
    Optional<Clientes> findByCodigo(String codigo);
    Optional<List<Clientes>> findAllByRutas(Rutas rutas);
    Optional<List<Clientes>> findByCodigoContainsOrNombreContains(String codigo, String nombre);
    @Query(value = "SELECT `CD_RUTA` AS rutas, SUM(`CD_DEUDA`-`CD_SALDO`) AS abonado, SUM(`CD_DEUDA`) AS total FROM tins_clientes GROUP BY `CD_RUTA`", nativeQuery = true)
    Optional<List<Object[]>> findSumrutas();
    boolean existsByCodigo(String codigo);
}
