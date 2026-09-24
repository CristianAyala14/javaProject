package primerspringboot.com.gestiondepedidos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import primerspringboot.com.gestiondepedidos.dtos.Producto.ProductoCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Producto.ProductoEditReq;
import primerspringboot.com.gestiondepedidos.dtos.Producto.ProductoRes;
import primerspringboot.com.gestiondepedidos.service.Producto.ProductoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/producto")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoRes> save(
            @Valid @RequestBody ProductoCreateReq productoCreateReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(productoService.save(productoCreateReq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoRes> findById(
            @PathVariable Long id) {

        return ResponseEntity
                .ok(productoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductoRes>> findAll() {

        return ResponseEntity
                .ok(productoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoRes> update(
            @Valid @RequestBody ProductoEditReq productoEditReq,
            @PathVariable Long id) {

        return ResponseEntity
                .ok(productoService.update(productoEditReq, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        productoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}