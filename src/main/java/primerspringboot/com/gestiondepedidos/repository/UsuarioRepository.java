package primerspringboot.com.gestiondepedidos.repository;

import primerspringboot.com.gestiondepedidos.entities.*;

import java.util.List;
import java.util.Optional;

public class UsuarioRepository extends BaseRepository<Usuario> {

    public UsuarioRepository() {
        super(Usuario.class);
    }

    // Buscar usuario por mail (validación de registro)
    public Optional<Usuario> buscarPorMail(String mail) {

        String jpql =
                "SELECT u FROM Usuario u " +
                "WHERE u.mail = :mail AND u.eliminado = false";

        return em.createQuery(jpql, Usuario.class)
                .setParameter("mail", mail)
                .getResultList()
                .stream()
                .findFirst();
    }

    // Pedidos de un usuario
    public List<Pedido> buscarPedidosPorUsuario(Long idUsuario) {

        String jpql =
                "SELECT p FROM Usuario u JOIN u.pedidos p " +
                "WHERE u.id = :id AND p.eliminado = false";

        return em.createQuery(jpql, Pedido.class)
                .setParameter("id", idUsuario)
                .getResultList();
    }
}