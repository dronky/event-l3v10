package ru.miit.lab3events;

import ru.miit.lab3events.annotation.Added;
import ru.miit.lab3events.annotation.Changed;

import javax.enterprise.event.Observes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

import static ru.miit.lab3events.Lab3Start.container;

/**
 * Created by Sinky on 19.03.2017.
 */
public class GraphLoop {
    private static int loops = 0;
    private static Integer[][] graph;
    private static int si;

    public int calculateAdded(@Observes @Added Integer[][] graph) {
        loops = 0;
        this.graph = graph;
        si = graph.length;
        cntLoop();
        System.out.println("Loops: " + loops);
        nodesDegree();
        return loops;
    }

    public int calculateChanged(@Observes @Changed Integer[] a) {
        cntLoop();
        System.out.println("Loops: " + loops);
        nodesDegree();
        return loops;
    }

    private void cntLoop() {
        loops = 0;
        for (int i = 0; i < si; i++) {
            if (graph[i][i] == 1) {
                loops += 1;
            }
        }
    }

    private void nodesDegree() {
        int sum;
        List<Integer> nodes = new ArrayList();
        for (int j = 0; j < graph.length; j++) {
            sum = 0;
            for (int i = 0; i < graph.length; i++) {
                sum += graph[i][j];
            }
            nodes.add(sum);
        }
        System.out.println("Graph degree: " + Collections.max(nodes));
    }
}
