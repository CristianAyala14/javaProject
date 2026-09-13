package primerspringboot.com.gestiondepedidos.entities;

import primerspringboot.com.gestiondepedidos.enums.*;
import primerspringboot.com.gestiondepedidos.interfaces.*;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import lombok.*;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "detalles")
public class Pedido extends Base implements Calculable {

    @Builder.Default
    private LocalDate fecha = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Estado estado = Estado.PENDIENTE;

    @Builder.Default
    private Double total = 0.0;

    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Builder.Default
    @OneToMany(
            mappedBy = "pedido",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.EAGER
    )
    private Set<DetallePedido> detalles = new HashSet<>();


    @Override
    public void calcularTotal() {

        this.total = detalles.stream()
                .filter(d -> !d.isEliminado())
                .filter(d -> d.getSubtotal() != null)
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }


    public void addDetallePedido(
            int cantidad,
            Producto producto) {

        if (producto == null) {
            return;
        }

        DetallePedido existente =
                findDetallePedidoByProducto(producto);

        if (existente != null) {

            existente.setCantidad(
                    existente.getCantidad() + cantidad
            );

            existente.calcularSubtotal();

        } else {

            DetallePedido nuevo =
                    DetallePedido.builder()
                            .cantidad(cantidad)
                            .producto(producto)
                            .pedido(this)
                            .build();

            detalles.add(nuevo);
        }

        calcularTotal();
    }


    public DetallePedido findDetallePedidoByProducto(
            Producto producto) {

        if (producto == null) {
            return null;
        }

        return detalles.stream()
                .filter(d -> d.getProducto() != null)
                .filter(d -> d.getProducto().equals(producto))
                .filter(d -> !d.isEliminado())
                .findFirst()
                .orElse(null);
    }


    public void deleteDetallePedidoByProducto(
            Producto producto) {

        DetallePedido detalle =
                findDetallePedidoByProducto(producto);

        if (detalle != null) {

            detalle.setEliminado(true);

            calcularTotal();
        }
    }


    @PrePersist
    @PreUpdate
    public void preSave() {
        calcularTotal();
    }
}