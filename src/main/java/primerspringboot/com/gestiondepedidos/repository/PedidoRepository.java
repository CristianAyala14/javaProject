package primerspringboot.com.gestiondepedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import primerspringboot.com.gestiondepedidos.entities.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

}