package com.tutorial.crud.service;

import com.tutorial.crud.entity.Clientes;
import com.tutorial.crud.entity.Producto;
import com.tutorial.crud.entity.Rutas;
import com.tutorial.crud.repository.ClientesRepository;
import com.tutorial.crud.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClienteService {
    @Autowired
    ProductoRepository productoRepository;
    @Autowired
    ClientesRepository clientesRepository;
    @Autowired
    RutasService rutasService;

    public List<Clientes> list(){
        return (List<Clientes>) clientesRepository.findAll();
    }
    public List<Object[]> listSumRutas()  {

        return (List<Object[]>) clientesRepository.findSumrutas().get();
    }
    public Optional<List<Clientes>> listContains(String data){
        return clientesRepository.findByCodigoContainsOrNombreContains(data,data);
    }
    public Clientes findByCodigo(String codigo){
        return clientesRepository.findByCodigo(codigo).get();
    }
    public Optional<List<Clientes>> findAllByRutas(Rutas rutas){
        return clientesRepository.findAllByRutas(rutas);
    }
    public Optional<Clientes> getOne(int id){
        return clientesRepository.findById(id);
    }

    public void  save(Clientes clientes){

        clientesRepository.save(clientes);
    }

    public void delete(int id){
        clientesRepository.deleteById(id);
    }

    public boolean existsByCodigo(String id){
        return clientesRepository.existsByCodigo(id);
    }

    public Clientes actualizarDeuda(Producto producto,int cantidad,String codigo, int precio, String estado){
            Clientes clientes = clientesRepository.findByCodigo(codigo).get();
            if (clientes != null){
                if(estado.equals("RETIRADO")){
                    producto.setCantidadDisponible(producto.getCantidadDisponible()+cantidad);
                    productoRepository.save(producto);
                    if(clientes.getDeuda() > 0 && clientes.getDeuda() >= precio){
                        clientes.setDeuda(clientes.getDeuda() - (precio));
                        clientes.setFchVenta(new Date());
                    }else{
                        clientes.setDeuda(0);
                    }

                }else {
                    producto.setCantidadDisponible(producto.getCantidadDisponible()-cantidad);
                    productoRepository.save(producto);
                    clientes.setDeuda(clientes.getDeuda() + (precio));
                    clientes.setFchVenta(new Date());
                }
                return clientesRepository.save(clientes);
            }
        return null;
    }
    public Clientes actualizarSaldo(String codigo, float abono){
            Clientes clientes = clientesRepository.findByCodigo(codigo).get();
            if (clientes != null){
                clientes.setSaldo(clientes.getSaldo()-abono);
                return clientesRepository.save(clientes);
            }
        return null;
    }
    public Clientes actualizarSaldoUpdate(String codigo, float abono, float oldAbono){
        Clientes clientes = clientesRepository.findByCodigo(codigo).get();
        if (clientes != null){
            clientes.setSaldo(clientes.getSaldo()+oldAbono);
            clientes.setSaldo(clientes.getSaldo()-abono);
            return clientesRepository.save(clientes);
        }
        return null;
    }
    public Optional<List<Clientes>> findByRuta(int id){
        Rutas rutas = rutasService.getOne(id).get();
        return clientesRepository.findAllByRutas(rutas);
    }

}
