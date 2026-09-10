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

    //relacion con producto
    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

    @Builder
    public DetallePedido(int cantidad, Producto producto) {
        this.cantidad = cantidad;
        this.producto = producto;
        calcularSubtotal();
    }

    public void calcularSubtotal() {
        if (producto != null && producto.getPrecio() != null) {
            this.subtotal = cantidad * producto.getPrecio();
        } else {
            this.subtotal = 0.0;
        }
    }
    //antes de guardar en jpa, se ejecuta este metodo para calcular el subtotal antes de persistir o actualizar la entidad.
    @PrePersist
    @PreUpdate
    public void preSave() {
        calcularSubtotal();
    }
}
