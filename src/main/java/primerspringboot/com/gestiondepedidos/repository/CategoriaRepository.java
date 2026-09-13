package primerspringboot.com.gestiondepedidos.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import primerspringboot.com.gestiondepedidos.entities.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    
}
