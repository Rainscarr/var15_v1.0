import java.util.ArrayList;
import java.util.List;

public class Enterprise {
    private List<Department> departments;

    public Enterprise() {
        departments = new ArrayList<>();
    }

    public void addDepartment(Department department) {
        departments.add(department);
    }

    public void removeDepartment(Department department) {
        departments.remove(department);
    }

    public List<Department> getDepartments() {
        return departments;
    }

    // Добавьте методы для редактирования информации о предприятии и другие необходимые методы.

    public double getSalarySumInEnterprise() {
        double sum = 0;
        for (Department department : departments) {
            sum += department.getSalarySum();
        }
        return sum;
    }
}
