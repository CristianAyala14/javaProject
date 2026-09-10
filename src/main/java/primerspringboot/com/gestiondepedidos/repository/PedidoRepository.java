package primerspringboot.com.gestiondepedidos.repository;

import primerspringboot.com.gestiondepedidos.entities.*;
import primerspringboot.com.gestiondepedidos.enums.*;
import primerspringboot.com.gestiondepedidos.dtos.*;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public class PedidoRepository extends BaseRepository<Pedido> {

    public PedidoRepository() {
        super(Pedido.class);
    }

    // TRANSACCIÓN ATÓMICA
    @Transactional
    public Optional<Pedido> crearPedidoTransaccional(
            Long usuarioId,
            Pedido pedido,
            List<ItemPedidoDTO> items,
            UsuarioRepository usuarioRepo,
            ProductoRepository productoRepo) {

        try {

            // buscamos el usuario utilizando el EntityManager
            // que Spring nos proporciona desde BaseRepository
            Usuario usuario = em.find(Usuario.class, usuarioId);

            if (usuario == null) {
                return Optional.empty();
            }

            pedido.setUsuario(usuario);

            for (ItemPedidoDTO item : items) {

                Producto p = em.find(
                        Producto.class,
                        item.productoId()
                );

                if (p == null) continue;
                if (!Boolean.TRUE.equals(p.getDisponible())) continue;
                if (p.getStock() < item.cantidad()) continue;

                // descontamos el stock
                p.setStock(p.getStock() - item.cantidad());

                // creamos el detalle del pedido
                DetallePedido detalle = new DetallePedido();

                detalle.setCantidad(item.cantidad());
                detalle.setProducto(p);

                pedido.getDetalles().add(detalle);
            }

            // calculamos el total del pedido
            pedido.calcularTotal();

            // guardamos el pedido
            em.persist(pedido);

            // Spring hace el commit automáticamente
            return Optional.of(pedido);

        } catch (Exception e) {

            // Si ocurre una excepción, Spring hace rollback
            // automáticamente gracias a @Transactional.
            return Optional.empty();
        }
    }

    // CONSULTA POR ESTADO
    public List<Pedido> buscarPorEstado(Estado estado) {

        String jpql =
                "SELECT p FROM Pedido p " +
                "WHERE p.estado = :estado AND p.eliminado = false";

        return em.createQuery(jpql, Pedido.class)
                .setParameter("estado", estado)
                .getResultList();
    }
}