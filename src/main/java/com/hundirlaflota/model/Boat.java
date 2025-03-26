package com.hundirlaflota.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "boat")
public class Boat {

    @Id
    @Column(name = "boat_id")
    private int boatId;

    @Column(name = "number")
    private int number;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "level")
    private int level;

    @ManyToMany
    @JoinTable(
            name = "boat_type",
            joinColumns = @JoinColumn(name = "boat_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id")
    )
    private Set<Type> types = new HashSet<>();

    public Boat() { }

    public Boat(int boatId, int number, String name, int level) {
        this.boatId = boatId;
        this.number = number;
        this.name = name;
        this.level = level;
    }

    // Getters y setters
    public int getBoatId() {
        return boatId;
    }

    public void setBoatId(int boatId) {
        this.boatId = boatId;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Set<Type> getTypes() {
        return types;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    @Override
    public String toString() {
        return "Boat{" +
                "boatId=" + boatId +
                ", number=" + number +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", types=" + types +
                '}';
    }
}
