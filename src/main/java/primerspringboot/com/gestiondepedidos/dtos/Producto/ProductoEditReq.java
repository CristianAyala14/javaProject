package primerspringboot.com.gestiondepedidos.dtos.Producto;

import primerspringboot.com.gestiondepedidos.entities.Producto;
import primerspringboot.com.gestiondepedidos.entities.Categoria;

public record ProductoEditReq(
        String nombre,
        Double precio,  
        String descripcion,
        int stock,
        String imagen,
        Boolean disponible,
        Long categoriaId
) {
    public void updateEntity(Producto producto, Categoria categoria) {
        if (this.nombre != null) {
            producto.setNombre(nombre);
        }
        if (this.precio != null) {
            producto.setPrecio(precio);
        }
        if (this.descripcion != null) {
            producto.setDescripcion(descripcion);
        }
        if (this.stock != 0) {
            producto.setStock(stock);
        }
        if (this.imagen != null) {
            producto.setImagen(imagen);
        }
        if (this.disponible != null) {
            producto.setDisponible(disponible);
        }
        if (categoria != null) {
            producto.setCategoria(categoria);
        }
    }
}