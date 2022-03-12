package com.tutorial.crud.controller;

import com.tutorial.crud.dto.FechasDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.Pagos;
import com.tutorial.crud.dto.RegistrarPagos;
import com.tutorial.crud.entity.Inventario;
import com.tutorial.crud.entity.Vendedor;
import com.tutorial.crud.service.InventarioService;
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
@RequestMapping("/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {
    @Autowired
    InventarioService inventarioService;

    @GetMapping("/lista")
    public ResponseEntity<List<Inventario>> list(){
        Optional<List<Inventario>> list = Optional.ofNullable(inventarioService.list());
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<Inventario> getById(@PathVariable("id") int id){
        if(inventarioService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        Inventario producto = inventarioService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }
    @PostMapping("/detailsEstado/{estado}")
    public ResponseEntity<List<Inventario>> getByVendedorAndEstado(@RequestBody Vendedor fechasDto, @PathVariable("estado") String id ){
        Optional<List<Inventario>> list = inventarioService.getByVendedorAndEstado(fechasDto,id);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/detailsEstadoNot/{estado}")
    public ResponseEntity<List<Inventario>> getByVendedorAndEstadoIsNot(@RequestBody Vendedor fechasDto, @PathVariable("estado") String id ){
        Optional<List<Inventario>> list = inventarioService.getByVendedorAndEstadoIsNot(fechasDto,id);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Inventario>> getByVendedorByEstado( @PathVariable("estado") String id ){
        Optional<List<Inventario>> list = inventarioService.getByVendedorByEstado(id);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @GetMapping("/tipo/{estado}")
    public ResponseEntity<List<Inventario>> getByVendedorByTipo( @PathVariable("estado") String id ){
        Optional<List<Inventario>> list = inventarioService.getByTipoMovimiento(id);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/pagos")
    public ResponseEntity<Pagos> getByVendedorByTipo(@RequestBody FechasDto fechasDto ){
        Pagos list = inventarioService.getPagos(fechasDto);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/pagosDeducion")
    public ResponseEntity<RegistrarPagos> savePagosDeducion(@RequestBody RegistrarPagos fechasDto ){
        RegistrarPagos registrarPagos = inventarioService.savePagosDeducion(fechasDto);
        return new ResponseEntity(registrarPagos, HttpStatus.OK);
    }
    @PostMapping("/pagosSinDeducion")
    public ResponseEntity<RegistrarPagos> savePagosSinDeducion(@RequestBody RegistrarPagos fechasDto ){
        RegistrarPagos registrarPagos = inventarioService.savePagosSinDeducion(fechasDto);
        return new ResponseEntity(registrarPagos, HttpStatus.OK);
    }
    @PostMapping("/detailsFch")
    public ResponseEntity<List<Inventario>> getByFechas(@RequestBody FechasDto fechasDto){
        if(fechasDto.getFrom() == null || fechasDto.getUntil() == null)
            return new ResponseEntity(new Mensaje("las fechas son obligatorias"), HttpStatus.BAD_REQUEST);
        Optional<List<Inventario>> list = inventarioService.getByFechas(fechasDto);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody Inventario clientes){
        inventarioService.save(clientes);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody Inventario clientes){
        if(inventarioService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        inventarioService.update(clientes);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(inventarioService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        inventarioService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
}
