package com.oe.irrigation.service;

import com.oe.irrigation.SensorDeviceApiClient;
import com.oe.irrigation.Status;
import com.oe.irrigation.dao.IrrigationTimeTableRepository;
import com.oe.irrigation.dao.PlotRepository;
import com.oe.irrigation.entity.IrrigationTimeTable;
import com.oe.irrigation.entity.Plot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class PlotServiceImpl implements PlotService{

    private final PlotRepository plotRepository;
    private final IrrigationTimeTableRepository irrigationTimeTableRepository;

    @Autowired
    public PlotServiceImpl(PlotRepository plotRepository, IrrigationTimeTableRepository irrigationRepository) {
        this.plotRepository = plotRepository;
        this.irrigationTimeTableRepository = irrigationRepository;
    }

    public Plot configurePlot(int plot_id, int irrigation_id){
        Plot plot = plotRepository.findById(plot_id).orElse(null);
        IrrigationTimeTable irrigationTimeTable = irrigationTimeTableRepository.findById(irrigation_id).orElse(null);

        if (plot != null && irrigationTimeTable != null) {
            plot.getIrrigationTimeTables().add(irrigationTimeTable);
            return plotRepository.save(plot);
        }

        return null;
    }

    @Override
    public Plot changePlotStatus(int plot_id, String status) {
        Plot plot = plotRepository.findById(plot_id).orElse(null);

        if (plot != null) {
            plot.setStatus(status);
            return plotRepository.save(plot);
        }

        return null;
    }

    @Transactional
    @Override
    public Plot save(Plot plot) {
        return plotRepository.save(plot);
    }

    @Override
    public Optional<Plot> findByID(int plotID) {
        return plotRepository.findById(plotID);
    }

    @Override
    public List<Plot> findAll() {
        return plotRepository.findAll();
    }

    @Transactional
    @Override
    public void deleteById(int plotID) {
        plotRepository.deleteById(plotID);
    }
}
