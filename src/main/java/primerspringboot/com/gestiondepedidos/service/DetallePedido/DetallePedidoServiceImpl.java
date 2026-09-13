package primerspringboot.com.gestiondepedidos.service.DetallePedido;

import java.util.List;

import org.springframework.stereotype.Service;

import primerspringboot.com.gestiondepedidos.dtos.DetallePedido.*;
import primerspringboot.com.gestiondepedidos.entities.DetallePedido;
import primerspringboot.com.gestiondepedidos.entities.Pedido;
import primerspringboot.com.gestiondepedidos.entities.Producto;
import primerspringboot.com.gestiondepedidos.repository.DetallePedidoRepository;
import primerspringboot.com.gestiondepedidos.repository.PedidoRepository;
import primerspringboot.com.gestiondepedidos.repository.ProductoRepository;

@Service
public class DetallePedidoServiceImpl implements DetallePedidoService {

    private final DetallePedidoRepository detallePedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public DetallePedidoServiceImpl(
            DetallePedidoRepository detallePedidoRepository,
            PedidoRepository pedidoRepository,
            ProductoRepository productoRepository) {

        this.detallePedidoRepository = detallePedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public DetallePedidoRes save(DetallePedidoCreateReq detalleCreateReq, Long pedidoId) {

        Pedido pedido = pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new NullPointerException("Pedido no encontrado"));

        Producto producto = productoRepository.findById(detalleCreateReq.productoId())
                .orElseThrow(() -> new NullPointerException("Producto no encontrado"));

        pedido.addDetallePedido(detalleCreateReq.cantidad(), producto);

        pedidoRepository.save(pedido);

        DetallePedido detalle = pedido.findDetallePedidoByProducto(producto);

        return DetallePedidoRes.toDto(detalle);
    }

    @Override
    public DetallePedidoRes findById(Long id) {

        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("DetallePedido no encontrado"));

        return DetallePedidoRes.toDto(detalle);
    }

    @Override
    public List<DetallePedidoRes> findAll() {

        List<DetallePedido> detalles = detallePedidoRepository.findAll();

        return detalles.stream()
                .map(DetallePedidoRes::toDto)
                .toList();
    }

    @Override
    public DetallePedidoRes update(
            DetallePedidoEditReq detalleEdit,
            Long id) {

        DetallePedido detalleExistente = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("DetallePedido no encontrado"));

        Producto producto = null;

        if (detalleEdit.productoId() != null) {
            producto = productoRepository.findById(detalleEdit.productoId())
                    .orElseThrow(() -> new NullPointerException("Producto no encontrado"));
        }

        detalleEdit.updateEntity(detalleExistente, producto);

        detalleExistente = detallePedidoRepository.save(detalleExistente);

        Pedido pedido = detalleExistente.getPedido();

        if (pedido != null) {
            pedido.calcularTotal();
            pedidoRepository.save(pedido);
        }

        return DetallePedidoRes.toDto(detalleExistente);
    }

    @Override
    public void delete(Long id) {

        DetallePedido detalle = detallePedidoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("DetallePedido no encontrado"));

        detalle.setEliminado(true);

        detallePedidoRepository.save(detalle);

        Pedido pedido = detalle.getPedido();

        if (pedido != null) {
            pedido.calcularTotal();
            pedidoRepository.save(pedido);
        }
    }
}