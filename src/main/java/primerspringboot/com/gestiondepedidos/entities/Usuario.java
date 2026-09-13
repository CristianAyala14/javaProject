package primerspringboot.com.gestiondepedidos.entities;
import primerspringboot.com.gestiondepedidos.enums.*;
import java.util.Set;
import java.util.HashSet;
import lombok.*;
import jakarta.persistence.*;


@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "pedidos") // Evita bucles recursivos en el toString (investigar un poco mas para entender mejor)
public class Usuario extends Base {
    private String nombre;
    private String apellido;
    private String mail;
    private String celular;
    private String contraseña;
    
    
    @Enumerated(EnumType.STRING) // Guarda el string ('ADMIN', 'USUARIO') en la BD y no el numero ordinal del enum
    private Rol rol;
    
    // Un usuario tiene muchos pedidos, mapeado por el atributo 'usuario' en Pedido
    @OneToMany(
        mappedBy = "usuario",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @Builder.Default
    private Set<Pedido> pedidos = new HashSet<>();
    
    
   
}