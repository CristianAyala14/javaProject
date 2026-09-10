package primerspringboot.com.gestiondepedidos.dtos;
//record es una clase inmutable que se utiliza para transferir datos entre capas de la aplicación, como entre el controlador y el servicio.

public record UsuarioDto(
        Long id,
        String nombre,
        String apellido,
        String mail,
        String celular
) {}