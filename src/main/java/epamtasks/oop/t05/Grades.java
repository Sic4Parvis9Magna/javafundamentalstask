package epamtasks.oop.t05;

import java.util.*;

public class Grades {

   private Map<Discipline,List<Double>> gradeMap;

    Grades(){
        gradeMap = new HashMap<>();

    }

    public Grades addGrade(Discipline discipline,Double grade){
        if(!gradeMap.containsKey(discipline)){
           this.addDiscipline(discipline);
        }
        gradeMap.get(discipline).add(grade);
        return this;
    }
    public Grades addGrade(Discipline discipline,Integer grade){

        this.addGrade(discipline,grade.doubleValue());
        return this;
    }
    private boolean addDiscipline(Discipline discipline){
        if(gradeMap.containsKey(discipline)){return false;
        }else{
            gradeMap.put(discipline,new ArrayList<Double>());
            return true;
        }

    }

    public double getMeanGrade(Discipline discipline){
        double meanGrade = 0;
        List<Double> grades = gradeMap.get(discipline);
        for (Double gradeVal:grades){
             meanGrade += gradeVal.doubleValue();
        }
        return meanGrade/grades.size();
    }
    public Map<Discipline, List<Double>> getGradeMap() {
        return gradeMap;
    }

}
