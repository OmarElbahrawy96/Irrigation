package com.oe.irrigation.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Plot {

    //define fields
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "supervisor_name")
    private String supervisor_name;

    @Column(name = "supervisor_email")
    private String supervisor_email;

    @Column(name = "soil_type")
    private String soil_type;

    @Column(name = "longitude")
    private float longitude;

    @Column(name = "latitude")
    private float latitude;

    @Column(name = "length")
    private float length;

    @Column(name = "width")
    private float width;

    private float area;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "plot_irrigation",
            joinColumns = @JoinColumn(name = "plot_id"),
            inverseJoinColumns = @JoinColumn(name = "irrigation_id"))
    private List<IrrigationTimeTable> irrigationTimeTables;


    //define constructors

    public Plot(){
    }

    public Plot(String supervisor_name, String supervisor_email, String soil_type, float longitude, float latitude, float length, float width, String status, List<IrrigationTimeTable> irrigationTimeTables) {
        this.supervisor_name = supervisor_name;
        this.supervisor_email = supervisor_email;
        this.soil_type = soil_type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.length = length;
        this.width = width;
        this.area = length * width;
        this.status = status;
        this.irrigationTimeTables = irrigationTimeTables;
    }

    public Plot(String supervisor_name, String supervisor_email, String soil_type, float longitude, float latitude, float length, float width, String status) {
        this.supervisor_name = supervisor_name;
        this.supervisor_email = supervisor_email;
        this.soil_type = soil_type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.length = length;
        this.width = width;
        this.area = length * width;
        this.status = status;
    }

    //define setters & getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSoil_type() {
        return soil_type;
    }

    public void setSoil_type(String soil_type) {
        this.soil_type = soil_type;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getArea() {
        return length*width;
    }

    public void setArea(float area) {
        this.area = area;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<IrrigationTimeTable> getIrrigationTimeTables() {
        return irrigationTimeTables;
    }

    public void setIrrigationTimeTables(List<IrrigationTimeTable> irrigationTimeTables) {
        this.irrigationTimeTables = irrigationTimeTables;
    }

    public String getSupervisorName() {
        return supervisor_name;
    }

    public void setSupervisorName(String supervisor_name) {
        this.supervisor_name = supervisor_name;
    }

    public String getSupervisorEmail() {
        return supervisor_email;
    }

    public void setSupervisorEmail(String supervisor_email) {
        this.supervisor_email = supervisor_email;
    }

    //define methods

    @Override
    public String toString() {
        return "Plot{" +
                "id=" + id +
                ", soil_type='" + soil_type + '\'' +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", length=" + length +
                ", width=" + width +
                ", area=" + area +
                ", status='" + status + '\'' +
                ", irrigationTimeTables=" + irrigationTimeTables +
                '}';
    }

}
