package com.company.Model;

import java.util.List;

/**
 * Created by Mauricio on 11/14/2015.
 */
public class FindBest {
    List<List<Period>> solutions;

    public FindBest(List<List<Period>> solutions) {
        this.solutions = solutions;
    }

    public int calcValue(List<Period> solution) {
        int rsf = 0;
        Period p0 = solution.get(0);
        for (Period next : solution) {
            int dx = Math.abs(p0.getX() - next.getX());
            int dy = Math.abs(p0.getY() - next.getY());
            rsf += dx + dy;
            p0 = next;
        }
        return rsf;
    }

    public List<Period> returnBest() {
        List<Period> best = solutions.get(0);
        for (List<Period> next : solutions) {
            if (calcValue(next) < calcValue(best)) {
                best = next;
            }
        }
        return best;
    }
}
