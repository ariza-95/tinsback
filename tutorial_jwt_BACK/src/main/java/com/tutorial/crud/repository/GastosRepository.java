package com.tutorial.crud.repository;

import com.tutorial.crud.entity.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface GastosRepository extends CrudRepository<Gastos, Integer>{
    Optional<List<Gastos>> findAllByOrderByFchPagoDesc();
    Optional<List<Gastos>> findAllByFchPagoBetween(Date desde, Date hasta);
    Optional<List<Gastos>> findByTipoEmpleadoContainsOrDescripcionContains(String empleado,String desc);
    @Query("SELECT tipoEmpleado,YEAR(fchPago), SUM(precio) FROM Gastos GROUP BY YEAR(fchPago), tipoEmpleado ORDER BY fchPago DESC")
    Optional<List<Gastos>> findAllSum();
    @Query("SELECT tipoEmpleado,MONTH(fchPago), SUM(precio) FROM Gastos GROUP BY MONTH(fchPago), tipoEmpleado ORDER BY fchPago DESC")
    Optional<List<Gastos>> findAllMonth();
}
