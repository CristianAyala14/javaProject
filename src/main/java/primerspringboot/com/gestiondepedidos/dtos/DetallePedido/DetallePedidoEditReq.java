package primerspringboot.com.gestiondepedidos.dtos.DetallePedido;

import primerspringboot.com.gestiondepedidos.entities.DetallePedido;
import primerspringboot.com.gestiondepedidos.entities.Producto;

public record DetallePedidoEditReq(
        Integer cantidad,
        Long productoId
) {

    public void updateEntity(
            DetallePedido detallePedido,
            Producto producto) {

        if (cantidad != null) {
            detallePedido.setCantidad(cantidad);
        }

        if (producto != null) {
            detallePedido.setProducto(producto);
        }

        detallePedido.calcularSubtotal();
    }
}