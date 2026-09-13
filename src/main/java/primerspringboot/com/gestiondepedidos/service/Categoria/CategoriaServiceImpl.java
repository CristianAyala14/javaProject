package primerspringboot.com.gestiondepedidos.service.Categoria;
import java.util.List;
import org.springframework.stereotype.Service;
import primerspringboot.com.gestiondepedidos.dtos.Categoria.*;
import primerspringboot.com.gestiondepedidos.entities.Categoria;
import primerspringboot.com.gestiondepedidos.repository.CategoriaRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;
    public CategoriaServiceImpl(CategoriaRepository         categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public CategoriaRes save(CategoriaCreateReq categoriaCreateReq) {
        Categoria categoria = categoriaCreateReq.toEntity();
        categoria = categoriaRepository.save(categoria);
        return CategoriaRes.toDto(categoria);
        
    }



    @Override
    public CategoriaRes findById(Long id) {
        Categoria categoria = categoriaRepository.findById(id).orElseThrow(() -> new NullPointerException("Categoria no encontrada"));
        return CategoriaRes.toDto(categoria);
    }

    @Override
    public List<CategoriaRes> findAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        return categorias.stream().map(CategoriaRes::toDto).toList();
    }

    @Override
    public CategoriaRes update(CategoriaEditReq categoriaEdit, Long id) {
        //buscar la categoria existente
        Categoria categoriaExistente = categoriaRepository.findById(id).orElseThrow(() -> new NullPointerException("Categoria no encontrada"));
        //actualizar la categoria existente con los datos del DTO
        categoriaEdit.updateEntity(categoriaExistente);
        //guardar la categoria actualizada en la base de datos
        categoriaExistente = categoriaRepository.save(categoriaExistente);
        //devolver la categoria actualizada como DTO
        return CategoriaRes.toDto(categoriaExistente);
    }

    @Override
    public void delete(Long id) {
    Categoria categoria = categoriaRepository.findById(id)
        .orElseThrow(() -> new NullPointerException("Categoria no encontrada"));
        categoria.setEliminado(true);
        categoriaRepository.save(categoria);
    }
}
    
