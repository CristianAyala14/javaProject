package primerspringboot.com.gestiondepedidos.service.DetallePedido;

import java.util.List;

import primerspringboot.com.gestiondepedidos.dtos.DetallePedido.DetallePedidoCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.DetallePedido.DetallePedidoEditReq;
import primerspringboot.com.gestiondepedidos.dtos.DetallePedido.DetallePedidoRes;

public interface DetallePedidoService {

    public DetallePedidoRes save(DetallePedidoCreateReq detalle, Long pedidoId);

    public DetallePedidoRes findById(Long id);

    public List<DetallePedidoRes> findAll();

    public DetallePedidoRes update(DetallePedidoEditReq detalle, Long id);

    public void delete(Long id);
}