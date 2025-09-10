import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main application class for the Employee Management System.
 * Provides a console-based interface for managing employees with clean code principles.
 */
public class App {
    private static final Scanner scanner = new Scanner(System.in);
    private static final EmployeeManager employeeManager = new EmployeeManager();
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        System.out.println("=== Employee Management System ===");
        System.out.println("Welcome to the Clean Code Employee Management System!");
        
        // Add some sample data for demonstration
        initializeSampleData();
        
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    createNewEmployee();
                    break;
                case 2:
                    viewAllEmployees();
                    break;
                case 3:
                    searchEmployee();
                    break;
                case 4:
                    updateEmployee();
                    break;
                case 5:
                    deactivateEmployee();
                    break;
                case 6:
                    viewDepartmentStatistics();
                    break;
                case 7:
                    viewSalaryStatistics();
                    break;
                case 8:
                    searchByDepartment();
                    break;
                case 9:
                    searchBySalaryRange();
                    break;
                case 0:
                    running = false;
                    System.out.println("Thank you for using the Employee Management System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            
            if (running) {
                System.out.println("\nPress Enter to continue...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }

    /**
     * Displays the main menu options.
     */
    private static void displayMainMenu() {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("EMPLOYEE MANAGEMENT SYSTEM - MAIN MENU");
        System.out.println("=".repeat(50));
        System.out.println("1. Create New Employee");
        System.out.println("2. View All Active Employees");
        System.out.println("3. Search Employee by ID/Email");
        System.out.println("4. Update Employee Information");
        System.out.println("5. Deactivate Employee");
        System.out.println("6. View Department Statistics");
        System.out.println("7. View Salary Statistics");
        System.out.println("8. Search by Department");
        System.out.println("9. Search by Salary Range");
        System.out.println("0. Exit");
        System.out.println("=".repeat(50));
    }

    /**
     * Creates a new employee with user input validation.
     */
    private static void createNewEmployee() {
        System.out.println("\n--- Create New Employee ---");
        
        try {
            String firstName = getStringInput("Enter first name: ");
            String lastName = getStringInput("Enter last name: ");
            String email = getStringInput("Enter email: ");
            String department = getStringInput("Enter department: ");
            double salary = getDoubleInput("Enter salary: ");
            LocalDate hireDate = getDateInput("Enter hire date (yyyy-MM-dd): ");
            
            Employee employee = employeeManager.createEmployee(firstName, lastName, email, 
                                                             department, salary, hireDate);
            
            System.out.println("\n✓ Employee created successfully!");
            System.out.println(employee);
            
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("✗ An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Displays all active employees in a formatted table.
     */
    private static void viewAllEmployees() {
        System.out.println("\n--- All Active Employees ---");
        
        List<Employee> employees = employeeManager.getAllActiveEmployees();
        
        if (employees.isEmpty()) {
            System.out.println("No active employees found.");
            return;
        }
        
        displayEmployeeTable(employees);
        System.out.println("\nTotal active employees: " + employees.size());
    }

    /**
     * Searches for an employee by ID or email.
     */
    private static void searchEmployee() {
        System.out.println("\n--- Search Employee ---");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Email");
        System.out.println("3. Search by Name");
        
        int choice = getIntInput("Enter search type: ");
        
        switch (choice) {
            case 1:
                searchById();
                break;
            case 2:
                searchByEmail();
                break;
            case 3:
                searchByName();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    /**
     * Searches for an employee by ID.
     */
    private static void searchById() {
        int id = getIntInput("Enter employee ID: ");
        Optional<Employee> employee = employeeManager.getEmployeeById(id);
        
        if (employee.isPresent()) {
            System.out.println("\n✓ Employee found:");
            displayEmployeeDetails(employee.get());
        } else {
            System.out.println("✗ Employee with ID " + id + " not found.");
        }
    }

    /**
     * Searches for an employee by email.
     */
    private static void searchByEmail() {
        String email = getStringInput("Enter employee email: ");
        Optional<Employee> employee = employeeManager.getEmployeeByEmail(email);
        
        if (employee.isPresent()) {
            System.out.println("\n✓ Employee found:");
            displayEmployeeDetails(employee.get());
        } else {
            System.out.println("✗ Employee with email '" + email + "' not found.");
        }
    }

    /**
     * Searches for employees by name.
     */
    private static void searchByName() {
        String searchTerm = getStringInput("Enter name to search: ");
        List<Employee> employees = employeeManager.searchEmployeesByName(searchTerm);
        
        if (employees.isEmpty()) {
            System.out.println("✗ No employees found matching '" + searchTerm + "'.");
        } else {
            System.out.println("\n✓ Found " + employees.size() + " employee(s):");
            displayEmployeeTable(employees);
        }
    }

    /**
     * Updates an existing employee's information.
     */
    private static void updateEmployee() {
        System.out.println("\n--- Update Employee ---");
        
        int id = getIntInput("Enter employee ID to update: ");
        Optional<Employee> employeeOpt = employeeManager.getEmployeeById(id);
        
        if (!employeeOpt.isPresent()) {
            System.out.println("✗ Employee with ID " + id + " not found.");
            return;
        }
        
        Employee employee = employeeOpt.get();
        System.out.println("\nCurrent employee information:");
        displayEmployeeDetails(employee);
        
        System.out.println("\nEnter new values (press Enter to keep current value):");
        
        try {
            String firstName = getOptionalStringInput("First name [" + employee.getFirstName() + "]: ");
            String lastName = getOptionalStringInput("Last name [" + employee.getLastName() + "]: ");
            String email = getOptionalStringInput("Email [" + employee.getEmail() + "]: ");
            String department = getOptionalStringInput("Department [" + employee.getDepartment() + "]: ");
            Double salary = getOptionalDoubleInput("Salary [" + employee.getSalary() + "]: ");
            
            boolean updated = employeeManager.updateEmployee(id, firstName, lastName, 
                                                           email, department, salary);
            
            if (updated) {
                System.out.println("\n✓ Employee updated successfully!");
                displayEmployeeDetails(employeeManager.getEmployeeById(id).get());
            } else {
                System.out.println("✗ Failed to update employee.");
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Deactivates an employee.
     */
    private static void deactivateEmployee() {
        System.out.println("\n--- Deactivate Employee ---");
        
        int id = getIntInput("Enter employee ID to deactivate: ");
        Optional<Employee> employeeOpt = employeeManager.getEmployeeById(id);
        
        if (!employeeOpt.isPresent()) {
            System.out.println("✗ Employee with ID " + id + " not found.");
            return;
        }
        
        Employee employee = employeeOpt.get();
        displayEmployeeDetails(employee);
        
        String confirm = getStringInput("\nAre you sure you want to deactivate this employee? (yes/no): ");
        
        if (confirm.equalsIgnoreCase("yes") || confirm.equalsIgnoreCase("y")) {
            boolean deactivated = employeeManager.deactivateEmployee(id);
            if (deactivated) {
                System.out.println("✓ Employee deactivated successfully.");
            } else {
                System.out.println("✗ Failed to deactivate employee.");
            }
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    /**
     * Displays department statistics.
     */
    private static void viewDepartmentStatistics() {
        System.out.println("\n--- Department Statistics ---");
        
        Map<String, Long> stats = employeeManager.getDepartmentStatistics();
        
        if (stats.isEmpty()) {
            System.out.println("No department data available.");
            return;
        }
        
        System.out.printf("%-20s %s%n", "Department", "Employee Count");
        System.out.println("-".repeat(35));
        
        stats.entrySet().stream()
             .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
             .forEach(entry -> System.out.printf("%-20s %d%n", entry.getKey(), entry.getValue()));
        
        System.out.println("\nTotal active employees: " + employeeManager.getActiveEmployeeCount());
    }

    /**
     * Displays salary statistics.
     */
    private static void viewSalaryStatistics() {
        System.out.println("\n--- Salary Statistics ---");
        
        double avgSalary = employeeManager.getAverageSalary();
        int activeCount = employeeManager.getActiveEmployeeCount();
        
        System.out.printf("Total active employees: %d%n", activeCount);
        System.out.printf("Average salary: $%.2f%n", avgSalary);
    }

    /**
     * Searches employees by department.
     */
    private static void searchByDepartment() {
        System.out.println("\n--- Search by Department ---");
        
        String department = getStringInput("Enter department name: ");
        List<Employee> employees = employeeManager.getEmployeesByDepartment(department);
        
        if (employees.isEmpty()) {
            System.out.println("✗ No employees found in department '" + department + "'.");
        } else {
            System.out.println("\n✓ Found " + employees.size() + " employee(s) in " + department + ":");
            displayEmployeeTable(employees);
        }
    }

    /**
     * Searches employees by salary range.
     */
    private static void searchBySalaryRange() {
        System.out.println("\n--- Search by Salary Range ---");
        
        try {
            double minSalary = getDoubleInput("Enter minimum salary: ");
            double maxSalary = getDoubleInput("Enter maximum salary: ");
            
            List<Employee> employees = employeeManager.getEmployeesBySalaryRange(minSalary, maxSalary);
            
            if (employees.isEmpty()) {
                System.out.printf("✗ No employees found with salary between $%.2f and $%.2f.%n", 
                                minSalary, maxSalary);
            } else {
                System.out.printf("\n✓ Found %d employee(s) with salary between $%.2f and $%.2f:%n", 
                                employees.size(), minSalary, maxSalary);
                displayEmployeeTable(employees);
            }
            
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
        }
    }

    /**
     * Displays employees in a formatted table.
     */
    private static void displayEmployeeTable(List<Employee> employees) {
        System.out.printf("%n%-4s %-15s %-15s %-25s %-15s %-10s %-8s%n", 
                         "ID", "First Name", "Last Name", "Email", "Department", "Salary", "Active");
        System.out.println("-".repeat(95));
        
        for (Employee emp : employees) {
            System.out.printf("%-4d %-15s %-15s %-25s %-15s $%-9.2f %-8s%n",
                            emp.getId(), emp.getFirstName(), emp.getLastName(), 
                            emp.getEmail(), emp.getDepartment(), emp.getSalary(),
                            emp.isActive() ? "Yes" : "No");
        }
    }

    /**
     * Displays detailed information for a single employee.
     */
    private static void displayEmployeeDetails(Employee employee) {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("Employee Details");
        System.out.println("-".repeat(40));
        System.out.printf("ID: %d%n", employee.getId());
        System.out.printf("Name: %s%n", employee.getFullName());
        System.out.printf("Email: %s%n", employee.getEmail());
        System.out.printf("Department: %s%n", employee.getDepartment());
        System.out.printf("Salary: $%.2f%n", employee.getSalary());
        System.out.printf("Hire Date: %s%n", employee.getHireDate());
        System.out.printf("Status: %s%n", employee.isActive() ? "Active" : "Inactive");
        System.out.println("-".repeat(40));
    }

    /**
     * Initializes sample data for demonstration purposes.
     */
    private static void initializeSampleData() {
        try {
            employeeManager.createEmployee("John", "Doe", "john.doe@company.com", 
                                         "Engineering", 75000, LocalDate.of(2022, 1, 15));
            employeeManager.createEmployee("Jane", "Smith", "jane.smith@company.com", 
                                         "Marketing", 65000, LocalDate.of(2022, 3, 20));
            employeeManager.createEmployee("Mike", "Johnson", "mike.johnson@company.com", 
                                         "Engineering", 80000, LocalDate.of(2021, 11, 10));
            employeeManager.createEmployee("Sarah", "Wilson", "sarah.wilson@company.com", 
                                         "HR", 60000, LocalDate.of(2023, 2, 5));
        } catch (Exception e) {
            System.out.println("Warning: Could not initialize sample data: " + e.getMessage());
        }
    }

    // Input utility methods
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    private static String getOptionalStringInput(String prompt) {
        String input = getStringInput(prompt);
        return input.isEmpty() ? null : input;
    }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static double getDoubleInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static Double getOptionalDoubleInput(String prompt) {
        String input = getStringInput(prompt);
        if (input.isEmpty()) return null;
        
        try {
            return Double.parseDouble(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Keeping current value.");
            return null;
        }
    }

    private static LocalDate getDateInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine().trim();
                return LocalDate.parse(input, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println("Please enter date in format yyyy-MM-dd (e.g., 2023-01-15).");
            }
        }
    }
}
