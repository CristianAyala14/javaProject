package primerspringboot.com.gestiondepedidos.dtos.Usuario;

import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.enums.Rol;

public record UsuarioEditReq(
        String nombre,
        String apellido,
        String mail,
        String celular,
        String contraseña,
        Rol rol
) {

    public void updateEntity(Usuario usuario) {

        if (nombre != null) {
            usuario.setNombre(nombre);
        }
        if (apellido != null) {
            usuario.setApellido(apellido);
        }
        if (mail != null) {
            usuario.setMail(mail);
        }
        if (celular != null) {
            usuario.setCelular(celular);
        }
        if (contraseña != null) {
            usuario.setContraseña(contraseña);
        }
        if (rol != null) {
            usuario.setRol(rol);
        }
    }
}