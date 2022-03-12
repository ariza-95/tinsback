package com.tutorial.crud.controller;

import com.tutorial.crud.dto.FechasDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Gastos;
import com.tutorial.crud.service.GastosService;
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
@RequestMapping("/gastos")
@CrossOrigin(origins = "*")
public class GastosController {
    @Autowired
    GastosService gastosService;

    @GetMapping("/lista")
    public ResponseEntity<List<Gastos>> list(){
        Optional<List<Gastos>> list = gastosService.list();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/listaContains/{id}")
    public ResponseEntity<List<Gastos>> listContains(@PathVariable("id") String id){
        Optional<List<Gastos>> list = gastosService.getByContains(id);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/listaSumYear")
    public ResponseEntity<List<Gastos>> listContainsYear(){
        Optional<List<Gastos>> list = gastosService.listSumYear();
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/listaSumMonth")
    public ResponseEntity<List<Gastos>> listContainsMonth(){
        Optional<List<Gastos>> list = gastosService.listSumMonth();
        return new ResponseEntity(list, HttpStatus.OK);
    }



    @GetMapping("/detail/{id}")
    public ResponseEntity<Gastos> getById(@PathVariable("id") int id){
        if(gastosService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Gastos producto = gastosService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }
    @GetMapping("/detailsFch")
    public ResponseEntity<List<Gastos>> getByFechas(@RequestBody FechasDto fechasDto){
        if(fechasDto.getFrom() == null || fechasDto.getUntil() == null)
            return new ResponseEntity(new Mensaje("las fechas son obligatorias"), HttpStatus.BAD_REQUEST);
        Optional<List<Gastos>> list = gastosService.getByFechas(fechasDto);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Gastos clientes){
        gastosService.save(clientes);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Gastos clientes){
        if(gastosService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        gastosService.save(clientes);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(gastosService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        gastosService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
}
