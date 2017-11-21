

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Register extends LinkedList<Employee> implements Cloneable {


    static public Register regFromFile (File f){
        Register reg = new Register();
        Scanner scan;
        Scanner s;
        String code;
        double salaire;

        try {
            scan = new Scanner(f);
            while (scan.hasNextLine()){
                s = new Scanner(scan.nextLine());
                s.useLocale(Locale.FRANCE);
                if(!s.hasNext()){
                    continue;
                }
                code = s.next();
                if (s.hasNextDouble()){
                    salaire = s.nextDouble();
                    reg.add(new Employee(code,salaire));
                }
                else {
                    System.out.println("Error in file format !");
                    System.exit(1);
                }
            }
        }
        catch (FileNotFoundException e){
            System.out.println("Wrong file path !");
            System.exit(1);
        }
        return reg;


    }

    @Override
    public boolean add(Employee employee) {
        if (this.contains(employee)) {
            return false;
        } else {
            super.add(employee);
            return true;
        }
    }


    public boolean remove(String code) {
        return super.remove(new Employee(code));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.forEach(employee -> sb.append(employee).append("\n") );
        return sb.toString();
    }

    @Override
    public Register clone() {
        Register clone = new Register();
        this.forEach(employee -> clone.add(employee.clone()));
        return clone;
    }

    public void codeSort(){
        Collections.sort(this);
    }

    public Register filter(Predicate<Employee> predicate){
        return this.stream()
                .filter(predicate)
                .collect(Collectors.toCollection(Register::new));
//      Using filter, we could have done the same with an iterator/for loop/foreach
//      Example :
//        Register reg = new Register();
//        for (Employee e: this) {
//            if (predicate.test(e)){
//                reg.add(e);
//            }
//        }
//        return reg;
    }


    public Register map(Function<Employee,Employee> f){
        return this.stream()
                .map(f)
                .collect(Collectors.toCollection(Register::new));
//      Using map, we could have done the same with an iterator/for loop/foreach
//      Example :
//        for (Employee e: this) f.apply(e);
//        return this;
    }

    public void salarySort() { this.sort(Employee.sp()); }

    public Register salaryHigherThan(double m){
        return this.filter(Employee.sup(m));
    }

    /**
     * This method is to update all salaries lower than a certain number
     * @param maximumSalary the ceil of salaries that get updated
     * @param amount  the amount we're willing to update those salaries with
     * @param onClone set to true to make modifications on clone and not original Register
     * @return int This returns sum of numA and numB.
     */
    public Register updateSalaryLowerThan(double maximumSalary,double amount,boolean onClone){
        Register reg;
        reg = onClone ? this.clone() : this;
        return reg.filter(Employee.sup(maximumSalary).negate())
                .map(Employee.misaj(amount));
    }

}

