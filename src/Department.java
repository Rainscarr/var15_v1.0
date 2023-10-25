import java.util.ArrayList;
import java.util.List;

class Department {
    private String name;
    private int numberOfEmployees;
    private List<Employee> employees;

    public Department(String name) {
        this.name = name;
        this.numberOfEmployees = 0;
        this.employees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
        numberOfEmployees++;
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
        numberOfEmployees--;
    }

    public double getSalarySum() {
        double sum = 0;
        for (Employee employee : employees) {
            sum += employee.getSalary();
        }
        return sum;
    }
}
