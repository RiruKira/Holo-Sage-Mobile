package com.example.pfaloginv2.beans;

import java.util.List;

public class Shape {
    private List<Double> coords;

    public Shape() {
    }

    public Shape(List<Double> coords) {
        this.coords = coords;
    }


    public List<Double> getCoords() {
        return coords;
    }

    public void setCoords(List<Double> coords) {
        this.coords = coords;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "Doubles=" + coords +
                '}';
    }
}
