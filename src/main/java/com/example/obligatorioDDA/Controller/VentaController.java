package com.example.obligatorioDDA.Controller;

import com.example.obligatorioDDA.Entity.DetalleVentaEntity;
import com.example.obligatorioDDA.Entity.UsuarioEntity;
import com.example.obligatorioDDA.Entity.VentaEntity;
import com.example.obligatorioDDA.Entity.VideoJuegoEntity;
import com.example.obligatorioDDA.Service.UsuarioService;
import com.example.obligatorioDDA.Service.VentaService;
import com.example.obligatorioDDA.Service.VideoJuegoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private VideoJuegoService videoJuegoService;

    @Autowired
    private UsuarioService usuarioService;

    @Transactional
    @PostMapping("/add")
    public ResponseEntity<?> registrarVenta(@RequestBody Map<String, Object> requestData) {
        try {
            // Validar campos obligatorios
            if (esNuloOInvalido(requestData.get("idUsuario")) ||
                    esNuloOInvalido(requestData.get("listaDetalles"))) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: idUsuario y listaDetalles son obligatorios.");
            }

            // Validar lista de detalles
            List<Map<String, Object>> detalles = (List<Map<String, Object>>) requestData.get("listaDetalles");
            if (detalles.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Error: La lista de detalles no puede estar vacía.");
            }

            // Buscar usuario
            int idUsuario = Integer.parseInt(requestData.get("idUsuario").toString());
            Optional<UsuarioEntity> usuarioOpt = usuarioService.findById(idUsuario);
            if (usuarioOpt.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: Usuario no encontrado.");
            }

            // Crear la venta
            VentaEntity venta = new VentaEntity();
            venta.setUsuarioEntity(usuarioOpt.get());
            venta.setFechaDeVenta(new Date());

            // Crear detalles de la venta
            List<DetalleVentaEntity> listaDetalles = detalles.stream().map(detalle -> {
                DetalleVentaEntity detalleVenta = new DetalleVentaEntity();
                int idVideojuego = Integer.parseInt(detalle.get("idVideojuego").toString());
                Optional<VideoJuegoEntity> videojuegoOpt = videoJuegoService.findById(idVideojuego);

                if (videojuegoOpt.isEmpty()) {
                    throw new IllegalArgumentException("Error: Videojuego con ID " + idVideojuego + " no encontrado.");
                }

                VideoJuegoEntity videojuego = videojuegoOpt.get();
                int cantidad = Integer.parseInt(detalle.get("cantidad").toString());
                int precioUnitario = videojuego.getPrecio();

                detalleVenta.setVideojuegoEntity(videojuego);
                detalleVenta.setCantidad(cantidad);
                detalleVenta.setPrecioUnitario(precioUnitario);
                detalleVenta.setVentaEntity(venta); // Asociar detalle a la venta
                return detalleVenta;
            }).toList();

            // Calcular monto total
            int montoTotal = listaDetalles.stream()
                    .mapToInt(detalle -> detalle.getCantidad() * detalle.getPrecioUnitario())
                    .sum();
            venta.setMontoTotal(montoTotal);

            // Asociar los detalles a la venta
            venta.setListaDetalles(listaDetalles);

            // Guardar la venta con los detalles
            VentaEntity nuevaVenta = ventaService.save(venta);

            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error inesperado: " + e.getMessage());
        }
    }



    private boolean esNuloOInvalido(Object valor) {
        return valor == null || (valor instanceof String && ((String) valor).trim().isEmpty());
    }


    @GetMapping("/all")
    public ResponseEntity<List<VentaEntity>> obtenerVentas() {
        return ResponseEntity.ok(ventaService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerVentaPorId(@PathVariable int id) {
        Optional<VentaEntity> venta = ventaService.findById(id);

        if (venta.isPresent()) {
            return ResponseEntity.ok(venta.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Venta no encontrada");
        }
    }
}
