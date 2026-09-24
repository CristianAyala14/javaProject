package primerspringboot.com.gestiondepedidos.dtos.DetallePedido;

import primerspringboot.com.gestiondepedidos.entities.DetallePedido;



public record DetallePedidoRes(
        Long id,
        int cantidad,
        Double subtotal,
        Long productoId
) {

    public static DetallePedidoRes toDto(DetallePedido detallePedido) {
        return new DetallePedidoRes(
                detallePedido.getId(),
                detallePedido.getCantidad(),
                detallePedido.getSubtotal(),
                detallePedido.getProducto().getId()
        );
    }
}