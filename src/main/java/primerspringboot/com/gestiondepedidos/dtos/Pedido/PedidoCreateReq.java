package primerspringboot.com.gestiondepedidos.dtos.Pedido;

import primerspringboot.com.gestiondepedidos.entities.Pedido;
import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.enums.FormaPago;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PedidoCreateReq(

        @NotNull(message = "La forma de pago es obligatoria")
        FormaPago formaPago,

        @NotNull(message = "El usuario es obligatorio")
        @Positive(message = "El ID de usuario debe ser mayor a 0")
        Long usuarioId

) {

    public Pedido toEntity(Usuario usuario) {
        return Pedido.builder()
                .formaPago(formaPago)
                .usuario(usuario)
                .build();
    }
}