package primerspringboot.com.gestiondepedidos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import primerspringboot.com.gestiondepedidos.dtos.Categoria.CategoriaCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Categoria.CategoriaRes;
import primerspringboot.com.gestiondepedidos.dtos.DetallePedido.DetallePedidoCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Pedido.PedidoCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Pedido.PedidoRes;
import primerspringboot.com.gestiondepedidos.dtos.Producto.ProductoCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Producto.ProductoRes;
import primerspringboot.com.gestiondepedidos.dtos.Usuario.UsuarioCreateReq;
import primerspringboot.com.gestiondepedidos.dtos.Usuario.UsuarioRes;
import primerspringboot.com.gestiondepedidos.enums.FormaPago;
import primerspringboot.com.gestiondepedidos.enums.Rol;
import primerspringboot.com.gestiondepedidos.service.Categoria.CategoriaService;
import primerspringboot.com.gestiondepedidos.service.DetallePedido.DetallePedidoService;
import primerspringboot.com.gestiondepedidos.service.Pedido.PedidoService;
import primerspringboot.com.gestiondepedidos.service.Producto.ProductoService;
import primerspringboot.com.gestiondepedidos.service.Usuario.UsuarioService;

@SpringBootApplication
public class GestiondepedidosApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestiondepedidosApplication.class, args);
    }

    @Bean // esto es un bean de Spring, que se ejecuta al iniciar la aplicación. Lo hace despues de que se inicializa el contexto de Spring, y se ejecuta una sola vez.
    CommandLineRunner initData(
            UsuarioService usuarioService,
            CategoriaService categoriaService,
            ProductoService productoService,
            PedidoService pedidoService,
            DetallePedidoService detallePedidoService) {

        return args -> {

            // ==========================================
            // 1. CREAR 2 USUARIOS
            // ==========================================

            UsuarioRes usuario1 = usuarioService.save(
                    new UsuarioCreateReq(
                            "Juan",
                            "Perez",
                            "juan@gmail.com",
                            "1122334455",
                            "1234",
                            Rol.USUARIO
                    )
            );

            UsuarioRes usuario2 = usuarioService.save(
                    new UsuarioCreateReq(
                            "Maria",
                            "Gomez",
                            "maria@gmail.com",
                            "1166778899",
                            "1234",
                            Rol.USUARIO
                    )
            );


            // ==========================================
            // 2. CREAR 3 CATEGORIAS
            // ==========================================

            CategoriaRes categoria1 = categoriaService.save(
                    new CategoriaCreateReq(
                            "Electrónica",
                            "Productos electrónicos"
                    )
            );

            CategoriaRes categoria2 = categoriaService.save(
                    new CategoriaCreateReq(
                            "Hogar",
                            "Productos para el hogar"
                    )
            );

            CategoriaRes categoria3 = categoriaService.save(
                    new CategoriaCreateReq(
                            "Informática",
                            "Productos de informática"
                    )
            );


            // ==========================================
            // 3. CREAR 10 PRODUCTOS
            // ==========================================

            ProductoRes producto1 = productoService.save(
                    new ProductoCreateReq(
                            "Mouse",
                            15000.0,
                            "Mouse inalámbrico",
                            20,
                            "mouse.jpg",
                            true,
                            categoria3.id()
                    )
            );

            ProductoRes producto2 = productoService.save(
                    new ProductoCreateReq(
                            "Teclado",
                            25000.0,
                            "Teclado mecánico",
                            15,
                            "teclado.jpg",
                            true,
                            categoria3.id()
                    )
            );

            ProductoRes producto3 = productoService.save(
                    new ProductoCreateReq(
                            "Monitor",
                            300000.0,
                            "Monitor 24 pulgadas",
                            10,
                            "monitor.jpg",
                            true,
                            categoria3.id()
                    )
            );

            ProductoRes producto4 = productoService.save(
                    new ProductoCreateReq(
                            "Auriculares",
                            45000.0,
                            "Auriculares inalámbricos",
                            25,
                            "auriculares.jpg",
                            true,
                            categoria1.id()
                    )
            );

            ProductoRes producto5 = productoService.save(
                    new ProductoCreateReq(
                            "Parlante",
                            60000.0,
                            "Parlante Bluetooth",
                            12,
                            "parlante.jpg",
                            true,
                            categoria1.id()
                    )
            );

            ProductoRes producto6 = productoService.save(
                    new ProductoCreateReq(
                            "Smartphone",
                            500000.0,
                            "Teléfono inteligente",
                            8,
                            "smartphone.jpg",
                            true,
                            categoria1.id()
                    )
            );

            ProductoRes producto7 = productoService.save(
                    new ProductoCreateReq(
                            "Lámpara",
                            20000.0,
                            "Lámpara de escritorio",
                            30,
                            "lampara.jpg",
                            true,
                            categoria2.id()
                    )
            );

            ProductoRes producto8 = productoService.save(
                    new ProductoCreateReq(
                            "Silla",
                            120000.0,
                            "Silla de escritorio",
                            10,
                            "silla.jpg",
                            true,
                            categoria2.id()
                    )
            );

            ProductoRes producto9 = productoService.save(
                    new ProductoCreateReq(
                            "Escritorio",
                            200000.0,
                            "Escritorio de oficina",
                            5,
                            "escritorio.jpg",
                            true,
                            categoria2.id()
                    )
            );

            ProductoRes producto10 = productoService.save(
                    new ProductoCreateReq(
                            "Webcam",
                            70000.0,
                            "Cámara web HD",
                            15,
                            "webcam.jpg",
                            true,
                            categoria3.id()
                    )
            );


            // ==========================================
            // 4. CREAR 3 PEDIDOS
            // ==========================================

            PedidoRes pedido1 = pedidoService.save(
                    new PedidoCreateReq(
                            FormaPago.TARJETA,
                            usuario1.id()
                    )
            );

            PedidoRes pedido2 = pedidoService.save(
                    new PedidoCreateReq(
                            FormaPago.TRANSFERENCIA,
                            usuario2.id()
                    )
            );

            PedidoRes pedido3 = pedidoService.save(
                    new PedidoCreateReq(
                            FormaPago.EFECTIVO,
                            usuario1.id()
                    )
            );


            // ==========================================
            // 5. CREAR AL MENOS 2 DETALLES POR PEDIDO
            // ==========================================

            detallePedidoService.save(
                    new DetallePedidoCreateReq(
                            2,
                            producto1.id()
                    ),
                    pedido1.id()
            );

            detallePedidoService.save(
                    new DetallePedidoCreateReq(
                            1,
                            producto2.id()
                    ),
                    pedido1.id()
            );


            detallePedidoService.save(
                    new DetallePedidoCreateReq(
                            1,
                            producto3.id()
                    ),
                    pedido2.id()
            );

            detallePedidoService.save(
                    new DetallePedidoCreateReq(
                            2,
                            producto4.id()
                    ),
                    pedido2.id()
            );


            detallePedidoService.save(
                    new DetallePedidoCreateReq(
                            1,
                            producto5.id()
                    ),
                    pedido3.id()
            );

            detallePedidoService.save(
                    new DetallePedidoCreateReq(
                            3,
                            producto7.id()
                    ),
                    pedido3.id()
            );


            System.out.println("==========================================");
            System.out.println("DATOS INICIALES CARGADOS CORRECTAMENTE");
            System.out.println("==========================================");
        };
    }
}