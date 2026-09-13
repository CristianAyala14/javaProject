package primerspringboot.com.gestiondepedidos.service.Producto;

import primerspringboot.com.gestiondepedidos.dtos.Producto.ProductoCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Producto.ProductoEditReq;
import primerspringboot.com.gestiondepedidos.dtos.Producto.ProductoRes;

import java.util.List;

public interface ProductoService {

    public ProductoRes save(ProductoCreateReq producto);

    public ProductoRes findById(Long id);

    public List<ProductoRes> findAll();

    public ProductoRes update(ProductoEditReq producto, Long id);

    public void delete(Long id);

}