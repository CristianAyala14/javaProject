package primerspringboot.com.gestiondepedidos.dtos.Pedido;

import primerspringboot.com.gestiondepedidos.entities.Pedido;
import primerspringboot.com.gestiondepedidos.enums.Estado;
import primerspringboot.com.gestiondepedidos.enums.FormaPago;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record PedidoRes(

        Long id,
        LocalDate fecha,
        Estado estado,
        Double total,
        FormaPago formaPago,
        Long usuarioId,
        Set<Long> detallesIds

) {

    public static PedidoRes toDto(Pedido pedido) {

        return new PedidoRes(

                pedido.getId(),
                pedido.getFecha(),
                pedido.getEstado(),
                pedido.getTotal(),
                pedido.getFormaPago(),
                pedido.getUsuario().getId(),
                pedido.getDetalles()
                        .stream()
                        .map(detalle -> detalle.getId())
                        .collect(Collectors.toSet())
        );
    }
}