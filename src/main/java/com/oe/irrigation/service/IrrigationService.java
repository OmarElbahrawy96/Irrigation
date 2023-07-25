package com.oe.irrigation.service;

import com.oe.irrigation.entity.IrrigationTimeTable;

import java.util.List;
import java.util.Optional;

public interface IrrigationService {
    IrrigationTimeTable save(IrrigationTimeTable irrigationTimeTable);
    Optional<IrrigationTimeTable> findByID(int irrigation_id);
    List<IrrigationTimeTable> findAll();
    void deleteById(int irrigation_ID);
    void irrigatePlot(int plotId);
}
