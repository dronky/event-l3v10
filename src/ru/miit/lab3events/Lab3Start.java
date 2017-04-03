package ru.miit.lab3events;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


/**
 * Created by Андрей on 18.02.2017.
 */
public class Lab3Start {
    protected static Weld weld;
    protected static WeldContainer container;

    public static void main(String args[]) throws Exception {
        String FILE_NAME = "G:\\dropbox\\Dropbox\\Гребаная учеба\\4курс\\2с\\кис\\Lab3Evens\\Lab3Evens — копия\\src\\ru\\miit\\lab3events\\graph.txt";
        java.util.List<String> stringList = Files.readAllLines(Paths.get(FILE_NAME), StandardCharsets.UTF_8);
        String[] stringArray = stringList.toArray(new String[]{});
        int size[] = strArrChecker(stringArray);
        int si = size[0];
        int sj = size[1];
        Integer[][] graph = convertToIntArr(stringArray, si, sj);

        weld = new Weld();
        container = weld.initialize();
        try {
            GraphManager graphManager = container.instance().select(GraphManager.class).get();
            graphManager.addGraph(graph);
            graphManager.changeGraph(new Integer[]{1, 1, 0});
            graphManager.graph_toStr();
        } finally {
            weld.shutdown();
        }
    }

    private static int[] strArrChecker(String[] stringArray) {
        boolean lampa;
        int[] size = new int[2];
        int tmp = 0, prevTmp = 0;
        //If Array filled
        if (stringArray.length != 0) {
            lampa = true;
        } else lampa = false;

        for (int n = 0; n < stringArray.length && lampa; n++) {
            Scanner scanner = new Scanner(stringArray[n]);
            try {
                while (scanner.hasNext()) {
                    tmp++;
                    scanner.nextInt();
                }
            } catch (InputMismatchException e) {
                System.out.println("input file broken");
                lampa = false;
            }
            if (n == 0) {
                prevTmp = tmp;
                size[0] = (tmp - 1);
                size[1] = (stringArray.length - 1);
            } else if (!scanner.hasNext() && prevTmp != tmp) {
                lampa = false;
                System.out.println("input file not have valid cols and rows");
            }
            tmp = 0;
        }
        return size;
    }

    private static Integer[][] convertToIntArr(String[] stringArray, int si, int sj) {
        Integer[][] intArray = new Integer[si + 1][sj + 1];
        for (int i = 0; i <= si; i++) {
            Scanner scanner = new Scanner(stringArray[i]);
            for (int j = 0; j <= sj; j++) {
                intArray[i][j] = scanner.nextInt();
            }
        }
        return intArray;
    }
}
