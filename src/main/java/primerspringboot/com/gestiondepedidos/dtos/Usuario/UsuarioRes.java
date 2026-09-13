package primerspringboot.com.gestiondepedidos.dtos.Usuario;

import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.enums.Rol;

public record UsuarioRes(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular,
        Rol rol
) {

    public static UsuarioRes toDto(Usuario usuario) {
        return new UsuarioRes(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getMail(),
                usuario.getCelular(),
                usuario.getRol()
        );
    }
}