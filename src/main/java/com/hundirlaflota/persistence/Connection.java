package com.hundirlaflota.persistence;

import javax.persistence.*;

@Entity
@Table(name = "connection")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "distance", nullable = false)
    private double distance;

    @ManyToOne
    @JoinColumn(name = "origin_id", nullable = false)
    private Port origin;

    @ManyToOne
    @JoinColumn(name = "destination_id", nullable = false)
    private Port destination;

    public Connection() { }

    public Connection(Port origin, Port destination, double distance) {
        this.origin = origin;
        this.destination = destination;
        this.distance = distance;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public double getDistance() {
        return distance;
    }

    public Port getOrigin() {
        return origin;
    }

    public Port getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "Connection{" +
                "id=" + id +
                ", distance=" + distance +
                ", origin=" + origin.getName() +
                ", destination=" + destination.getName() +
                '}';
    }
}
