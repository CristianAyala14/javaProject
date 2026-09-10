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
    //atributos 
    @Builder.Default
    private LocalDate fecha = LocalDate.now();
    @Enumerated(EnumType.STRING) //le dice a jpa "guardame el nombre del enum en la base de datos, no el ordinal"
    @Builder.Default
    private Estado estado = Estado.PENDIENTE;
    @Builder.Default
    private Double total = 0.0;
    @Enumerated(EnumType.STRING)
    private FormaPago formaPago;
            // relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

            //relacion con detalles
    @Builder.Default
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<DetallePedido> detalles = new HashSet<>();


    //investigar
    @Override
    public void calcularTotal() {
        this.total = detalles.stream()
                .filter(d -> d.getSubtotal() != null)
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();
    }

    //metodos

    public void addDetallePedido(int cantidad, Producto producto) {
        if (producto == null) return;

        DetallePedido existente = findDetallePedidoByProducto(producto);

        if (existente != null) {
            existente.setCantidad(existente.getCantidad() + cantidad);
            existente.calcularSubtotal();
        } else {
            DetallePedido nuevo = DetallePedido.builder()
                    .cantidad(cantidad)
                    .producto(producto)
                    .build();
            detalles.add(nuevo);
        }

        calcularTotal();
    }

    public DetallePedido findDetallePedidoByProducto(Producto producto) {
        if (producto == null) return null;

        return detalles.stream()
                .filter(d -> d.getProducto() != null && d.getProducto().equals(producto))
                .findFirst()
                .orElse(null);
    }

    public void deleteDetallePedidoByProducto(Producto producto) {
        DetallePedido d = findDetallePedidoByProducto(producto);
        if (d != null) {
            detalles.remove(d);
            calcularTotal();
        }
    }

    @PrePersist
    @PreUpdate
    public void preSave() {
        calcularTotal();
    }
}