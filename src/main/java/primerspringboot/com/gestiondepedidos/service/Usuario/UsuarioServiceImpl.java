package primerspringboot.com.gestiondepedidos.service.Usuario;

import java.util.List;

import org.springframework.stereotype.Service;

import primerspringboot.com.gestiondepedidos.dtos.Usuario.*;
import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.repository.UsuarioRepository;

@Service("implementacion-1")
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioRes save(UsuarioCreateReq usuarioCreate) {

        Usuario usuario = usuarioCreate.toEntity();

        usuario = usuarioRepository.save(usuario);

        return UsuarioRes.toDto(usuario);
    }

    @Override
    public UsuarioRes findById(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Usuario no encontrado"));

        return UsuarioRes.toDto(usuario);
    }

    @Override
    public List<UsuarioRes> findAll() {

        List<Usuario> usuarios = usuarioRepository.findAll();

        return usuarios.stream()
                .map(UsuarioRes::toDto)
                .toList();
    }

    @Override
    public UsuarioRes update(UsuarioEditReq usuarioEdit, Long id) {

        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Usuario no encontrado"));

        usuarioEdit.updateEntity(usuarioExistente);

        usuarioExistente = usuarioRepository.save(usuarioExistente);

        return UsuarioRes.toDto(usuarioExistente);
    }

    @Override
    public void delete(Long id) {

        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Usuario no encontrado"));

        usuario.setEliminado(true);

        usuarioRepository.save(usuario);
    }
}