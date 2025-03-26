package com.hundirlaflota.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "type")
public class Type {

    @Id
    @Column(name = "type_id")
    private int typeId;

    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;

    @ManyToMany(mappedBy = "types")
    private Set<Boat> boats = new HashSet<>();

    public Type() { }

    public Type(int typeId, String typeName) {
        this.typeId = typeId;
        this.typeName = typeName;
    }

    // Getters y setters
    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Set<Boat> getBoats() {
        return boats;
    }

    public void setBoats(Set<Boat> boats) {
        this.boats = boats;
    }

    @Override
    public String toString() {
        return "Type{" +
                "typeId=" + typeId +
                ", typeName='" + typeName + '\'' +
                '}';
    }
}
