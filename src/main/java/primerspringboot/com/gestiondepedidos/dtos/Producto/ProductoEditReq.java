package primerspringboot.com.gestiondepedidos.dtos.Producto;

import primerspringboot.com.gestiondepedidos.entities.Producto;
import primerspringboot.com.gestiondepedidos.entities.Categoria;


import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


public record ProductoEditReq(

        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @Positive(message = "El precio debe ser mayor a 0")
        Double precio,

        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion,

        @PositiveOrZero(message = "El stock no puede ser negativo")
        Integer stock,

        @Size(max = 500, message = "La imagen no puede superar los 500 caracteres")
        String imagen,

        Boolean disponible,

        @Positive(message = "El ID de categoría debe ser mayor a 0")
        Long categoriaId

) {

    public Producto updateEntity(Producto producto, Categoria categoria) {

        if (nombre != null && !nombre.isBlank()) {
            producto.setNombre(nombre);
        }

        if (precio != null) {
            producto.setPrecio(precio);
        }

        if (descripcion != null) {
            producto.setDescripcion(descripcion);
        }

        if (stock != null) {
            producto.setStock(stock);
        }

        if (imagen != null) {
            producto.setImagen(imagen);
        }

        if (disponible != null) {
            producto.setDisponible(disponible);
        }

        if (categoria != null) {
            producto.setCategoria(categoria);
        }

        return producto;
    }
}