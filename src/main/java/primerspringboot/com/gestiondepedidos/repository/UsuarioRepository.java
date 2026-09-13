package primerspringboot.com.gestiondepedidos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import primerspringboot.com.gestiondepedidos.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}