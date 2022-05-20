package com.example.pfaloginv2.beans;

import java.util.List;

public class ListHelper {

    private static List<Coords> coords;
    private static final ListHelper instance = new ListHelper();

    public ListHelper() {
    }

    public static List<Coords> getCoords() {
        return coords;
    }

    public static void setCoords(List<Coords> coords) {
        ListHelper.coords = coords;
    }


    public static ListHelper getInstance() {
        return instance;
    }

}
