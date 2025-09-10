import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents an Employee entity with basic information and operations.
 * This class follows clean code principles with proper encapsulation,
 * validation, and immutable design where appropriate.
 */
public class Employee {
    private final int id;
    private String firstName;
    private String lastName;
    private String email;
    private String department;
    private double salary;
    private LocalDate hireDate;
    private boolean isActive;

    /**
     * Constructor to create a new Employee instance.
     * 
     * @param id         Unique identifier for the employee
     * @param firstName  Employee's first name (cannot be null or empty)
     * @param lastName   Employee's last name (cannot be null or empty)
     * @param email      Employee's email address (cannot be null or empty)
     * @param department Employee's department (cannot be null or empty)
     * @param salary     Employee's salary (must be non-negative)
     * @param hireDate   Employee's hire date (cannot be null)
     * @throws IllegalArgumentException if any validation fails
     */
    public Employee(int id, String firstName, String lastName, String email, 
                   String department, double salary, LocalDate hireDate) {
        validateId(id);
        validateName(firstName, "First name");
        validateName(lastName, "Last name");
        validateEmail(email);
        validateName(department, "Department");
        validateSalary(salary);
        validateHireDate(hireDate);

        this.id = id;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.email = email.trim().toLowerCase();
        this.department = department.trim();
        this.salary = salary;
        this.hireDate = hireDate;
        this.isActive = true;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireDate() {
        return hireDate;
    }

    public boolean isActive() {
        return isActive;
    }

    // Setters with validation
    public void setFirstName(String firstName) {
        validateName(firstName, "First name");
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        validateName(lastName, "Last name");
        this.lastName = lastName.trim();
    }

    public void setEmail(String email) {
        validateEmail(email);
        this.email = email.trim().toLowerCase();
    }

    public void setDepartment(String department) {
        validateName(department, "Department");
        this.department = department.trim();
    }

    public void setSalary(double salary) {
        validateSalary(salary);
        this.salary = salary;
    }

    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
     * Deactivates the employee (soft delete).
     */
    public void deactivate() {
        this.isActive = false;
    }

    /**
     * Activates the employee.
     */
    public void activate() {
        this.isActive = true;
    }

    // Validation methods
    private void validateId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Employee ID must be positive");
        }
    }

    private void validateName(String name, String fieldName) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " cannot be null or empty");
        }
        if (name.trim().length() > 50) {
            throw new IllegalArgumentException(fieldName + " cannot exceed 50 characters");
        }
    }

    private void validateEmail(String email) {
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new IllegalArgumentException("Email must be in valid format");
        }
        if (email.trim().length() > 100) {
            throw new IllegalArgumentException("Email cannot exceed 100 characters");
        }
    }

    private void validateSalary(double salary) {
        if (salary < 0) {
            throw new IllegalArgumentException("Salary cannot be negative");
        }
        if (salary > 1000000) {
            throw new IllegalArgumentException("Salary cannot exceed 1,000,000");
        }
    }

    private void validateHireDate(LocalDate hireDate) {
        if (hireDate == null) {
            throw new IllegalArgumentException("Hire date cannot be null");
        }
        if (hireDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Hire date cannot be in the future");
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Employee{id=%d, name='%s', email='%s', department='%s', salary=%.2f, active=%s}",
                id, getFullName(), email, department, salary, isActive);
    }
}
