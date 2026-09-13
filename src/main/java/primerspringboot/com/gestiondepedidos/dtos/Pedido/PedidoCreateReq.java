package primerspringboot.com.gestiondepedidos.dtos.Pedido;

import primerspringboot.com.gestiondepedidos.entities.Pedido;
import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.enums.FormaPago;

public record PedidoCreateReq(
        FormaPago formaPago,
        Long usuarioId
) {

    public Pedido toEntity(Usuario usuario) {
        return Pedido.builder()
                .formaPago(formaPago)
                .usuario(usuario)
                .build();
    }
}