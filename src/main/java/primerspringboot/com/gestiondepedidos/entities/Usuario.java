package primerspringboot.com.gestiondepedidos.entities;

import primerspringboot.com.gestiondepedidos.enums.Rol;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "pedidos")
public class Usuario extends Base {

    private String nombre;

    private String apellido;

    private String mail;

    private String celular;

    private String contraseña;

    @Enumerated(EnumType.STRING)
    private Rol rol;

    @OneToMany(
            mappedBy = "usuario",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    @Builder.Default
    private Set<Pedido> pedidos = new HashSet<>();
}