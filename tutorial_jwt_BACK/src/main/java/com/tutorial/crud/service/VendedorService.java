package com.tutorial.crud.service;

import com.tutorial.crud.entity.Vendedor;
import com.tutorial.crud.repository.VendedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VendedorService {

    @Autowired
    VendedorRepository vendedorRepository;

    public List<Vendedor> list(){
        return (List<Vendedor>) vendedorRepository.findAll();
    }

    public Optional<Vendedor> getOne(int id){
        return vendedorRepository.findById(id);
    }

    public void  save(Vendedor producto){
        vendedorRepository.save(producto);
    }

    public void delete(int id){
        vendedorRepository.deleteById(id);
    }

    public boolean existsByIdentificacion(String id){
        return vendedorRepository.existsByIdentificacion(id);
    }
    public boolean existsById(int id){
        return vendedorRepository.existsById(id);
    }
    public Optional<List<Vendedor>> getByContains(String nombre){
        return vendedorRepository.findByNombreContainsOrIdentificacionContains(nombre,nombre);
    }
}
