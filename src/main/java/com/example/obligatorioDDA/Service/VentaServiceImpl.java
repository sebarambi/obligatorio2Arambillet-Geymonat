package com.example.obligatorioDDA.Service;

import com.example.obligatorioDDA.Entity.VentaEntity;
import com.example.obligatorioDDA.Repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    private VentaRepository ventaRepository;

    public VentaEntity save(VentaEntity venta) {
        return ventaRepository.save(venta);
    }

    public List<VentaEntity> getAll() {
        return ventaRepository.findAll();
    }

    public Optional<VentaEntity> findById(int id) {
        return ventaRepository.findById(id);
    }

    public void delete(int id) {
        ventaRepository.deleteById(id);
    }

    public List<VentaEntity> obtenerVentasPorRangoDeFechas(Date fechaInicio, Date fechaFin) {
        return ventaRepository.findByFechaDeVentaBetween(fechaInicio, fechaFin);
    }
}
