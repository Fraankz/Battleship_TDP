package com.hundirlaflota.graph;

public class Edge {
    private String destination;
    private double distance;

    public Edge(String destination, double distance) {
        this.destination = destination;
        this.distance = distance;
    }

    public String getDestination() {
        return destination;
    }

    public double getDistance() {
        return distance;
    }

    @Override
    public String toString() {
        return "Edge{destination='" + destination + "', distance=" + distance + "}";
    }
}
