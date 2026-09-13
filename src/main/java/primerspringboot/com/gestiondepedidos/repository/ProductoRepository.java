package primerspringboot.com.gestiondepedidos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import primerspringboot.com.gestiondepedidos.entities.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    
}
