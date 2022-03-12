package com.tutorial.crud.service;

import com.tutorial.crud.dto.EstadisticasCobradorDto;
import com.tutorial.crud.dto.FechasDto;
import com.tutorial.crud.dto.Pagos;
import com.tutorial.crud.dto.RegistrarPagos;
import com.tutorial.crud.entity.Clientes;
import com.tutorial.crud.entity.Cobradores;
import com.tutorial.crud.entity.Gastos;
import com.tutorial.crud.entity.Inventario;
import com.tutorial.crud.entity.PagosClientes;
import com.tutorial.crud.entity.Vendedor;
import com.tutorial.crud.repository.InventarioRepository;
import com.tutorial.crud.repository.PagosClientesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PagosClientesService {
    @Autowired
    ClienteService clienteService;
    @Autowired
    GastosService gastosService;
    @Autowired
    CobradoresService cobradoresService;
    @Autowired
    PagosClientesRepository pagosClientesRepository;

    public List<PagosClientes> list(){
        return (List<PagosClientes>) pagosClientesRepository.findAllByMovimientoIsNotOrderByFchPagoDesc("").get();
    }

    public Optional<PagosClientes> getOne(int id){
        return pagosClientesRepository.findById(id);
    }

    public Optional<List<PagosClientes>> getByVendedorAndEstado(Cobradores cobradores, String estado){
        return pagosClientesRepository.findByCobradorAndMovimiento(cobradores,estado);
    }
    public Optional<List<PagosClientes>> getByClientes(Clientes clientes){
        return pagosClientesRepository.findByClienteOrderByFchPagoDesc(clientes);
    }
    public Optional<List<PagosClientes>> getByFechas(FechasDto fechasDto){
        return pagosClientesRepository.findAllByFchPagoBetween(fechasDto.getFrom(),fechasDto.getUntil());
    }
    public List<EstadisticasCobradorDto> getEstadisticas(FechasDto fechasDto){
        float valorPago=0;
        List<EstadisticasCobradorDto> estadisticasCobradorDtos = new ArrayList<>();
        List<Cobradores> cobradores = cobradoresService.list();
        Calendar c = Calendar.getInstance();
        for(Cobradores cobra : cobradores){
            Optional<List<PagosClientes>> inventarios;
            Date date = fechasDto.getFrom();
            if(fechasDto.getFrom().equals(fechasDto.getUntil())){
                c.setTime(fechasDto.getFrom());
                int day=c.get(Calendar.DATE);
                c.set(Calendar.DATE,day-1);
                inventarios = pagosClientesRepository
                        .findAllByFchPagoBetweenAndCobrador(c.getTime(), fechasDto.getUntil(),cobra);
            }else{
                inventarios = pagosClientesRepository
                        .findAllByFchPagoBetweenAndCobrador(fechasDto.getFrom(), fechasDto.getUntil(),cobra);
            }

            if(inventarios.isPresent()){
                for(PagosClientes in: inventarios.get()){
                        valorPago= valorPago + in.getValor();
                }
            }
            if(inventarios.isPresent()) {
                estadisticasCobradorDtos.add(new EstadisticasCobradorDto(cobra, inventarios.get().size(), valorPago));
            }
            valorPago = 0;
        }
        return estadisticasCobradorDtos;
    }
    public Pagos getPagos(FechasDto fechasDto){
        float valorPago=0;
        double porcentajePago=0;
        float valorDescuento=0;
        double porcentajeDescuento=0;
        Optional<List<PagosClientes>> inventarios = pagosClientesRepository
                .findAllByFchPagoBetweenAndCobrador(fechasDto.getFrom(), fechasDto.getUntil(),cobradoresService.getOne(fechasDto.getId()).get());
        if(inventarios.isPresent()){
            for(PagosClientes in: inventarios.get()){
                if(in.getMovimiento().equals("1")){
                    valorPago= valorPago + in.getValor();
                }
            }
        }
        inventarios = pagosClientesRepository.findAllByFchPagoBeforeAndCobrador(fechasDto.getFrom(), cobradoresService.getOne(fechasDto.getId()).get());
        if(inventarios.isPresent()){
            for(PagosClientes in: inventarios.get()){
                if(in.getMovimiento().equals("1")){
                    valorPago= valorPago + in.getValor();
                }
            }
        }
        Cobradores vendedor = cobradoresService.getOne(fechasDto.getId()).get();
        porcentajePago = valorPago * 0.1;
        Pagos pagos = new Pagos(vendedor.getSaldo(),valorPago,porcentajePago,0,0,0,0, vendedor.getSaldo());
        return pagos;
    }

    public RegistrarPagos savePagosSinDeducion(RegistrarPagos pagos){
        Optional<List<PagosClientes>> inventarios = pagosClientesRepository
                .findAllByFchPagoBetweenAndCobrador(pagos.getFecha().getFrom(), pagos.getFecha().getUntil(),cobradoresService.getOne(pagos.getFecha().getId()).get());
        if(inventarios.isPresent()){
            for(PagosClientes in: inventarios.get()){
                if(in.getMovimiento().equals("1")){
                    in.setMovimiento("2");
                    pagosClientesRepository.save(in);
                }
            }
        }
        inventarios = pagosClientesRepository.findAllByFchPagoBeforeAndCobrador(pagos.getFecha().getFrom(), cobradoresService.getOne(pagos.getFecha().getId()).get());
        if(inventarios.isPresent()){
            for(PagosClientes in: inventarios.get()){
                if(in.getMovimiento().equals("1")){
                    in.setMovimiento("2");
                    pagosClientesRepository.save(in);
                }
            }
        }
        Cobradores vendedor = cobradoresService.getOne(pagos.getFecha().getId()).get();
        if(vendedor.getSaldo() == 0){
            double d = pagos.getPagos().getTotalPagar()
                    -pagos.getPagos().getOtrasDeducir()-pagos.getPagos().getAbono();
            vendedor.setSaldo((float) d);

        }else{
            double d = vendedor.getSaldo()+pagos.getPagos().getTotalPagar()
                    -pagos.getPagos().getOtrasDeducir()-pagos.getPagos().getAbono();
            vendedor.setSaldo((float) d);
        }

        if(vendedor.getSaldo() < 0){
            vendedor.setSaldo(0);
        }
        cobradoresService.save(vendedor);
        Gastos gastos = new Gastos();
        gastos.setDescripcion("Pago cobradores");
        gastos.setFchPago(new Date());
        gastos.setPrecio(pagos.getPagos().getAbono());
        gastos.setTipoEmpleado("Pago Empleados");
        gastosService.save(gastos);
        return pagos;
    }
    public Optional<List<PagosClientes>> getByFechasAndCliente(FechasDto fechasDto, int id){
        return pagosClientesRepository.findByClienteOrderByFchPagoDesc(clienteService.getOne(fechasDto.getId()).get());
    }
    public void  save(PagosClientes producto){
        clienteService.actualizarSaldo(producto.getCliente().getCodigo(), producto.getValor());
        if(producto.getMovimiento().isEmpty()){
            producto.setMovimiento("1");
        }
        pagosClientesRepository.save(producto);
    }
    public void  update(PagosClientes producto){
        PagosClientes inventario = pagosClientesRepository.findById(producto.getId()).get();
        producto.setMovimiento("1");
        clienteService.actualizarSaldoUpdate(producto.getCliente().getCodigo(), producto.getValor(),inventario.getValor());
        pagosClientesRepository.save(producto);
    }
    public void delete(int id){
        PagosClientes inventario = pagosClientesRepository.findById(id).get();
        clienteService.actualizarSaldoUpdate(inventario.getCliente().getCodigo(), 0,inventario.getValor());
        pagosClientesRepository.deleteById(id);
    }

    public boolean existsById(int id){
        return pagosClientesRepository.existsById(id);
    }

}
