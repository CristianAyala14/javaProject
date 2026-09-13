package primerspringboot.com.gestiondepedidos.service.Producto;

import java.util.List;

import org.springframework.stereotype.Service;

import primerspringboot.com.gestiondepedidos.dtos.Producto.*;
import primerspringboot.com.gestiondepedidos.entities.Categoria;
import primerspringboot.com.gestiondepedidos.entities.Producto;
import primerspringboot.com.gestiondepedidos.repository.CategoriaRepository;
import primerspringboot.com.gestiondepedidos.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(
            ProductoRepository productoRepository,
            CategoriaRepository categoriaRepository) {

        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public ProductoRes save(ProductoCreateReq productoCreateReq) {

        Categoria categoria = categoriaRepository.findById(productoCreateReq.categoriaId())
                .orElseThrow(() -> new NullPointerException("Categoria no encontrada"));

        Producto producto = productoCreateReq.toEntity(categoria);

        producto = productoRepository.save(producto);

        return ProductoRes.toDto(producto);
    }

    @Override
    public ProductoRes findById(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Producto no encontrado"));

        return ProductoRes.toDto(producto);
    }

    @Override
    public List<ProductoRes> findAll() {

        List<Producto> productos = productoRepository.findAll();

        return productos.stream()
                .map(ProductoRes::toDto)
                .toList();
    }

    @Override
    public ProductoRes update(ProductoEditReq productoEdit, Long id) {

        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Producto no encontrado"));
        Categoria categoria = null;
        if (productoEdit.categoriaId() != null) {
            categoria = categoriaRepository.findById(productoEdit.categoriaId())
                    .orElseThrow(() -> new NullPointerException("Categoria no encontrada"));
        }
        productoEdit.updateEntity(productoExistente, categoria);
        productoExistente = productoRepository.save(productoExistente);
        return ProductoRes.toDto(productoExistente);
        
    }

    @Override
    public void delete(Long id) {

        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Producto no encontrado"));

        producto.setEliminado(true);

        productoRepository.save(producto);
    }
}