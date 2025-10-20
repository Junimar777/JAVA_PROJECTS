package STUDENT_RECORD;

public class Student {

    String id;
    String name;
    double prelim,midterm,finals;

    public Student(String id, String name, double prelim,double midterm, double finals){
        this.id = id;
        this.name = name;
        this.prelim = prelim;
        this.midterm = midterm;
        this.finals =finals;
    }

    // gawa tau ng average return double
    public double getAverage(){
        return( (prelim + midterm + finals) / 3);
    }

    // gawa tau ng status kung failed ba or passed ternary pala gagmitin natin
    public String getStatus(){
        return getAverage() >= 75 ? "Passed" : "Failed";
    }

    //dito gawa tau ng bagong method sa grades para mag update
    public void UpdateGrades(double prelim,double midterm, double finals){
        this.prelim = prelim;
        this.midterm = midterm;
        this.finals = finals;
    }




    @Override
    public String toString() {
        return " ID: " + id +
                " Name:" + name +
                " Prelim: " + prelim +
                " Midterm: " + midterm +
                " Finals: " + finals +
                " Average: " + getAverage() +
                " Status: " + getStatus();

    }
}
