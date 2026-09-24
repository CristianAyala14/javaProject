package primerspringboot.com.gestiondepedidos.dtos.Pedido;

import primerspringboot.com.gestiondepedidos.entities.Pedido;
import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.enums.Estado;
import primerspringboot.com.gestiondepedidos.enums.FormaPago;


import jakarta.validation.constraints.Positive;


public record PedidoEditReq(

        Estado estado,

        FormaPago formaPago,

        @Positive(message = "El ID de usuario debe ser mayor a 0")
        Long usuarioId

) {

    public Pedido updateEntity(Pedido pedido, Usuario usuario) {

        if (estado != null) {
            pedido.setEstado(estado);
        }

        if (formaPago != null) {
            pedido.setFormaPago(formaPago);
        }

        if (usuario != null) {
            pedido.setUsuario(usuario);
        }

        return pedido;
    }
}