package com.oe.irrigation.dao;

import com.oe.irrigation.entity.Plot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlotRepository extends JpaRepository <Plot, Integer> {
}
