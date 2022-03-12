package com.tutorial.crud.service;

import com.tutorial.crud.dto.FechasDto;
import com.tutorial.crud.entity.Gastos;
import com.tutorial.crud.repository.GastosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class GastosService {
    @Autowired
    GastosRepository gastosRepository;

    public Optional<List<Gastos>> list(){
        return gastosRepository.findAllByOrderByFchPagoDesc();
    }
    public Optional<List<Gastos>> listSumYear(){
        return gastosRepository.findAllSum();
    }
    public Optional<List<Gastos>> listSumMonth(){
        return gastosRepository.findAllMonth();
    }
    public Optional<Gastos> getOne(int id){
        return gastosRepository.findById(id);
    }

    public Optional<List<Gastos>> getByFechas(FechasDto nombre){
        return gastosRepository.findAllByFchPagoBetween(nombre.getFrom(),nombre.getUntil());
    }
    public Optional<List<Gastos>> getByContains(String nombre){
        return gastosRepository.findByTipoEmpleadoContainsOrDescripcionContains(nombre,nombre);
    }
    public void  save(Gastos producto){
        gastosRepository.save(producto);
    }

    public void delete(int id){
        gastosRepository.deleteById(id);
    }

}
