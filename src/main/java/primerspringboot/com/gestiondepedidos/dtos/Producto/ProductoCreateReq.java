package primerspringboot.com.gestiondepedidos.dtos.Producto;
//static es un metodo propio de la clase. no de la instanciacion de un objeto especifico. por eso se puede llamar a la clase CategoriaDto.toDto(categoria) sin necesidad de instanciar un objeto de CategoriaDto. En cambio, el metodo toEntity() no es static, por lo que se necesita instanciar un objeto de CategoriaCreate para poder llamar a este metodo. Por eso se llama a categoriaCreate.toEntity() en lugar de CategoriaCreate.toEntity(categoriaCreate).
import primerspringboot.com.gestiondepedidos.entities.Producto;
import primerspringboot.com.gestiondepedidos.entities.Categoria;



public record ProductoCreateReq(
        String nombre,
        Double precio,  
        String descripcion,
        int stock,
        String imagen,
        Boolean disponible,
        Long categoriaId
) {

    public Producto toEntity(Categoria categoria) {
        return Producto.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .precio(precio)
                .stock(stock)
                .imagen(imagen)
                .disponible(disponible)
                .categoria(categoria)
                .build();
    }
}

