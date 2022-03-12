package com.tutorial.crud.service;

import com.tutorial.crud.entity.Clientes;
import com.tutorial.crud.entity.Cobradores;
import com.tutorial.crud.entity.Rutas;
import com.tutorial.crud.repository.ClientesRepository;
import com.tutorial.crud.repository.CobradoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CobradoresService {
    @Autowired
    CobradoresRepository cobradoresRepository;
    @Autowired
    RutasService rutasService;
    public List<Cobradores> list(){
        return (List<Cobradores>) cobradoresRepository.findAll();
    }
    public Optional<List<Cobradores>> listContains(String data){
        return cobradoresRepository.findByNombreContainsOrCedulaContains(data,data);
    }
    public Optional<List<Cobradores>> findAllByRutas(Rutas rutas){
        return cobradoresRepository.findAllByRutas(rutas);
    }
    public Optional<Cobradores> getOne(int id){
        return cobradoresRepository.findById(id);
    }

    public void  save(Cobradores clientes){

        cobradoresRepository.save(clientes);
    }

    public void delete(int id){
        cobradoresRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return cobradoresRepository.existsById(id);
    }
}
