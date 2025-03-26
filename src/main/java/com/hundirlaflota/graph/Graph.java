package com.hundirlaflota.graph;

import java.util.*;

public class Graph {
    // Representación interna: puerto -> lista de aristas
    private Map<String, List<Edge>> adjList = new HashMap<>();

    public void addPort(String portName) {
        adjList.putIfAbsent(portName, new ArrayList<>());
    }

    public void addEdge(String port1, String port2, double distance) {
        addPort(port1);
        addPort(port2);
        // Dado que es un grafo no dirigido, agregamos ambos sentidos
        adjList.get(port1).add(new Edge(port2, distance));
        adjList.get(port2).add(new Edge(port1, distance));
    }

    // Ejemplo de DFS
    public List<String> dfs(String start) {
        Set<String> visited = new HashSet<>();
        List<String> result = new ArrayList<>();
        dfsRecursive(start, visited, result);
        return result;
    }

    private void dfsRecursive(String current, Set<String> visited, List<String> result) {
        visited.add(current);
        result.add(current);
        for (Edge edge : adjList.getOrDefault(current, Collections.emptyList())) {
            if (!visited.contains(edge.getDestination())) {
                dfsRecursive(edge.getDestination(), visited, result);
            }
        }
    }

    // Método para calcular el camino más corto con Dijkstra
    public Map<String, Object> shortestPath(String source, String destination) {
        Map<String, Double> dist = new HashMap<>();
        Map<String, String> prev = new HashMap<>();
        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparing(dist::get));

        // Inicializar
        for (String port : adjList.keySet()) {
            dist.put(port, Double.MAX_VALUE);
            prev.put(port, null);
        }
        dist.put(source, 0.0);
        queue.add(source);

        while (!queue.isEmpty()) {
            String current = queue.poll();
            if (current.equals(destination)) break;
            for (Edge edge : adjList.getOrDefault(current, Collections.emptyList())) {
                double alt = dist.get(current) + edge.getDistance();
                if (alt < dist.get(edge.getDestination())) {
                    dist.put(edge.getDestination(), alt);
                    prev.put(edge.getDestination(), current);
                    queue.add(edge.getDestination());
                }
            }
        }

        // Reconstruir camino
        List<String> path = new ArrayList<>();
        for (String at = destination; at != null; at = prev.get(at)) {
            path.add(at);
        }
        Collections.reverse(path);

        Map<String, Object> result = new HashMap<>();
        result.put("path", path);
        result.put("distance", dist.get(destination));
        return result;
    }

    // Eliminar el puerto con mayor número de aristas
    public String removePortWithMaxEdges() {
        String portToRemove = null;
        int maxEdges = -1;
        for (Map.Entry<String, List<Edge>> entry : adjList.entrySet()) {
            if (entry.getValue().size() > maxEdges) {
                maxEdges = entry.getValue().size();
                portToRemove = entry.getKey();
            }
        }
        if (portToRemove != null) {
            // Eliminar el puerto y todas sus aristas
            adjList.remove(portToRemove);
            for (List<Edge> edges : adjList.values()) {
                String finalPortToRemove = portToRemove;
                edges.removeIf(edge -> edge.getDestination().equals(finalPortToRemove));
            }
        }
        return portToRemove;
    }

    // Método para imprimir el grafo
    public void printGraph() {
        for (String port : adjList.keySet()) {
            System.out.println(port + " -> " + adjList.get(port));
        }
    }
}
