package com.oe.irrigation.controller;

import com.oe.irrigation.entity.IrrigationTimeTable;
import com.oe.irrigation.entity.Plot;
import com.oe.irrigation.service.IrrigationService;
import com.oe.irrigation.service.PlotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class IrrigationRestController {

    private PlotService plotService;
    private IrrigationService irrigationService;

    @Autowired
    public IrrigationRestController(PlotService plotService, IrrigationService irrigationTimeTableService) {
        this.plotService = plotService;
        this.irrigationService = irrigationTimeTableService;
    }

    @GetMapping("/plots")
    public List<Plot> findAll(){
        return plotService.findAll();
    }

    @GetMapping("/plots/{plotId}")
    public Optional<Plot> findById(@PathVariable int plotId){
        Optional<Plot> plot = plotService.findByID(plotId);
        if (plot == null){
            throw new RuntimeException("Plot Id not found - "+plotId);
        }
        return plot;
    }

    @PostMapping("/plots")
    public Plot addPlot(@RequestBody Plot plot){
        plot.setId(0);
        Plot thePlot = plotService.save(plot);
        return thePlot;
    }

    @PutMapping("/plots")
    public Plot updatePlot(@RequestBody Plot plot){
        Plot thePlot = plotService.save(plot);
        return thePlot;
    }

    @DeleteMapping("/plots/{plotId}")
    public String deletePlot(@PathVariable int plotId){
        Optional<Plot> plot = plotService.findByID(plotId);
        if (plot == null){
            throw new RuntimeException("Plot Id not found - "+plotId);
        }
        plotService.deleteById(plotId);

        return "Plot deleted - "+plotId;
    }

    @PostMapping("/configure")
    public ResponseEntity<String> configurePlot(@RequestParam int plotId, @RequestParam int irrigationId) {
        Plot plot = plotService.configurePlot(plotId, irrigationId);
        if (plot == null){
            return ResponseEntity.badRequest().body("Invalid plotId or irrigationId");
        } else {
            return ResponseEntity.ok("Plot configured");
        }
    }

    @GetMapping("/plot/{plotId}/times")
    public ResponseEntity<List<IrrigationTimeTable>> getTimeSlotsForPlot(@PathVariable int plotId) {
        Optional<Plot> optionalPlot = plotService.findByID(plotId);

        if (optionalPlot.isPresent()) {
            Plot plot = optionalPlot.get();
            List<IrrigationTimeTable> irrigationTimeTables = plot.getIrrigationTimeTables();
            return ResponseEntity.ok(irrigationTimeTables);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/times")
    public List<IrrigationTimeTable> findAllTimeSlots(){
        return irrigationService.findAll();
    }

    @GetMapping("/times/{timeId}")
    public Optional<IrrigationTimeTable> findIrrigationTimeSlotById(@PathVariable int timeId){
        Optional<IrrigationTimeTable> irrigationTime = irrigationService.findByID(timeId);
        if (irrigationTime == null){
            throw new RuntimeException("Irrigation Id not found - " + timeId);
        }
        return irrigationTime;
    }

    @PostMapping("/times")
    public IrrigationTimeTable addIrrigationTimeSlot(@RequestBody IrrigationTimeTable irrigationTimeTable){
        irrigationTimeTable.setId(0);
        IrrigationTimeTable theIrrigationTime = irrigationService.save(irrigationTimeTable);
        return theIrrigationTime;
    }

    @PutMapping("/times")
    public IrrigationTimeTable updateIrrigationTimeSlot(@RequestBody IrrigationTimeTable irrigationTimeTable){
        IrrigationTimeTable theIrrigationTime = irrigationService.save(irrigationTimeTable);
        return theIrrigationTime;
    }

    @DeleteMapping("/times/{timeId}")
    public String deleteIrrigationTimeSlot(@PathVariable int timeId){
        Optional<IrrigationTimeTable> irrigationTime = irrigationService.findByID(timeId);
        if (irrigationTime == null){
            throw new RuntimeException("TimeSlot Id not found - " + timeId);
        }
        plotService.deleteById(timeId);

        return "TimeSlot deleted - " + timeId;
    }

    @GetMapping("/irrigate/{plotId}")
    public void irrigatePlot(@PathVariable int plotId){
        irrigationService.irrigatePlot(plotId);
    }
}
