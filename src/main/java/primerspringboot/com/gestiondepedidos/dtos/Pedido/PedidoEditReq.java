package primerspringboot.com.gestiondepedidos.dtos.Pedido;

import primerspringboot.com.gestiondepedidos.entities.Pedido;
import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.enums.Estado;
import primerspringboot.com.gestiondepedidos.enums.FormaPago;

public record PedidoEditReq(
        Estado estado,
        FormaPago formaPago,
        Long usuarioId
) {

    public void updateEntity(Pedido pedido, Usuario usuario) {

        if (estado != null) {
            pedido.setEstado(estado);
        }
        if (formaPago != null) {
            pedido.setFormaPago(formaPago);
        }
        if (usuario != null) {
            pedido.setUsuario(usuario);
        }
    }
}

