package ru.miit.lab3events;

import ru.miit.lab3events.annotation.Added;
import ru.miit.lab3events.annotation.Changed;

import javax.inject.Inject;
import java.io.IOException;

import static ru.miit.lab3events.Lab3Start.container;

/**
 * Created by Sinky on 19.03.2017.
 */
public class GraphManager {
    @Inject
    @Added
    private javax.enterprise.event.Event<Integer[][]> graphAdded;

    @Inject
    @Changed
    private javax.enterprise.event.Event<Integer[]> graphChanged;

    public Integer[][] graph;

    public void changeGraph(Integer[] a) {
        //a[0] - posI, [1]- posJ,[2]-number
        if (graph[a[0]][a[1]] == 0 || graph[a[0]][a[1]] == 1) {
            graph[a[0]][a[1]] = a[2];
            graphChanged.fire(a);
        }
    }

    public void addGraph(Integer[][] graph) {
        if (this.graph == null) {
            this.graph = graph;
        }
        graphAdded.fire(graph);

    }

    public void graph_toStr() {
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph.length; j++)
                System.out.print(graph[i][j].toString() + ' ');
            System.out.println();
        }
    }
}
