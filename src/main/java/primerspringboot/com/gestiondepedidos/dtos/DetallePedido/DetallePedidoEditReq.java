package primerspringboot.com.gestiondepedidos.dtos.DetallePedido;

import primerspringboot.com.gestiondepedidos.entities.DetallePedido;
import primerspringboot.com.gestiondepedidos.entities.Producto;


import jakarta.validation.constraints.Positive;


public record DetallePedidoEditReq(

        @Positive(message = "La cantidad debe ser mayor a 0")
        Integer cantidad,

        @Positive(message = "El ID de producto debe ser mayor a 0")
        Long productoId

) {

    public DetallePedido updateEntity(
            DetallePedido detallePedido,
            Producto producto
    ) {

        if (cantidad != null) {
            detallePedido.setCantidad(cantidad);
        }

        if (producto != null) {
            detallePedido.setProducto(producto);
        }

        return detallePedido;
    }
}