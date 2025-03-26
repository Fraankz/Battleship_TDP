package com.hundirlaflota.service;

import com.hundirlaflota.graph.Graph;
import com.hundirlaflota.persistence.Connection;
import com.hundirlaflota.persistence.Port;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class GraphService {

    private EntityManagerFactory emf;
    private EntityManager em;

    public GraphService() {
        // "BattleshipPU" es el nombre de la unidad de persistencia definida en persistence.xml
        emf = Persistence.createEntityManagerFactory("BattleshipPU");
        em = emf.createEntityManager();
    }

    public Graph loadGraph() {
        Graph graph = new Graph();
        // Cargar todos los puertos
        List<Port> ports = em.createQuery("SELECT p FROM Port p", Port.class).getResultList();
        for (Port p : ports) {
            graph.addPort(p.getName());
        }
        // Cargar todas las conexiones
        List<Connection> connections = em.createQuery("SELECT c FROM Connection c", Connection.class).getResultList();
        for (Connection c : connections) {
            // Agregar la conexión en ambos sentidos (grafo no dirigido)
            String originName = c.getOrigin().getName();
            String destName = c.getDestination().getName();
            graph.addEdge(originName, destName, c.getDistance());
        }
        return graph;
    }

    public void close() {
        if (em != null) em.close();
        if (emf != null) emf.close();
    }
}
