import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EnterpriseGUI extends JFrame {
    private Enterprise enterprise;

    private DefaultListModel<Department> departmentListModel;
    private DefaultListModel<Employee> employeeListModel;

    private JList<Department> departmentList;
    private JList<Employee> employeeList;
    private JTextArea infoArea;

    public EnterpriseGUI(Enterprise enterprise) {
        this.enterprise = enterprise;

        setTitle("Управление предприятием");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем панель
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        // Создаем список отделов
        departmentListModel = new DefaultListModel<>();
        departmentList = new JList<>(departmentListModel);

        // Создаем список сотрудников
        employeeListModel = new DefaultListModel<>();
        employeeList = new JList<>(employeeListModel);

        // Создаем область для отображения информации
        infoArea = new JTextArea();
        infoArea.setEditable(false);

        // Создаем JSplitPane, чтобы разделить окно на три части
        JSplitPane splitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, new JScrollPane(departmentList), new JScrollPane(employeeList));
        JSplitPane splitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPane1, new JScrollPane(infoArea));
        splitPane1.setResizeWeight(0.2);
        splitPane2.setResizeWeight(0.5);

        panel.add(splitPane2, BorderLayout.CENTER);

        // Создаем кнопку "Добавить отдел"
        JButton addDepartmentButton = new JButton("Добавить отдел");

        // Создаем кнопку "Добавить сотрудника"
        JButton addEmployeeButton = new JButton("Добавить сотрудника");

        // Добавляем компоненты на панель
        panel.add(addDepartmentButton, BorderLayout.NORTH);
        panel.add(addEmployeeButton, BorderLayout.SOUTH);

        // Добавляем обработчик события для кнопки "Добавить отдел"
        addDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departmentName = JOptionPane.showInputDialog(null, "Введите название отдела:", "Добавление отдела", JOptionPane.PLAIN_MESSAGE);
                if (departmentName != null && !departmentName.isEmpty()) {
                    Department department = new Department(departmentName);
                    enterprise.addDepartment(department);
                    departmentListModel.addElement(department);
                }
            }
        });

        // Добавляем обработчик события для кнопки "Добавить сотрудника"
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeName = JOptionPane.showInputDialog(null, "Введите имя сотрудника:", "Добавление сотрудника", JOptionPane.PLAIN_MESSAGE);
                if (employeeName != null && !employeeName.isEmpty()) {
                    String employeeAgeString = JOptionPane.showInputDialog(null, "Введите возраст сотрудника:", "Добавление сотрудника", JOptionPane.PLAIN_MESSAGE);
                    int employeeAge = Integer.parseInt(employeeAgeString);
                    String employeeSalaryString = JOptionPane.showInputDialog(null, "Введите зарплату сотрудника:", "Добавление сотрудника", JOptionPane.PLAIN_MESSAGE);
                    double employeeSalary = Double.parseDouble(employeeSalaryString);

                    Department selectedDepartment = departmentList.getSelectedValue();
                    if (selectedDepartment != null) {
                        Employee employee = new Employee(employeeName, employeeAge, employeeSalary);
                        selectedDepartment.addEmployee(employee);
                        employeeListModel.addElement(employee);
                    }
                }
            }
        });

        // Добавляем обработчик события для выбора отдела
        departmentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Department selectedDepartment = departmentList.getSelectedValue();
                if (selectedDepartment != null) {
                    // Отображаем информацию о выбранном отделе
                    String departmentInfo = "Отдел: " + selectedDepartment.getName() + "\nКоличество сотрудников: " + selectedDepartment.getNumberOfEmployees()
                            + "\nСумма зарплаты в отделе: " + selectedDepartment.getSalarySum();
                    infoArea.setText(departmentInfo);

                    // Отображаем сотрудников выбранного отдела
                    employeeListModel.clear();
                    for (Employee employee : selectedDepartment.getEmployees()) {
                        employeeListModel.addElement(employee);
                    }
                }
            }
        });

        // Добавляем обработчик события для выбора сотрудника
        employeeList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                Employee selectedEmployee = employeeList.getSelectedValue();
                if (selectedEmployee != null) {
                    // Отображаем информацию о сотруднике
                    String employeeInfo = "ФИО: " + selectedEmployee.getName() + "\nВозраст: " + selectedEmployee.getAge() + "\nЗП: " + selectedEmployee.getSalary();
                    infoArea.setText(employeeInfo);
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Enterprise enterprise = new Enterprise();
                EnterpriseGUI gui = new EnterpriseGUI(enterprise);
                gui.setVisible(true);
            }
        });
    }
}
