import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages employee operations including CRUD functionality.
 * This class follows clean code principles with proper separation of concerns,
 * single responsibility, and clear method naming.
 */
public class EmployeeManager {
    private final Map<Integer, Employee> employees;
    private int nextId;

    /**
     * Constructor initializes the employee storage and ID counter.
     */
    public EmployeeManager() {
        this.employees = new HashMap<>();
        this.nextId = 1;
    }

    /**
     * Creates a new employee and adds them to the system.
     * 
     * @param firstName  Employee's first name
     * @param lastName   Employee's last name
     * @param email      Employee's email address
     * @param department Employee's department
     * @param salary     Employee's salary
     * @param hireDate   Employee's hire date
     * @return The created Employee object
     * @throws IllegalArgumentException if validation fails
     */
    public Employee createEmployee(String firstName, String lastName, String email, 
                                 String department, double salary, LocalDate hireDate) {
        validateUniqueEmail(email);
        
        Employee employee = new Employee(nextId++, firstName, lastName, email, 
                                       department, salary, hireDate);
        employees.put(employee.getId(), employee);
        return employee;
    }

    /**
     * Retrieves an employee by their ID.
     * 
     * @param id The employee ID to search for
     * @return Optional containing the employee if found, empty otherwise
     */
    public Optional<Employee> getEmployeeById(int id) {
        return Optional.ofNullable(employees.get(id));
    }

    /**
     * Retrieves an employee by their email address.
     * 
     * @param email The email address to search for
     * @return Optional containing the employee if found, empty otherwise
     */
    public Optional<Employee> getEmployeeByEmail(String email) {
        if (email == null) return Optional.empty();
        
        return employees.values().stream()
                .filter(emp -> emp.getEmail().equalsIgnoreCase(email.trim()))
                .findFirst();
    }

    /**
     * Retrieves all active employees.
     * 
     * @return List of all active employees
     */
    public List<Employee> getAllActiveEmployees() {
        return employees.values().stream()
                .filter(Employee::isActive)
                .collect(Collectors.toList());
    }

    /**
     * Retrieves all employees (active and inactive).
     * 
     * @return List of all employees
     */
    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees.values());
    }

    /**
     * Retrieves employees by department.
     * 
     * @param department The department to filter by
     * @return List of employees in the specified department
     */
    public List<Employee> getEmployeesByDepartment(String department) {
        if (department == null) return new ArrayList<>();
        
        return employees.values().stream()
                .filter(emp -> emp.getDepartment().equalsIgnoreCase(department.trim()))
                .filter(Employee::isActive)
                .collect(Collectors.toList());
    }

    /**
     * Updates an existing employee's information.
     * 
     * @param id         The ID of the employee to update
     * @param firstName  New first name (null to keep current)
     * @param lastName   New last name (null to keep current)
     * @param email      New email (null to keep current)
     * @param department New department (null to keep current)
     * @param salary     New salary (null to keep current)
     * @return true if employee was updated, false if not found
     * @throws IllegalArgumentException if validation fails
     */
    public boolean updateEmployee(int id, String firstName, String lastName, 
                                String email, String department, Double salary) {
        Employee employee = employees.get(id);
        if (employee == null) {
            return false;
        }

        // Validate email uniqueness if changing email
        if (email != null && !email.equalsIgnoreCase(employee.getEmail())) {
            validateUniqueEmail(email);
        }

        // Update fields if provided
        if (firstName != null) employee.setFirstName(firstName);
        if (lastName != null) employee.setLastName(lastName);
        if (email != null) employee.setEmail(email);
        if (department != null) employee.setDepartment(department);
        if (salary != null) employee.setSalary(salary);

        return true;
    }

    /**
     * Deactivates an employee (soft delete).
     * 
     * @param id The ID of the employee to deactivate
     * @return true if employee was deactivated, false if not found
     */
    public boolean deactivateEmployee(int id) {
        Employee employee = employees.get(id);
        if (employee == null) {
            return false;
        }
        
        employee.deactivate();
        return true;
    }

    /**
     * Activates an employee.
     * 
     * @param id The ID of the employee to activate
     * @return true if employee was activated, false if not found
     */
    public boolean activateEmployee(int id) {
        Employee employee = employees.get(id);
        if (employee == null) {
            return false;
        }
        
        employee.activate();
        return true;
    }

    /**
     * Permanently removes an employee from the system.
     * 
     * @param id The ID of the employee to remove
     * @return true if employee was removed, false if not found
     */
    public boolean removeEmployee(int id) {
        return employees.remove(id) != null;
    }

    /**
     * Searches employees by name (first name or last name contains the search term).
     * 
     * @param searchTerm The term to search for in names
     * @return List of employees matching the search criteria
     */
    public List<Employee> searchEmployeesByName(String searchTerm) {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            return new ArrayList<>();
        }
        
        String lowerSearchTerm = searchTerm.trim().toLowerCase();
        return employees.values().stream()
                .filter(Employee::isActive)
                .filter(emp -> emp.getFirstName().toLowerCase().contains(lowerSearchTerm) ||
                              emp.getLastName().toLowerCase().contains(lowerSearchTerm) ||
                              emp.getFullName().toLowerCase().contains(lowerSearchTerm))
                .collect(Collectors.toList());
    }

    /**
     * Gets employees with salary in a specific range.
     * 
     * @param minSalary Minimum salary (inclusive)
     * @param maxSalary Maximum salary (inclusive)
     * @return List of employees within the salary range
     */
    public List<Employee> getEmployeesBySalaryRange(double minSalary, double maxSalary) {
        if (minSalary > maxSalary) {
            throw new IllegalArgumentException("Minimum salary cannot be greater than maximum salary");
        }
        
        return employees.values().stream()
                .filter(Employee::isActive)
                .filter(emp -> emp.getSalary() >= minSalary && emp.getSalary() <= maxSalary)
                .collect(Collectors.toList());
    }

    /**
     * Gets the total number of active employees.
     * 
     * @return Count of active employees
     */
    public int getActiveEmployeeCount() {
        return (int) employees.values().stream()
                .filter(Employee::isActive)
                .count();
    }

    /**
     * Gets the total number of employees (active and inactive).
     * 
     * @return Total count of employees
     */
    public int getTotalEmployeeCount() {
        return employees.size();
    }

    /**
     * Calculates the average salary of all active employees.
     * 
     * @return Average salary, or 0.0 if no active employees
     */
    public double getAverageSalary() {
        return employees.values().stream()
                .filter(Employee::isActive)
                .mapToDouble(Employee::getSalary)
                .average()
                .orElse(0.0);
    }

    /**
     * Gets department statistics (employee count per department).
     * 
     * @return Map of department names to employee counts
     */
    public Map<String, Long> getDepartmentStatistics() {
        return employees.values().stream()
                .filter(Employee::isActive)
                .collect(Collectors.groupingBy(
                    Employee::getDepartment,
                    Collectors.counting()
                ));
    }

    /**
     * Validates that an email is unique in the system.
     * 
     * @param email The email to validate
     * @throws IllegalArgumentException if email already exists
     */
    private void validateUniqueEmail(String email) {
        if (email == null) return;
        
        boolean emailExists = employees.values().stream()
                .anyMatch(emp -> emp.getEmail().equalsIgnoreCase(email.trim()));
        
        if (emailExists) {
            throw new IllegalArgumentException("Email address already exists in the system");
        }
    }

    /**
     * Clears all employees from the system (for testing purposes).
     */
    public void clearAllEmployees() {
        employees.clear();
        nextId = 1;
    }
}
