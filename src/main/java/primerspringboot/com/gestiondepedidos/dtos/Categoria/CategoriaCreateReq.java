package primerspringboot.com.gestiondepedidos.dtos.Categoria;
//static es un metodo propio de la clase. no de la instanciacion de un objeto especifico. por eso se puede llamar a la clase CategoriaDto.toDto(categoria) sin necesidad de instanciar un objeto de CategoriaDto. En cambio, el metodo toEntity() no es static, por lo que se necesita instanciar un objeto de CategoriaCreate para poder llamar a este metodo. Por eso se llama a categoriaCreate.toEntity() en lugar de CategoriaCreate.toEntity(categoriaCreate).


import primerspringboot.com.gestiondepedidos.entities.Categoria;

public record CategoriaCreateReq(
        String nombre,
        String descripcion
) {

    public Categoria toEntity() {
        return Categoria.builder()
                .nombre(nombre)
                .descripcion(descripcion)
                .build();
    }
}

