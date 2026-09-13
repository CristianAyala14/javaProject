package primerspringboot.com.gestiondepedidos.service.Pedido;

import java.util.List;

import org.springframework.stereotype.Service;

import primerspringboot.com.gestiondepedidos.dtos.Pedido.*;
import primerspringboot.com.gestiondepedidos.entities.Pedido;
import primerspringboot.com.gestiondepedidos.entities.Usuario;
import primerspringboot.com.gestiondepedidos.repository.PedidoRepository;
import primerspringboot.com.gestiondepedidos.repository.UsuarioRepository;

@Service("implementacion-1")
public class PedidoServiceImpl implements PedidoService {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;

    public PedidoServiceImpl(
            PedidoRepository pedidoRepository,
            UsuarioRepository usuarioRepository) {

        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public PedidoRes save(PedidoCreateReq pedidoCreateReq) {

        Usuario usuario = usuarioRepository.findById(pedidoCreateReq.usuarioId())
                .orElseThrow(() -> new NullPointerException("Usuario no encontrado"));

        Pedido pedido = pedidoCreateReq.toEntity(usuario);

        pedido = pedidoRepository.save(pedido);

        return PedidoRes.toDto(pedido);
    }

    @Override
    public PedidoRes findById(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Pedido no encontrado"));

        return PedidoRes.toDto(pedido);
    }

    @Override
    public List<PedidoRes> findAll() {

        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos.stream()
                .map(PedidoRes::toDto)
                .toList();
    }

    @Override
    public PedidoRes update(PedidoEditReq pedidoEdit, Long id) {

        Pedido pedidoExistente = pedidoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Pedido no encontrado"));

        Usuario usuario = null;

        if (pedidoEdit.usuarioId() != null) {
            usuario = usuarioRepository.findById(pedidoEdit.usuarioId())
                    .orElseThrow(() -> new NullPointerException("Usuario no encontrado"));
        }

        pedidoEdit.updateEntity(pedidoExistente, usuario);

        pedidoExistente = pedidoRepository.save(pedidoExistente);

        return PedidoRes.toDto(pedidoExistente);
    }

    @Override
    public void delete(Long id) {

        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Pedido no encontrado"));

        pedido.setEliminado(true);

        pedidoRepository.save(pedido);
    }
}