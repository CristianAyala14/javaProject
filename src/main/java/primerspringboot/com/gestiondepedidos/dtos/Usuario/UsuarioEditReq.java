package primerspringboot.com.gestiondepedidos.dtos.Usuario;

import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.enums.Rol;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;


public record UsuarioEditReq(

        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @Size(max = 100, message = "El apellido no puede superar los 100 caracteres")
        String apellido,

        @Email(message = "El mail no tiene un formato válido")
        String mail,

        String celular,

        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contraseña,

        Rol rol

) {

    public Usuario updateEntity(Usuario usuario) {

        if (nombre != null && !nombre.isBlank()) {
            usuario.setNombre(nombre);
        }

        if (apellido != null && !apellido.isBlank()) {
            usuario.setApellido(apellido);
        }

        if (mail != null && !mail.isBlank()) {
            usuario.setMail(mail);
        }

        if (celular != null && !celular.isBlank()) {
            usuario.setCelular(celular);
        }

        if (contraseña != null && !contraseña.isBlank()) {
            usuario.setContraseña(contraseña);
        }

        if (rol != null) {
            usuario.setRol(rol);
        }

        return usuario;
    }
}