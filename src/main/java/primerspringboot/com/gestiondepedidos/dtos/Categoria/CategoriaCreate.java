package primerspringboot.com.gestiondepedidos.dtos.Categoria;

import primerspringboot.com.gestiondepedidos.entities.Categoria;

public record CategoriaCreate(
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