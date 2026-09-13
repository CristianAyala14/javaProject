package primerspringboot.com.gestiondepedidos.dtos.Producto;

import primerspringboot.com.gestiondepedidos.entities.Producto;
import primerspringboot.com.gestiondepedidos.dtos.Categoria.CategoriaRes;
//lo que va entre parentesis en una record es el constructor de la clase record que se esta manejando. Es decir, CategoriaRes tiene un constructor que recibe un Long id, un String nombre y un String descripcion. Por eso se puede llamar a CategoriaRes.toDto(categoria) y pasarle un objeto categoria de tipo Categoria, ya que el metodo toDto() se encarga de extraer los valores de id, nombre y descripcion del objeto categoria y pasarlos al constructor de CategoriaRes para crear un nuevo objeto CategoriaRes con esos valores. En simples palabras un record, es una clase inmutable que tiene un constructor que recibe los valores de sus atributos y los asigna a esos atributos. Por eso se puede llamar a CategoriaRes.toDto(categoria) y pasarle un objeto categoria de tipo Categoria, ya que el metodo toDto() se encarga de extraer los valores de id, nombre y descripcion del objeto categoria y pasarlos al constructor de CategoriaRes para crear un nuevo objeto CategoriaRes con esos valores.
public record ProductoRes(
    Long id,
    String nombre,
    String precio,
    String descripcion,
    int stock,
    String imagen,
    Boolean disponible,
    CategoriaRes categoria
) {
    public static ProductoRes toDto(Producto producto) {
        return new ProductoRes(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio().toString(),
                producto.getDescripcion(),
                producto.getStock(),
                producto.getImagen(),
                producto.getDisponible(),
                producto.getCategoria() != null ? CategoriaRes.toDto(producto.getCategoria()) : null
        );
    }
}