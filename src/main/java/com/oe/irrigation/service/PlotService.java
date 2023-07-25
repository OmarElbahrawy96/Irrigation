package com.oe.irrigation.service;

import com.oe.irrigation.entity.Plot;

import java.util.List;
import java.util.Optional;

public interface PlotService {

/*
▪Add new plot of land
▪Configure a plot of land
▪Edit a plot of land
▪List all plots and it's details
*/

    Plot save(Plot plot);
    Optional<Plot> findByID(int plotID);
    List<Plot> findAll();
    void deleteById(int plotID);
    Plot configurePlot(int plot_id, int irrigation_id);
    Plot changePlotStatus(int plot_id, String status);
}
