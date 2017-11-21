import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

public class Employee implements Cloneable,Comparable<Employee> {
    private String code;
    private double salaire;

    public Employee(){

    }

    public Employee(String code,double salaire){
        this.code = code;
        this.salaire = salaire;
    }

    public Employee(String code){
        this(code,0);
    }


    public String getCode() {
        return code;
    }

    public double getSalaire() {
        return salaire;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        else if (obj instanceof Employee) {
            return this.code.equals(((Employee) obj).code);
        }
        else {
            return false;
        }
    }

    @Override
    public int compareTo(Employee o) {
        return code.compareTo(o.code);
    }

    @Override
    public String toString() {
        return "Employé : code = " + code + " et salaire = " + salaire;
    }

    public static Comparator<Employee> sp(){
        return Comparator.comparing(Employee::getSalaire);
    }

    public static Predicate<Employee> sup(double m){
        return employee -> employee.salaire>m ;
    }

    public static Function<Employee,Employee> misaj(double x){
        return (employee -> {
            employee.salaire *= x;
            return employee;
        });
    }

    @Override
    public Employee clone() {
        return new Employee(code,salaire);
    }
}
