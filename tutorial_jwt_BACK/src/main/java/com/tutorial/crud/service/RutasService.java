package com.tutorial.crud.service;

import com.tutorial.crud.entity.Rutas;
import com.tutorial.crud.repository.RutasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class RutasService {
    @Autowired
    RutasRepository rutasRepository;
    public List<Rutas> list(){
        return (List<Rutas>) rutasRepository.findAll();
    }

    public Optional<Rutas> getOne(int id){
        return rutasRepository.findById(id);
    }

    public void  save(Rutas ruta){
        rutasRepository.save(ruta);
    }

    public void delete(int id){
        rutasRepository.deleteById(id);
    }

}

