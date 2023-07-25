package com.oe.irrigation.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class IrrigationTimeTable {

    //define fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "amount_of_water")
    private float amount_of_water;

    @Column(name = "irrigation_datetime")
    private LocalDateTime eventDatetime;

    @ManyToMany(mappedBy = "irrigationTimeTables")
    private List<Plot> plots;

    //define constructors

    public IrrigationTimeTable(){
    }

    public IrrigationTimeTable(float amount_of_water, LocalDateTime eventDatetime, List<Plot> plots) {
        this.amount_of_water = amount_of_water;
        this.eventDatetime = eventDatetime;
        this.plots = plots;
    }

    //define setters & getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getAmount_of_water() {
        return amount_of_water;
    }

    public void setAmount_of_water(float amount_of_water) {
        this.amount_of_water = amount_of_water;
    }

    public LocalDateTime getEventDatetime() {
        return eventDatetime;
    }

    public void setEventDatetime(LocalDateTime eventDatetime) {
        this.eventDatetime = eventDatetime;
    }

    public List<Plot> getPlots() {
        return plots;
    }

    public void setPlots(List<Plot> plots) {
        this.plots = plots;
    }

    //define methods

    @Override
    public String toString() {
        return "IrrigationTimeTable{" +
                "id=" + id +
                ", amount_of_water=" + amount_of_water +
                ", eventDatetime=" + eventDatetime +
                ", plots=" + plots +
                '}';
    }
}
