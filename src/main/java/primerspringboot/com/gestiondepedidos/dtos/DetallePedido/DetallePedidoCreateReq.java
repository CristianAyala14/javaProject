package primerspringboot.com.gestiondepedidos.dtos.DetallePedido;

import primerspringboot.com.gestiondepedidos.entities.DetallePedido;
import primerspringboot.com.gestiondepedidos.entities.Pedido;
import primerspringboot.com.gestiondepedidos.entities.Producto;

public record DetallePedidoCreateReq(

        int cantidad,

        Long productoId

) {

    public DetallePedido toEntity(Producto producto, Pedido pedido) {

        return DetallePedido.builder()
                .cantidad(cantidad)
                .producto(producto)
                .pedido(pedido)
                .build();
    }
}