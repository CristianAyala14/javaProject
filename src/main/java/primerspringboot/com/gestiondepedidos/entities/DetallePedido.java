package primerspringboot.com.gestiondepedidos.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DetallePedido extends Base {

    private int cantidad;

    private Double subtotal;

    @ManyToOne(optional = false)
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id", nullable = false)
    private Pedido pedido;

    @Builder
    public DetallePedido(
            int cantidad,
            Producto producto,
            Pedido pedido) {

        this.cantidad = cantidad;
        this.producto = producto;
        this.pedido = pedido;

        calcularSubtotal();
    }

    public void calcularSubtotal() {

        if (producto != null && producto.getPrecio() != null) {
            this.subtotal = cantidad * producto.getPrecio();
        } else {
            this.subtotal = 0.0;
        }
    }

    @PrePersist
    @PreUpdate
    public void preSave() {
        calcularSubtotal();
    }
}