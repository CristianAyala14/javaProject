package primerspringboot.com.gestiondepedidos.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;
import primerspringboot.com.gestiondepedidos.dtos.DetallePedido.DetallePedidoCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.DetallePedido.DetallePedidoEditReq;
import primerspringboot.com.gestiondepedidos.dtos.DetallePedido.DetallePedidoRes;
import primerspringboot.com.gestiondepedidos.service.DetallePedido.DetallePedidoService;

@RestController
@CrossOrigin("*")
@RequestMapping("/detalle-pedido")
@RequiredArgsConstructor
public class DetallePedidoController {

    private final DetallePedidoService detallePedidoService;

    @PostMapping("/pedido/{pedidoId}")
    public ResponseEntity<DetallePedidoRes> save(
            @Valid @RequestBody DetallePedidoCreateReq detalleCreateReq,
            @PathVariable Long pedidoId) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(detallePedidoService.save(detalleCreateReq, pedidoId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoRes> findById(
            @PathVariable Long id) {

        return ResponseEntity
                .ok(detallePedidoService.findById(id));
    }

    @GetMapping
    public ResponseEntity<List<DetallePedidoRes>> findAll() {

        return ResponseEntity
                .ok(detallePedidoService.findAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoRes> update(
            @Valid @RequestBody DetallePedidoEditReq detalleEditReq,
            @PathVariable Long id) {

        return ResponseEntity
                .ok(detallePedidoService.update(detalleEditReq, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id) {

        detallePedidoService.delete(id);

        return ResponseEntity.noContent().build();
    }
}