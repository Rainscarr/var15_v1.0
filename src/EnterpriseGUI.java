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

    private Department selectedDepartment;
    private Employee selectedEmployee;

    public EnterpriseGUI(Enterprise enterprise) {
        this.enterprise = enterprise;

        setTitle("Управление предприятием");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем панель
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        add(panel);

        // Создаем кнопку "Добавить отдел"
        JButton addDepartmentButton = new JButton("Добавить отдел");
        JButton editDepartmentButton = new JButton("Изменить отдел");
        JButton deleteDepartmentButton = new JButton("Удалить отдел");

        // Панель с кнопками управления отделами
        JPanel departmentButtonPanel = new JPanel();
        departmentButtonPanel.add(addDepartmentButton);
        departmentButtonPanel.add(editDepartmentButton);
        departmentButtonPanel.add(deleteDepartmentButton);

        // Добавляем панель с кнопками управления отделами в верхнюю часть
        panel.add(departmentButtonPanel, BorderLayout.NORTH);

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
        splitPane1.setResizeWeight(0.3);
        splitPane2.setResizeWeight(0.2);

        panel.add(splitPane2, BorderLayout.CENTER);

        // Создаем кнопку "Добавить сотрудника"
        JButton addEmployeeButton = new JButton("Добавить сотрудника");
        JButton editEmployeeButton = new JButton("Изменить сотрудника");
        JButton deleteEmployeeButton = new JButton("Удалить сотрудника");

        // Панель с кнопками управления сотрудниками
        JPanel employeeButtonPanel = new JPanel();
        employeeButtonPanel.add(addEmployeeButton);
        employeeButtonPanel.add(editEmployeeButton);
        employeeButtonPanel.add(deleteEmployeeButton);

        // Добавляем панель с кнопками управления сотрудниками в нижнюю часть
        panel.add(employeeButtonPanel, BorderLayout.SOUTH);

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

                    if (selectedDepartment != null) {
                        Employee employee = new Employee(employeeName, employeeAge, employeeSalary);
                        selectedDepartment.addEmployee(employee);
                        employeeListModel.addElement(employee);
                    }
                }
            }
        });

        // Добавляем обработчик события для кнопки "Изменить отдел"
        editDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedDepartment != null) {
                    String newDepartmentName = JOptionPane.showInputDialog(null, "Введите новое название отдела:", "Изменение отдела", JOptionPane.PLAIN_MESSAGE);
                    if (newDepartmentName != null && !newDepartmentName.isEmpty()) {
                        selectedDepartment.setName(newDepartmentName);
                        int selectedIndex = departmentList.getSelectedIndex();
                        departmentListModel.setElementAt(selectedDepartment, selectedIndex);
                    }
                }
            }
        });

        // Добавляем обработчик события для кнопки "Удалить отдел"
        deleteDepartmentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedDepartment != null) {
                    enterprise.removeDepartment(selectedDepartment);
                    departmentListModel.removeElement(selectedDepartment);
                    selectedDepartment = null;
                }
            }
        });

        // Добавляем обработчик события для кнопки "Изменить сотрудника"
        editEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedEmployee != null) {
                    String newEmployeeName = JOptionPane.showInputDialog(null, "Введите новое имя сотрудника:", "Изменение сотрудника", JOptionPane.PLAIN_MESSAGE);
                    if (newEmployeeName != null && !newEmployeeName.isEmpty()) {
                        String newEmployeeAgeString = JOptionPane.showInputDialog(null, "Введите новый возраст сотрудника:", "Изменение сотрудника", JOptionPane.PLAIN_MESSAGE);
                        int newEmployeeAge = Integer.parseInt(newEmployeeAgeString);
                        String newEmployeeSalaryString = JOptionPane.showInputDialog(null, "Введите новую зарплату сотрудника:", "Изменение сотрудника", JOptionPane.PLAIN_MESSAGE);
                        double newEmployeeSalary = Double.parseDouble(newEmployeeSalaryString);

                        selectedEmployee.setName(newEmployeeName);
                        selectedEmployee.setAge(newEmployeeAge);
                        selectedEmployee.setSalary(newEmployeeSalary);

                        int selectedIndex = employeeList.getSelectedIndex();
                        employeeListModel.setElementAt(selectedEmployee, selectedIndex);
                    }
                }
            }
        });

        // Добавляем обработчик события для кнопки "Удалить сотрудника"
        deleteEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedEmployee != null) {
                    selectedDepartment.removeEmployee(selectedEmployee);
                    employeeListModel.removeElement(selectedEmployee);
                    selectedEmployee = null;
                }
            }
        });

        // Добавляем обработчик события для выбора отдела
        departmentList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedDepartment = departmentList.getSelectedValue();
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

                    selectedEmployee = null;
                }
            }
        });

        // Добавляем обработчик события для выбора сотрудника
        employeeList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                selectedEmployee = employeeList.getSelectedValue();
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
