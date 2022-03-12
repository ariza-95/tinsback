package com.tutorial.crud.service;

import com.tutorial.crud.dto.FechasDto;
import com.tutorial.crud.dto.Pagos;
import com.tutorial.crud.dto.RegistrarPagos;
import com.tutorial.crud.entity.Clientes;
import com.tutorial.crud.entity.Gastos;
import com.tutorial.crud.entity.Inventario;
import com.tutorial.crud.entity.Vendedor;
import com.tutorial.crud.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventarioService {
    @Autowired
    InventarioRepository inventarioRepository;
    @Autowired
    ClienteService clienteService;
    @Autowired
    ProductoService productoService;
    @Autowired
    VendedorService vendedorService;
    @Autowired
    GastosService gastosService;

    public List<Inventario> list(){
        return (List<Inventario>) inventarioRepository.findAll();
    }

    public Optional<Inventario> getOne(int id){
        return inventarioRepository.findById(id);
    }

    public Optional<List<Inventario>> getByVendedorAndEstado(Vendedor vendedor, String nombre){
        return inventarioRepository.findAllByVendedorAndEstado(vendedor,nombre);
    }
    public Optional<List<Inventario>> getByVendedorAndEstadoIsNot(Vendedor vendedor, String nombre){
        return inventarioRepository.findAllByVendedorAndTipoMovimientoIsNot(vendedor,nombre);
    }
    public Optional<List<Inventario>> getByVendedorByEstado(String nombre){
        return inventarioRepository.findAllByEstado(nombre);
    }
    public Optional<List<Inventario>> getByTipoMovimiento(String nombre){
        return inventarioRepository.findAllByTipoMovimiento(nombre);
    }
    public Optional<List<Inventario>> getByFechas(FechasDto fechasDto){
        return inventarioRepository.findAllByFchEntregaBetween(fechasDto.getFrom(),fechasDto.getUntil());
    }
    public Pagos getPagos(FechasDto fechasDto){
        float valorPago=0;
        double porcentajePago=0;
        float valorDescuento=0;
        double porcentajeDescuento=0;
        Optional<List<Inventario>> inventarios = inventarioRepository
                .findAllByFchEntregaBetweenAndVendedor(fechasDto.getFrom(), fechasDto.getUntil(),vendedorService.getOne(fechasDto.getId()).get());
        if(inventarios.isPresent()){
            for(Inventario in: inventarios.get()){
                if(in.getEstado().equals("ENTREGADO") && in.getTipoMovimiento().equals("1")){
                    valorPago= valorPago + in.getTotalPrecio();
                }else if(in.getEstado().equals("RETIRADO") && in.getTipoMovimiento().equals("1")){
                    valorDescuento = valorDescuento+in.getTotalPrecio();
                }
            }
        }
        inventarios = inventarioRepository.findAllByFchEntregaBeforeAndVendedor(fechasDto.getFrom(), vendedorService.getOne(fechasDto.getId()).get());
        if(inventarios.isPresent()){
            for(Inventario in: inventarios.get()){
                if(in.getEstado().equals("RETIRADO") && in.getTipoMovimiento().equals("1")){
                    valorDescuento = valorDescuento+in.getTotalPrecio();
                }
            }
        }
        Vendedor vendedor = vendedorService.getOne(fechasDto.getId()).get();
        porcentajePago = valorPago * 0.1;
        porcentajeDescuento = valorDescuento * 0.1;
        Pagos pagos = new Pagos(vendedor.getSaldo(),valorPago,porcentajePago,valorDescuento,porcentajeDescuento,0,0, vendedorService.getOne(fechasDto.getId()).get().getSaldo());
        return pagos;
    }
    public RegistrarPagos savePagosDeducion(RegistrarPagos pagos){
        Optional<List<Inventario>> inventarios = inventarioRepository
                .findAllByFchEntregaBetweenAndVendedor(pagos.getFecha().getFrom(), pagos.getFecha().getUntil(),vendedorService.getOne(pagos.getFecha().getId()).get());
        if(inventarios.isPresent()){
            for(Inventario in: inventarios.get()){
                if(in.getEstado().equals("ENTREGADO") && in.getTipoMovimiento().equals("1")){
                    in.setTipoMovimiento("2");
                    inventarioRepository.save(in);
                }else if(in.getEstado().equals("RETIRADO") && in.getTipoMovimiento().equals("1")){
                    in.setTipoMovimiento("2");
                    inventarioRepository.save(in);
                }
            }
        }
        inventarios = inventarioRepository.findAllByFchEntregaBeforeAndVendedor(pagos.getFecha().getFrom(), vendedorService.getOne(pagos.getFecha().getId()).get());
        if(inventarios.isPresent()){
            for(Inventario in: inventarios.get()){
                if(in.getEstado().equals("RETIRADO") && in.getTipoMovimiento().equals("1")){
                    in.setTipoMovimiento("2");
                    inventarioRepository.save(in);
                }
            }
        }
        Vendedor vendedor = vendedorService.getOne(pagos.getFecha().getId()).get();
        if(vendedor.getSaldo() == 0){
            double d = pagos.getPagos().getTotalPagar()
                    -pagos.getPagos().getOtrasDeducir()-pagos.getPagos().getTotalDeducir()-pagos.getPagos().getAbono();
            vendedor.setSaldo((float) d);

        }else{
            double d = vendedor.getSaldo()+pagos.getPagos().getTotalPagar()
                    -pagos.getPagos().getOtrasDeducir()-pagos.getPagos().getTotalDeducir()-pagos.getPagos().getAbono();
            vendedor.setSaldo((float) d);
        }
        vendedorService.save(vendedor);
        Gastos gastos = new Gastos();
        gastos.setDescripcion("Pago Vendedores");
        gastos.setFchPago(new Date());
        gastos.setPrecio(pagos.getPagos().getAbono());
        gastos.setTipoEmpleado("Pago Empleados");
        gastosService.save(gastos);
        return pagos;
    }

    public RegistrarPagos savePagosSinDeducion(RegistrarPagos pagos){
        Optional<List<Inventario>> inventarios = inventarioRepository
                .findAllByFchEntregaBetweenAndVendedor(pagos.getFecha().getFrom(), pagos.getFecha().getUntil(),vendedorService.getOne(pagos.getFecha().getId()).get());
        if(inventarios.isPresent()){
            for(Inventario in: inventarios.get()){
                if(in.getEstado().equals("ENTREGADO") && in.getTipoMovimiento().equals("1")){
                    in.setTipoMovimiento("2");
                    inventarioRepository.save(in);
                }
            }
        }
        Vendedor vendedor = vendedorService.getOne(pagos.getFecha().getId()).get();
        if(vendedor.getSaldo() == 0){
            double d = pagos.getPagos().getTotalPagar()
                    -pagos.getPagos().getOtrasDeducir()-pagos.getPagos().getAbono();
            vendedor.setSaldo((float) d);

        }else{
            double d = vendedor.getSaldo()+pagos.getPagos().getTotalPagar()
                    -pagos.getPagos().getOtrasDeducir()-pagos.getPagos().getAbono();
            vendedor.setSaldo((float) d);
        }
        vendedorService.save(vendedor);
        Gastos gastos = new Gastos();
        gastos.setDescripcion("Pago Vendedores");
        gastos.setFchPago(new Date());
        gastos.setPrecio(pagos.getPagos().getAbono());
        gastos.setTipoEmpleado("Pago Empleados");
        gastosService.save(gastos);
        return pagos;
    }
    public Optional<List<Inventario>> getByFechasAndVendedor(FechasDto fechasDto, int id){
        return inventarioRepository.findAllByFchEntregaBetween(fechasDto.getFrom(), fechasDto.getUntil());
    }
    public void  save(Inventario producto){
        clienteService.actualizarDeuda(producto.getProducto(),producto.getCantidadProducto(),producto.getCliente().getCodigo(), producto.getTotalPrecio(), producto.getEstado());
        if(producto.getTipoMovimiento().isEmpty()){
            producto.setTipoMovimiento("1");
        }
        inventarioRepository.save(producto);
    }
    public void  update(Inventario producto){
        Inventario inventario = inventarioRepository.findById(producto.getId()).get();
        if(!inventario.getEstado().equals(producto.getEstado())){
            producto.setTipoMovimiento("1");
            clienteService.actualizarDeuda(producto.getProducto(),producto.getCantidadProducto(),producto.getCliente().getCodigo(), producto.getTotalPrecio(), producto.getEstado());
        }

        if(producto.getTipoMovimiento().isEmpty()){
            producto.setTipoMovimiento("1");
        }
        inventarioRepository.save(producto);
    }
    public void delete(int id){
        inventarioRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return inventarioRepository.existsById(id);
    }

}
