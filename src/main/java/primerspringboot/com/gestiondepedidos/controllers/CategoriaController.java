package primerspringboot.com.gestiondepedidos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import primerspringboot.com.gestiondepedidos.dtos.Categoria.CategoriaCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Categoria.CategoriaEditReq;
import primerspringboot.com.gestiondepedidos.dtos.Categoria.CategoriaRes;
import primerspringboot.com.gestiondepedidos.service.Categoria.CategoriaService;

@RestController
@CrossOrigin("*")
@RequestMapping("/categoria")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaService categoriaService;

    @PostMapping
    public ResponseEntity<CategoriaRes> save(
            @Valid @RequestBody CategoriaCreateReq categoriaCreateReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoriaService.save(categoriaCreateReq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaRes> findById(
            @PathVariable Long id) {

        return ResponseEntity
                .ok(categoriaService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<CategoriaRes>> findAll() {

        return ResponseEntity
                .ok(categoriaService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaRes> update(
            @Valid @RequestBody CategoriaEditReq categoriaEditReq,
            @PathVariable Long id) {

        return ResponseEntity
                .ok(categoriaService.update(categoriaEditReq, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        categoriaService.delete(id);

        return ResponseEntity.noContent().build();
    }
}