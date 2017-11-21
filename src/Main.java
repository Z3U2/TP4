import java.io.File;

public class Main {

    public static void main(String[] args) {
        Register reg;
        reg = new Register();
        //String path = INSERT PATH TO FILE HERE
        //File f = new File(path);
        //reg = Register.regFromFile(f);
        reg.add( new Employee("e1",10000));
        reg.add( new Employee("e2",20000));
        reg.add( new Employee("e3",15000));
        reg.add( new Employee("e4",30000));
        System.out.println(reg);
        reg.codeSort();
        System.out.println(reg);
        reg.salarySort();
        System.out.println(reg);
        System.out.println(reg.salaryHigherThan(15000));
        System.out.println(reg.updateSalaryLowerThan(15000,2,false));
        System.out.println(reg);
    }
}
