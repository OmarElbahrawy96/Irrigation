package com.oe.irrigation.dao;

import com.oe.irrigation.entity.IrrigationTimeTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IrrigationTimeTableRepository extends JpaRepository <IrrigationTimeTable, Integer> {
}
