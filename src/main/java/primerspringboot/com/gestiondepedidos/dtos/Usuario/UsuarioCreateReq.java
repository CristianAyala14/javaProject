package primerspringboot.com.gestiondepedidos.dtos.Usuario;

import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.enums.Rol;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public record UsuarioCreateReq(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @NotBlank(message = "El apellido es obligatorio")
        @Size(max = 100, message = "El apellido no puede superar los 100 caracteres")
        String apellido,

        @NotBlank(message = "El mail es obligatorio")
        @Email(message = "El mail no tiene un formato válido")
        String mail,

        @NotBlank(message = "El celular es obligatorio")
        String celular,

        @NotBlank(message = "La contraseña es obligatoria")
        @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
        String contraseña,

        @NotNull(message = "El rol es obligatorio")
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