package primerspringboot.com.gestiondepedidos.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ErrorDTO {

    private int status;
    private String mensaje;
    private List<String> detalles;

    public static ErrorDTO of(int status, String mensaje, List<String> detalles) {
        return ErrorDTO.builder()
                .status(status)
                .mensaje(mensaje)
                .detalles(detalles)
                .build();
    }
}
