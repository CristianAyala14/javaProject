package primerspringboot.com.gestiondepedidos.repository;
import jakarta.persistence.TypedQuery;
import primerspringboot.com.gestiondepedidos.entities.*;

import java.util.List;

public class ProductoRepository extends BaseRepository<Producto> {

    public ProductoRepository() {
        super(Producto.class);
    }

    // buscarPorCategoria(Long categoriaId) con JPQL personalizado
    public List<Producto> buscarPorCategoria(Long categoriaId) {

        /*
         * Consulta JPQL utilizando un parámetro nombrado ':categoriaId'.
         * Filtra los productos que pertenecen a la categoría indicada a través de la colección inversa
         * y que posean estado lógico activo (eliminado = false).
         */
        String jpql =
                "SELECT p FROM Categoria c " +
                "JOIN c.productos p " +
                "WHERE c.id = :categoriaId " +
                "AND p.eliminado = false";

        TypedQuery<Producto> query =
                em.createQuery(jpql, Producto.class);

        query.setParameter("categoriaId", categoriaId);

        return query.getResultList();
    }
}