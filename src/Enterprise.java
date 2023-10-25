import java.util.ArrayList;
import java.util.List;

class Enterprise {
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
}
