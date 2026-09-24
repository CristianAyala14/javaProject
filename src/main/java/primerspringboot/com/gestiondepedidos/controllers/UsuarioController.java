package primerspringboot.com.gestiondepedidos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import primerspringboot.com.gestiondepedidos.dtos.Usuario.UsuarioCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Usuario.UsuarioEditReq;
import primerspringboot.com.gestiondepedidos.dtos.Usuario.UsuarioRes;
import primerspringboot.com.gestiondepedidos.service.Usuario.UsuarioService;

@RestController
@CrossOrigin("*")
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<UsuarioRes> save(
            @Valid @RequestBody UsuarioCreateReq usuarioCreateReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioService.save(usuarioCreateReq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioRes> findById(
            @PathVariable Long id) {

        return ResponseEntity
                .ok(usuarioService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<UsuarioRes>> findAll() {

        return ResponseEntity
                .ok(usuarioService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRes> update(
            @Valid @RequestBody UsuarioEditReq usuarioEditReq,
            @PathVariable Long id) {

        return ResponseEntity
                .ok(usuarioService.update(usuarioEditReq, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        usuarioService.delete(id);

        return ResponseEntity.noContent().build();
    }
}