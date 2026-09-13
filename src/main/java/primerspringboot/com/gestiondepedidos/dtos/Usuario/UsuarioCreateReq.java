package primerspringboot.com.gestiondepedidos.dtos.Usuario;

import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.enums.Rol;

public record UsuarioCreateReq(
        String nombre,
        String apellido,
        String mail,
        String celular,
        String contraseña,
        Rol rol
) {

    public Usuario toEntity() {
        return Usuario.builder()
                .nombre(nombre)
                .apellido(apellido)
                .mail(mail)
                .celular(celular)
                .contraseña(contraseña)
                .rol(rol)
                .build();
    }
}