package primerspringboot.com.gestiondepedidos.dtos.DetallePedido;

import primerspringboot.com.gestiondepedidos.entities.DetallePedido;
import primerspringboot.com.gestiondepedidos.entities.Pedido;
import primerspringboot.com.gestiondepedidos.entities.Producto;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record DetallePedidoCreateReq(

        @NotNull(message = "La cantidad es obligatoria")
        @Positive(message = "La cantidad debe ser mayor a 0")
        Integer cantidad,

        @NotNull(message = "El producto es obligatorio")
        @Positive(message = "El ID de producto debe ser mayor a 0")
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