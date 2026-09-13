package primerspringboot.com.gestiondepedidos.service.Pedido;

import java.util.List;

import primerspringboot.com.gestiondepedidos.dtos.Pedido.PedidoCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Pedido.PedidoEditReq;
import primerspringboot.com.gestiondepedidos.dtos.Pedido.PedidoRes;

public interface PedidoService {

    public PedidoRes save(PedidoCreateReq pedido);

    public PedidoRes findById(Long id);

    public List<PedidoRes> findAll();

    public PedidoRes update(PedidoEditReq pedido, Long id);

    public void delete(Long id);
}