public class Main {
    public static void main(String[] args) {
        Enterprise enterprise = new Enterprise();

        Department hrDepartment = new Department("HR");
        Department itDepartment = new Department("IT");

        Employee employee1 = new Employee("Иван Иванов", 30, 50000);
        Employee employee2 = new Employee("Мария Смирнова", 25, 55000);
        Employee employee3 = new Employee("Петр Петров", 28, 52000);
        Employee employee4 = new Employee("Елена Козлова", 27, 53000);

        hrDepartment.addEmployee(employee1);
        hrDepartment.addEmployee(employee2);
        itDepartment.addEmployee(employee3);
        itDepartment.addEmployee(employee4);

        enterprise.addDepartment(hrDepartment);
        enterprise.addDepartment(itDepartment);

        for (Department department : enterprise.getDepartments()) {
            System.out.println("Отдел: " + department.getName());
            System.out.println("Количество сотрудников: " + department.getNumberOfEmployees());
            System.out.println("Сотрудники в отделе:");
            for (Employee employee : department.getEmployees()) {
                System.out.println("  ФИО: " + employee.getName());
                System.out.println("  Возраст: " + employee.getAge());
                System.out.println("  ЗП: " + employee.getSalary());
            }
            double salarySum = department.getSalarySum();
            System.out.println("Сумма зарплаты в отделе: " + salarySum);
            System.out.println();
        }
    }
}
