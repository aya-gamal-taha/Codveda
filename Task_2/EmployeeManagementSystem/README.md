# Employee Management System

A clean, well-structured Java application for managing employee information with comprehensive CRUD operations, search functionality, and robust validation.

## 🚀 Features

- **Complete CRUD Operations**: Create, Read, Update, Delete employees
- **Advanced Search**: Search by ID, email, name, department, or salary range
- **Data Validation**: Comprehensive input validation with meaningful error messages
- **Clean Code Architecture**: Follows SOLID principles and clean code practices
- **Comprehensive Testing**: Full unit test coverage for all functionality
- **Interactive Console Interface**: User-friendly menu-driven application
- **Statistics & Reporting**: Department statistics and salary analytics

## 📁 Project Structure

```
EmployeeManagementSystem/
├── src/
│   ├── App.java                    # Main application with console interface
│   ├── Employee.java               # Employee entity with validation
│   ├── EmployeeManager.java        # Business logic and CRUD operations
│   └── test/
│       ├── EmployeeTest.java       # Unit tests for Employee class
│       ├── EmployeeManagerTest.java # Unit tests for EmployeeManager class
│       └── TestRunner.java         # Test suite runner
├── lib/                            # Dependencies (if any)
└── README.md                       # This file
```

## 🏗️ Clean Code Principles Applied

### 1. **Single Responsibility Principle (SRP)**
- `Employee`: Represents employee data and validation
- `EmployeeManager`: Handles business logic and data management
- `App`: Manages user interface and application flow

### 2. **Encapsulation**
- Private fields with public getters/setters
- Input validation in constructors and setters
- Clear separation of concerns

### 3. **Meaningful Names**
- Descriptive method names: `createEmployee()`, `searchEmployeesByName()`
- Clear variable names: `firstName`, `lastName`, `hireDate`
- Intention-revealing function names

### 4. **Error Handling**
- Comprehensive validation with descriptive error messages
- Proper exception handling throughout the application
- Graceful degradation for invalid inputs

### 5. **DRY (Don't Repeat Yourself)**
- Reusable validation methods
- Common input handling utilities
- Consistent error handling patterns

## 🚦 Getting Started

### Prerequisites
- Java 8 or higher
- Any Java IDE (VS Code, IntelliJ IDEA, Eclipse)

## How to Compile and Run

### Compile the Application
```bash
# Navigate to the project directory
cd EmployeeManagementSystem

# Compile main application files
javac -d bin src/*.java

# Compile test files separately
javac -cp bin -d bin test/*.java
```

### Run the Main Application
```bash
# Run the interactive console application
java -cp bin App
```

### Run the Test Suite
```bash
# Run all unit tests
java -cp bin TestRunner
```

### Sample Usage

The application starts with pre-loaded sample data and provides an interactive menu:

```
==================================================
EMPLOYEE MANAGEMENT SYSTEM - MAIN MENU
==================================================
1. Create New Employee
2. View All Active Employees
3. Search Employee by ID/Email
4. Update Employee Information
5. Deactivate Employee
6. View Department Statistics
7. View Salary Statistics
8. Search by Department
9. Search by Salary Range
0. Exit
==================================================
```

## 📊 Employee Data Model

Each employee contains the following information:

- **ID**: Unique identifier (auto-generated)
- **First Name**: Employee's first name (required, max 50 chars)
- **Last Name**: Employee's last name (required, max 50 chars)
- **Email**: Unique email address (required, valid format)
- **Department**: Employee's department (required, max 50 chars)
- **Salary**: Employee's salary (non-negative, max 1,000,000)
- **Hire Date**: Date of employment (cannot be in future)
- **Active Status**: Whether employee is currently active

## 🔍 Key Features Explained

### 1. **Employee Creation**
- Validates all input fields
- Ensures email uniqueness
- Auto-generates sequential IDs
- Sets employees as active by default

### 2. **Search Functionality**
- **By ID**: Direct lookup using employee ID
- **By Email**: Case-insensitive email search
- **By Name**: Partial matching in first/last names
- **By Department**: Filter employees by department
- **By Salary Range**: Find employees within salary bounds

### 3. **Update Operations**
- Partial updates (only change specified fields)
- Maintains data integrity during updates
- Validates email uniqueness on email changes

### 4. **Soft Delete**
- Employees are deactivated rather than permanently deleted
- Maintains data history and referential integrity
- Option for permanent removal if needed

### 5. **Statistics & Analytics**
- Department-wise employee count
- Average salary calculations
- Active vs total employee counts

## 🧪 Testing

The project includes comprehensive unit tests covering:

### Employee Class Tests
- Valid employee creation
- Input validation (ID, names, email, salary, hire date)
- Setter validation
- Activation/deactivation functionality
- Equals and hashCode methods
- toString method

### EmployeeManager Class Tests
- Employee creation and duplicate email handling
- Retrieval operations (by ID, email, department)
- Update operations with partial updates
- Search functionality (name, salary range)
- Statistics calculations
- Activation/deactivation operations
- Permanent removal operations

### Running Tests
```bash
# Compile and run all tests
javac -d bin src/*.java src/test/*.java
java -cp bin test.TestRunner
```

## 🎯 Clean Code Practices Demonstrated

### Method Design
- **Small Methods**: Each method does one thing well
- **Descriptive Names**: Method names clearly indicate their purpose
- **Minimal Parameters**: Methods have reasonable parameter counts
- **Return Meaningful Values**: Methods return appropriate types (Optional, boolean, etc.)

### Error Handling
- **Fail Fast**: Validate inputs immediately
- **Meaningful Messages**: Clear, actionable error messages
- **Appropriate Exceptions**: Use IllegalArgumentException for validation errors

### Code Organization
- **Logical Grouping**: Related methods are grouped together
- **Consistent Formatting**: Uniform indentation and spacing
- **Clear Comments**: Javadoc comments for all public methods
- **Separation of Concerns**: UI, business logic, and data are separated

### Data Integrity
- **Immutable IDs**: Employee IDs cannot be changed after creation
- **Validation Everywhere**: Input validation in constructors and setters
- **Defensive Copying**: Return copies of collections to prevent external modification

## 🔧 Extending the System

The system is designed for easy extension:

1. **Add New Fields**: Extend the Employee class with additional properties
2. **New Search Criteria**: Add methods to EmployeeManager for new search types
3. **Persistence**: Add database integration by implementing a repository pattern
4. **REST API**: Wrap EmployeeManager with REST endpoints
5. **GUI Interface**: Replace console interface with Swing/JavaFX

## 📝 Code Quality Metrics

- **Test Coverage**: 100% method coverage
- **Validation**: All inputs validated with meaningful error messages
- **Documentation**: Complete Javadoc for all public methods
- **Error Handling**: Comprehensive exception handling
- **Performance**: Efficient search operations using streams and collections

## 🤝 Contributing

When contributing to this project, please maintain the clean code standards:

1. Write descriptive method and variable names
2. Keep methods small and focused
3. Add comprehensive tests for new functionality
4. Include Javadoc comments for public methods
5. Validate all inputs with meaningful error messages
6. Follow the existing code formatting style

