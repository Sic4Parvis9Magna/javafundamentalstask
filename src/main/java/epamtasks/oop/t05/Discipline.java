package epamtasks.oop.t05;

public enum Discipline {
    ASTRONOMY(true),BIOLOGY(true),CHEMISTRY(false),COMPUTER_SCIENCE(false),HISTORY(true),
    MATH(false),PHYSICS(false),UNKNOWN(true);

    private boolean integerGrade;
    Discipline(boolean integerGrade){this.integerGrade = integerGrade;}
  public  boolean isIntegerGrade(){return integerGrade;}

}
