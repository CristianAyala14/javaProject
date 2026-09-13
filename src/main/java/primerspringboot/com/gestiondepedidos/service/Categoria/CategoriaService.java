package primerspringboot.com.gestiondepedidos.service.Categoria;
import primerspringboot.com.gestiondepedidos.dtos.Categoria.CategoriaCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Categoria.CategoriaEditReq;
import primerspringboot.com.gestiondepedidos.dtos.Categoria.CategoriaRes;
import java.util.List;

public interface CategoriaService {
    public CategoriaRes save(CategoriaCreateReq categoria);

    public CategoriaRes findById(Long id);

    public List<CategoriaRes> findAll();

    public CategoriaRes update(CategoriaEditReq categoria, Long id);

    public void delete(Long id);
}