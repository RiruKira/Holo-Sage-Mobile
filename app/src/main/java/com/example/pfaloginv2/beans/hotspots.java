package com.example.pfaloginv2.beans;

public class hotspots {
    private String name;
    private Shape shape;

    public hotspots() {
    }

    public hotspots(String name, Shape shape) {
        this.name = name;
        this.shape = shape;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    @Override
    public String toString() {
        return "hotspots{" +
                "name='" + name + '\'' +
                ", shape=" + shape +
                '}';
    }
}
