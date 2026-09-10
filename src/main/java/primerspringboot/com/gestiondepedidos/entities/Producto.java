package primerspringboot.com.gestiondepedidos.entities;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "productos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(callSuper = true)
public class Producto extends Base {

    private String nombre;
    private Double precio;
    private String descripcion;
    private int stock;
    private String imagen;
    private Boolean disponible;
    //relacion con categoria 
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}