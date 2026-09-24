package primerspringboot.com.gestiondepedidos.dtos.Categoria;
//static es un metodo propio de la clase. no de la instanciacion de un objeto especifico. por eso se puede llamar a la clase CategoriaDto.toDto(categoria) sin necesidad de instanciar un objeto de CategoriaDto. En cambio, el metodo toEntity() no es static, por lo que se necesita instanciar un objeto de CategoriaCreate para poder llamar a este metodo. Por eso se llama a categoriaCreate.toEntity() en lugar de CategoriaCreate.toEntity(categoriaCreate).



import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import primerspringboot.com.gestiondepedidos.entities.Categoria;

@Builder
public record CategoriaCreateReq(

        @NotBlank(message = "El nombre es obligatorio")
        @Size(max = 100, message = "El nombre no puede superar los 100 caracteres")
        String nombre,

        @Size(max = 255, message = "La descripción no puede superar los 255 caracteres")
        String descripcion

) {

    public Categoria toEntity() {
        return Categoria.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .build();
    }
}