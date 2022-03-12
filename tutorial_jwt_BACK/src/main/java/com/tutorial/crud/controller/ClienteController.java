package com.tutorial.crud.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Clientes;
import com.tutorial.crud.service.ClienteService;
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
@RequestMapping("/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/lista")
    public ResponseEntity<List<Clientes>> list(){
        List<Clientes> list = clienteService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/listaRutas")
    public ResponseEntity<List<Object[]>> listRutas(){
        List<Object[]> list = clienteService.listSumRutas();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/listaContains/{id}")
    public ResponseEntity<List<Clientes>> listContains(@PathVariable("id") String id){
        Optional<List<Clientes>> list = clienteService.listContains(id);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/findByRuta/{id}")
    public ResponseEntity<List<Clientes>> listContains(@PathVariable("id") int id){
        Optional<List<Clientes>> list = clienteService.findByRuta(id);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Clientes> getById(@PathVariable("id") int id){
        if(clienteService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Clientes producto = clienteService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @GetMapping("/detailName/{id}")
    public ResponseEntity<Clientes> getByCodigo(@PathVariable("id") String  id){
        if(clienteService.findByCodigo(id) == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Clientes producto = clienteService.findByCodigo(id);
        return new ResponseEntity(producto, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Clientes clientes){
        if(StringUtils.isBlank(clientes.getCodigo()))
            return new ResponseEntity(new Mensaje("el codigo es obligatorio"), HttpStatus.BAD_REQUEST);
        if(clienteService.existsByCodigo(clientes.getCodigo()))
            return new ResponseEntity(new Mensaje("el codigo ya existe"), HttpStatus.BAD_REQUEST);
        clienteService.save(clientes);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Clientes clientes){
        if(clienteService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        clienteService.save(clientes);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(clienteService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        clienteService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }

}
