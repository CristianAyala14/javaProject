package primerspringboot.com.gestiondepedidos.entities;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@MappedSuperclass //sus atributos seran heredados y mapeados en tablas hijas. Pero esta clase no tendra una tabla propia.
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    protected boolean eliminado;
    protected LocalDateTime createdAt;


}