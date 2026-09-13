package primerspringboot.com.gestiondepedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import primerspringboot.com.gestiondepedidos.entities.DetallePedido;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Long> {

}