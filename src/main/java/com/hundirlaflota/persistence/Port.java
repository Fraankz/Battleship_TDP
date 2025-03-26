package com.hundirlaflota.persistence;

import javax.persistence.*;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "port")
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    // Relación bidireccional: opcional para consultar conexiones
    @OneToMany(mappedBy = "origin", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Connection> outgoingConnections = new HashSet<>();

    public Port() { }

    public Port(String name) {
        this.name = name;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Connection> getOutgoingConnections() {
        return outgoingConnections;
    }

    @Override
    public String toString() {
        return "Port{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
