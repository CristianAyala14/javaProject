package primerspringboot.com.gestiondepedidos.service.Usuario;

import java.util.List;

import primerspringboot.com.gestiondepedidos.dtos.Usuario.UsuarioCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Usuario.UsuarioRes;
import primerspringboot.com.gestiondepedidos.dtos.Usuario.UsuarioEditReq;

public interface UsuarioService {

    public UsuarioRes save(UsuarioCreateReq usuario);

    public UsuarioRes findById(Long id);

    public List<UsuarioRes> findAll();

    public UsuarioRes update(UsuarioEditReq usuario, Long id);

    public void delete(Long id);
}