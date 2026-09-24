package primerspringboot.com.gestiondepedidos.dtos.Producto;
//static es un metodo propio de la clase. no de la instanciacion de un objeto especifico. por eso se puede llamar a la clase CategoriaDto.toDto(categoria) sin necesidad de instanciar un objeto de CategoriaDto. En cambio, el metodo toEntity() no es static, por lo que se necesita instanciar un objeto de CategoriaCreate para poder llamar a este metodo. Por eso se llama a categoriaCreate.toEntity() en lugar de CategoriaCreate.toEntity(categoriaCreate).
import primerspringboot.com.gestiondepedidos.entities.Producto;
import primerspringboot.com.gestiondepedidos.entities.Categoria;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;


public record ProductoCreateReq(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @NotNull(message = "El precio es obligatorio")
        @Positive(message = "El precio debe ser mayor a 0")
        Double precio,

        @Size(max = 500, message = "La descripción no puede superar los 500 caracteres")
        String descripcion,

        @NotNull(message = "El stock es obligatorio")
        @PositiveOrZero(message = "El stock no puede ser negativo")
        Integer stock,

        @Size(max = 500, message = "La imagen no puede superar los 500 caracteres")
        String imagen,

        @NotNull(message = "El campo disponible es obligatorio")
        Boolean disponible,

        @NotNull(message = "La categoría es obligatoria")
        @Positive(message = "El ID de categoría debe ser mayor a 0")
        Long categoriaId

) {

    public Producto toEntity(Categoria categoria) {
        return Producto.builder()
                .nombre(nombre)
                .precio(precio)
                .descripcion(descripcion)
                .stock(stock)
                .imagen(imagen)
                .disponible(disponible)
                .categoria(categoria)
                .build();
    }
}