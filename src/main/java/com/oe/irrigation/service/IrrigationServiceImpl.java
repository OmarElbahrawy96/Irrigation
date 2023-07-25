package com.oe.irrigation.service;

import com.oe.irrigation.SensorDeviceApiClient;
import com.oe.irrigation.Status;
import com.oe.irrigation.dao.IrrigationTimeTableRepository;
import com.oe.irrigation.dao.PlotRepository;
import com.oe.irrigation.entity.IrrigationTimeTable;
import com.oe.irrigation.entity.Plot;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@Service
public class IrrigationServiceImpl implements IrrigationService {

    private static final int MAX_RETRIES = 3;
    private static final Duration BACKOFF_DURATION = Duration.ofSeconds(5);
    private final SensorDeviceApiClient sensorDeviceApiClient;
    private final AlertService alertService;
    private final PlotRepository plotRepository;
    private final IrrigationTimeTableRepository irrigationTimeTableRepository;

    public IrrigationServiceImpl(SensorDeviceApiClient sensorDeviceApiClient, AlertService alertService, PlotRepository plotRepository, IrrigationTimeTableRepository irrigationTimeTableRepository) {
        this.sensorDeviceApiClient = sensorDeviceApiClient;
        this.alertService = alertService;
        this.plotRepository = plotRepository;
        this.irrigationTimeTableRepository = irrigationTimeTableRepository;
    }

    public void irrigatePlot(int plotId) {
        Plot plot = plotRepository.findById(plotId).orElseThrow(() -> new RuntimeException("Plot not found"));

        boolean irrigationSuccess = sendIrrigationRequestToSensorDevice(plot);
        if (irrigationSuccess) {
            plot.setStatus(Status.IRRIGATED.toString());
            plotRepository.save(plot);
        }
        else {
            plot.setStatus(Status.SensorNotAvailable.toString());
            plotRepository.save(plot);
        }
    }

    private boolean sendIrrigationRequestToSensorDevice(Plot plot) {
        int retryCount = 0;
        boolean success = false;

        while (retryCount < MAX_RETRIES && !success) {
            try {
                sensorDeviceApiClient.sendIrrigationRequest(plot.getId());
                success = true;
            } catch (Exception e) {
                System.out.println("Sensor device unavailable. Retrying...");
                retryCount++;
                try {
                    Thread.sleep(BACKOFF_DURATION.toMillis());
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }

        if (!success && retryCount >= MAX_RETRIES) {
            alertService.sendSensorUnavailableAlert(plot);
        }

        return success;
    }


    @Override
    public IrrigationTimeTable save(IrrigationTimeTable irrigationTimeTable) {
        return irrigationTimeTableRepository.save(irrigationTimeTable);
    }

    @Override
    public Optional<IrrigationTimeTable> findByID(int irrigation_id) {
        return irrigationTimeTableRepository.findById(irrigation_id);
    }

    @Override
    public List<IrrigationTimeTable> findAll() {
        return irrigationTimeTableRepository.findAll();
    }

    @Override
    public void deleteById(int irrigation_ID) {
        irrigationTimeTableRepository.deleteById(irrigation_ID);
    }
}
