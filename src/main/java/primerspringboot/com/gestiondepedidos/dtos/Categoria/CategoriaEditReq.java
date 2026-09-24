package primerspringboot.com.gestiondepedidos.dtos.Categoria;
import primerspringboot.com.gestiondepedidos.entities.Categoria;
import jakarta.validation.constraints.Size;

public record CategoriaEditReq(

        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
        String descripcion

) {

    public Categoria updateEntity(Categoria categoria) {

        if (nombre != null && !nombre.isBlank()) {
            categoria.setNombre(nombre);
        }

        if (descripcion != null) {
            categoria.setDescripcion(descripcion);
        }

        return categoria;
    }
}