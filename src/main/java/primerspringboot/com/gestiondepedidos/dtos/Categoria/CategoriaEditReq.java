package primerspringboot.com.gestiondepedidos.dtos.Categoria;
import primerspringboot.com.gestiondepedidos.entities.Categoria;

public record CategoriaEditReq(
    String nombre,
    String descripcion
) {
    public void updateEntity(Categoria categoria) {
        if (this.nombre != null) {
            categoria.setNombre(nombre);
        }
        if (this.descripcion != null) {
            categoria.setDescripcion(descripcion);
        }
    }
}