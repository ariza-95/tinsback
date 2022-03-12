package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Cobradores;
import com.tutorial.crud.service.CobradoresService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cobradores")
@CrossOrigin(origins = "*")
public class CobradoresController {
    @Autowired
    CobradoresService cobradoresService;
    @GetMapping("/lista")
    public ResponseEntity<List<Cobradores>> list(){
        List<Cobradores> list = cobradoresService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/listaContains/{id}")
    public ResponseEntity<List<Cobradores>> listContains(@PathVariable("id") String id){
        Optional<List<Cobradores>> list = cobradoresService.listContains(id);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Cobradores> getById(@PathVariable("id") int id){
        if(cobradoresService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Cobradores producto = cobradoresService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }


    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Cobradores clientes){
        if(StringUtils.isBlank(clientes.getCedula()))
            return new ResponseEntity(new Mensaje("la cedula es obligatorio"), HttpStatus.BAD_REQUEST);
        cobradoresService.save(clientes);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Cobradores clientes){
        if(cobradoresService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        cobradoresService.save(clientes);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(cobradoresService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        cobradoresService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
}
