package com.tutorial.crud.controller;

import com.tutorial.crud.dto.EstadisticasCobradorDto;
import com.tutorial.crud.dto.FechasDto;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.dto.Pagos;
import com.tutorial.crud.dto.RegistrarPagos;
import com.tutorial.crud.entity.Clientes;
import com.tutorial.crud.entity.Inventario;
import com.tutorial.crud.entity.PagosClientes;
import com.tutorial.crud.entity.Vendedor;
import com.tutorial.crud.service.ClienteService;
import com.tutorial.crud.service.PagosClientesService;
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
@RequestMapping("/pagosClientes")
@CrossOrigin(origins = "*")
public class PagosClientesControler {

    @Autowired
    PagosClientesService pagosClientesService;

    @Autowired
    ClienteService clienteService;


    @GetMapping("/lista")
    public ResponseEntity<List<PagosClientes>> list(){
        Optional<List<PagosClientes>> list = Optional.ofNullable(pagosClientesService.list());
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<PagosClientes> getById(@PathVariable("id") int id){
        if(pagosClientesService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        PagosClientes producto = pagosClientesService.getOne(id).get();
        return new ResponseEntity(producto, HttpStatus.OK);
    }
    @PostMapping("/detailsEstado/{estado}")
    public ResponseEntity<List<PagosClientes>> getByVendedorAndEstado(@RequestBody PagosClientes fechasDto, @PathVariable("estado") String id ){
        Optional<List<PagosClientes>> list = pagosClientesService.getByVendedorAndEstado(fechasDto.getCobrador(),fechasDto.getMovimiento());
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/cliente")
    public ResponseEntity<List<PagosClientes>> getByVendedorAndEstado(@RequestBody Clientes fechasDto){
        Optional<List<PagosClientes>> list = pagosClientesService.getByClientes(fechasDto);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PostMapping("/pagos")
    public ResponseEntity<Pagos> getByVendedorByTipo(@RequestBody FechasDto fechasDto ){
        Pagos list = pagosClientesService.getPagos(fechasDto);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/estadisticasCobrador")
    public ResponseEntity<EstadisticasCobradorDto> getEstadisticas(@RequestBody FechasDto fechasDto ){
        List<EstadisticasCobradorDto> list = pagosClientesService.getEstadisticas(fechasDto);
        return new ResponseEntity(list, HttpStatus.OK);
    }
    @PostMapping("/pagosSinDeducion")
    public ResponseEntity<RegistrarPagos> savePagosSinDeducion(@RequestBody RegistrarPagos fechasDto ){
        RegistrarPagos registrarPagos = pagosClientesService.savePagosSinDeducion(fechasDto);
        return new ResponseEntity(registrarPagos, HttpStatus.OK);
    }
    @PostMapping("/detailsFch")
    public ResponseEntity<List<PagosClientes>> getByFechas(@RequestBody FechasDto fechasDto){
        if(fechasDto.getFrom() == null || fechasDto.getUntil() == null)
            return new ResponseEntity(new Mensaje("las fechas son obligatorias"), HttpStatus.BAD_REQUEST);
        Optional<List<PagosClientes>> list = pagosClientesService.getByFechas(fechasDto);
        return new ResponseEntity(list, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody PagosClientes clientes){
        if(clienteService.findByCodigo(clientes.getCliente().getCodigo()) == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        pagosClientesService.save(clientes);
        return new ResponseEntity(new Mensaje("producto creado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody PagosClientes clientes){
        if(pagosClientesService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        pagosClientesService.update(clientes);
        return new ResponseEntity(new Mensaje("producto actualizado"), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(pagosClientesService.getOne(id).get() == null)
            return new ResponseEntity(new Mensaje("no existe"), HttpStatus.NOT_FOUND);
        pagosClientesService.delete(id);
        return new ResponseEntity(new Mensaje("producto eliminado"), HttpStatus.OK);
    }
}
