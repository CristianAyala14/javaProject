package primerspringboot.com.gestiondepedidos.repository;

import jakarta.persistence.*;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

// repositorio generico que contiene operaciones que sirven
// para todas las entidades
// en vez de escribir metodos como guardar(), buscarPorId(), listarActivos(),
// eliminarLogico() en cada repositorio, se escriben una sola
// vez en esta clase y se heredan en los repositorios de cada entidad.

// aca le decimos a java "no se que entidad manejare aun, solo sabre cuando se
// cree un repositorio de una entidad especifica,"
// dsp puedo tener BaseRepository<Usuario>, BaseRepository<Producto>,
// BaseRepository<Pedido> etc.

// Por que creamos un BaseRepository generico? Para evitar
// repetir codigo y tener un solo lugar donde manejar las
// operaciones comunes a todas las entidades.
// Por ejemplo, en este caso, este repository es una capa que creamos para
// manejar las operaciones de persistencia de todas las entidades,
// y asi no tener que repetir el mismo codigo en cada repositorio de cada entidad.

@Repository //le dice a spring que esta clase se encarga de acceder a la base de datos y que la maneje como un bean de spring
public abstract class BaseRepository<T> {

    // esto guarda la clase concreta que estamos vamos a usar
    protected final Class<T> entityClass;

    // le decimos a Spring que inyecte el EntityManager,
    // que es el que nos permite realizar operaciones
    // sobre la base de datos.
    // Antes haciamos:
    // EntityManager em = emf.createEntityManager();
    // Ahora Spring se encarga de crearlo y administrarlo.
    @PersistenceContext //le dice a spring que nos de un entity manager para trabajar con jpa.
    protected EntityManager em;

    // el constructor recibe la clase concreta de la
    // entidad que se va a manejar
    public BaseRepository(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Transactional //le dice a spring que todo lo que ocurra en este metodo debe ejecutarse
    // dentro de una transaccion, y si algo falla, se deshace todo.
    public T guardar(T entity) {

        // le decimos a JPA que sincronice la entidad con la base de datos.
        // Si no existe, la crea. Si existe, la actualiza.
        T mergedEntity = em.merge(entity);

        // Spring se encarga automaticamente de confirmar la transaccion.
        // Ya no necesitamos hacer:
        // em.getTransaction().begin();
        // em.getTransaction().commit();

        // se devuelve la entidad gestionada
        return mergedEntity;
    }

    // Optional es una clase de Java que representa un valor que puede
    // estar presente o ausente, para que si llega a devolver null,
    // no tengamos que trabajar directamente con null.
    public Optional<T> buscarPorId(Long id) {

        // le decimos a JPA que busque la entidad de tipo T
        // con el id que le pasamos y la convierte en un Optional,
        // para manejar el caso en que no encuentre la entidad.
        return Optional.ofNullable(em.find(entityClass, id));
    }

    public List<T> listarActivos() {

        // armamos una consulta JPQL que son consultas orientadas a objetos,
        // que se escriben utilizando las entidades Java y no directamente
        // las tablas de la base de datos.
        String jpql =
                "SELECT e FROM " + entityClass.getSimpleName()
                + " e WHERE e.eliminado = false";

        // aca le decimos a JPA que ejecute la consulta y nos
        // devuelva una lista de entidades de tipo entityClass.
        return em.createQuery(jpql, entityClass)
                .getResultList();
    }

    // devuelve el entity eliminado
    @Transactional
    public Optional<T> eliminarLogico(Long id) {

        // buscamos la entidad por su ID
        T entity = em.find(entityClass, id);

        // si no existe, devolvemos un Optional vacio
        if (entity == null) {
            return Optional.empty();
        }

        try {
            // llamamos al setter setEliminado(true)
            // para marcar la entidad como eliminada logicamente.
            entity.getClass()
                    .getMethod("setEliminado", boolean.class)
                    .invoke(entity, true);

            // JPA detectara el cambio y Spring confirmara
            // la transaccion automaticamente.
            return Optional.of(entity);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}