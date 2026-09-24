package primerspringboot.com.gestiondepedidos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import primerspringboot.com.gestiondepedidos.dtos.Pedido.PedidoCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Pedido.PedidoEditReq;
import primerspringboot.com.gestiondepedidos.dtos.Pedido.PedidoRes;
import primerspringboot.com.gestiondepedidos.service.Pedido.PedidoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/pedido")
@RequiredArgsConstructor
public class PedidoController {

    private final PedidoService pedidoService;

    @PostMapping
    public ResponseEntity<PedidoRes> save(
            @Valid @RequestBody PedidoCreateReq pedidoCreateReq) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(pedidoService.save(pedidoCreateReq));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoRes> findById(
            @PathVariable Long id) {

        return ResponseEntity
                .ok(pedidoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<PedidoRes>> findAll() {

        return ResponseEntity
                .ok(pedidoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoRes> update(
            @Valid @RequestBody PedidoEditReq pedidoEditReq,
            @PathVariable Long id) {

        return ResponseEntity
                .ok(pedidoService.update(pedidoEditReq, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        pedidoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}