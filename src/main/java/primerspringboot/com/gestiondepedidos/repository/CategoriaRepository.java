package primerspringboot.com.gestiondepedidos.repository;
import primerspringboot.com.gestiondepedidos.entities.*;

import java.util.List;

//este repository de categoria sirve para consultas mas especificas de esta entidad, 
// como por ejemplo buscar productos de una categoria. Extiende de 
// BaseRepository para heredar los metodos genericos de CRUD y listar activos pero tambien hace sus 
// propias consultas.
public class CategoriaRepository extends BaseRepository<Categoria> {

    //llama al constructor de la clase padre BaseRepository y le pasa 
    // la clase Categoria.class para que sepa que esta 
    // clase es un repositorio de Categoria.
    public CategoriaRepository() {
        super(Categoria.class);
    }

   
    // Consulta JPQL con JOIN a productos filtrando eliminados
    public List<Producto> buscarProductosPorCategoria(Long categoriaId) {
        try {
            String jpql =
                    "SELECT p FROM Categoria c " +
                    "JOIN c.productos p " +
                    "WHERE c.id = :id AND p.eliminado = false";

            return em.createQuery(jpql, Producto.class)
                    .setParameter("id", categoriaId)
                    .getResultList();

        } finally {
            em.close();
        }
    }
}