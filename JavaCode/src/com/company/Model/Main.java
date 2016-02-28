package com.company.Model;

import com.company.Parsers.BuildingsParser;
import com.company.Parsers.CriticalParser;
import com.company.Parsers.PeriodParser;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.company.Model.Schedule;

public class Main {
    private static final int numIterations = 1000;

    public static void main(String[] args) throws IOException, JSONException {
        //====================  define problem  ===========================
        List<Period> bank;
        bank = PeriodParser.periodsParser();
        List<Period> feasible;
        FindFeasible findFeasible = new FindFeasible(bank);
        feasible = findFeasible.feasibleResult();
        List<String> critical;
        critical = CriticalParser.criticalParser();

        ScheduleProblem problem = new ScheduleProblem(bank, feasible, critical);
        //==================== solve =====================================

        List<List<Period>> solutions = new ArrayList<List<Period>>();
        int i=0;
        while (i<numIterations){
            problem.iterator();
            List<Period> particular = new ArrayList<Period>();
            for (Period p: problem.getCurrentSchedule()){
                particular.add(p);
            }
            solutions.add(particular);
            i++;
        }
        int count = 0;
        List<Period> toCheck = solutions.get(0);
        for (List<Period> next: solutions){
            if (toCheck.equals(next)){
                count++;
            }
        }
        //=====================  output  =====================================
        FindBest look = new FindBest(solutions);
        List<Period> finalSched = look.returnBest();

        List<Building> campus = BuildingsParser.buildingsParser();
        System.out.println("Your total walking time is " + look.calcValue(finalSched) + " [units].");
        int numHours = 16;
        for (int j = 0; j < numHours; j++) {
            for (Period next : finalSched) {
                if (next.getTime().equals(j)) {
                    System.out.println("You're taking " + next.getName() + " at " + next.getTime() + " in " +
                            next.findBuilding(campus).toString() + " " + next.room + ".");
                }
            }
        }

/*
        List<Schedule> solutions = new ArrayList<Schedule>();
        while (int i = 0 < numIterations){

        }
        for (int i = 0; i < numIterations; i++) {
            problem.iterator();
            Schedule particular = new Schedule(problem.getCurrentSchedule());
            solutions.add(particular);
        }
        Schedule best = solutions.get(0);
        for (Schedule next : solutions) {
            if (next.valueCalc() < best.valueCalc()) {
                best = next;
            }
        }

        //=====================  output  =====================================
        List<Building> campus = BuildingsParser.buildingsParser();
        System.out.println("Your total walking time is " + problem.problemValue() + " [units].");
        int numHours = 16;
        for (int j = 0; j < numHours; j++) {
            for (Period next : best.sched) {
                if (next.getTime().equals(j)) {
                    System.out.println("You're taking " + next.getName() + " at " + next.getTime() + " in " +
                            next.findBuilding(campus).toString() + " " + next.room + ".");
                }
            }
        }
    }
    */
    }
}