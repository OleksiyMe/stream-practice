package com.cydeo.streampractice.practice;

import com.cydeo.streampractice.model.*;
import com.cydeo.streampractice.service.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class Practice {

    public static CountryService countryService;
    public static DepartmentService departmentService;
    public static EmployeeService employeeService;
    public static JobHistoryService jobHistoryService;
    public static JobService jobService;
    public static LocationService locationService;
    public static RegionService regionService;

    public Practice(CountryService countryService, DepartmentService departmentService,
                    EmployeeService employeeService, JobHistoryService jobHistoryService,
                    JobService jobService, LocationService locationService,
                    RegionService regionService) {

        Practice.countryService = countryService;
        Practice.departmentService = departmentService;
        Practice.employeeService = employeeService;
        Practice.jobHistoryService = jobHistoryService;
        Practice.jobService = jobService;
        Practice.locationService = locationService;
        Practice.regionService = regionService;

    }

    // You can use the services above for all the CRUD (create, read, update, delete) operations.
    // Above services have all the required methods.
    // Also, you can check all the methods in the ServiceImpl classes inside the service.impl package, they all have explanations.

    // Display all the employees
    public static List<Employee> getAllEmployees() {
        return employeeService.readAll();
    }

    // Display all the countries
    public static List<Country> getAllCountries() {
        //TODO Implement the method
        // return new ArrayList<>();
        return countryService.readAll();
    }

    // Display all the departments
    public static List<Department> getAllDepartments() {
        //TODO Implement the method
        //  return new ArrayList<>();

        return departmentService.readAll();
    }

    // Display all the jobs
    public static List<Job> getAllJobs() {
        //TODO Implement the method
        //  return new ArrayList<>();

        return jobService.readAll();
    }

    // Display all the locations
    public static List<Location> getAllLocations() {
        //TODO Implement the method
        //   return new ArrayList<>();
        return locationService.readAll();
    }

    // Display all the regions
    public static List<Region> getAllRegions() {
        //TODO Implement the method
        //return new ArrayList<>();
        return regionService.readAll();
    }

    // Display all the job histories
    public static List<JobHistory> getAllJobHistories() {
        //TODO Implement the method
        //return new ArrayList<>();
        return jobHistoryService.readAll();
    }

    // Display all the employees' first names
    public static List<String> getAllEmployeesFirstName() {
        //TODO Implement the method
        return getAllEmployees().stream().map(Employee::getFirstName).collect(Collectors.toList());

        // return new ArrayList<>();
        // return getAllEmployees().
    }

    // Display all the countries' names
    public static List<String> getAllCountryNames() {
        //TODO Implement the method
        return getAllCountries().stream().map(Country::getCountryName).collect(Collectors.toList());
        //  return new ArrayList<>();
    }

    // Display all the departments' managers' first names
    public static List<String> getAllDepartmentManagerFirstNames() {
        //TODO Implement the method
        return getAllDepartments().stream()
                .map(Department::getManager)
                .map(Employee::getFirstName)
                .collect(Collectors.toList());
    }

    // Display all the departments where manager name of the department is 'Steven'
    public static List<Department> getAllDepartmentsWhichManagerFirstNameIsSteven() {
        //TODO Implement the method
        return getAllDepartments().stream()
                .filter(p -> p.getManager().getFirstName().equals("Steven"))
                .collect(Collectors.toList());

    }

    // Display all the departments where postal code of the location of the department is '98199'
    public static List<Department> getAllDepartmentsWhereLocationPostalCodeIs98199() {
        //TODO Implement the method
        return getAllDepartments().stream()
                .filter(p -> p.getLocation().getPostalCode().equals("98199"))
                .collect(Collectors.toList());
    }

    // Display the region of the IT department
    public static Region getRegionOfITDepartment() throws Exception {
        //TODO Implement the method
        /*return getAllDepartments().stream()
                .filter(p -> p.getDepartmentName().equals("IT"))
                .map(Department::getLocation).map(Location::getCountry).map(Country::getRegion)
                .findAny().get();*/


      /*  return getAllDepartments().stream().filter(p -> p.getDepartmentName().equals("IT"))
                .findFirst().get()
                .getLocation().getCountry().getRegion();
*/
        return getAllDepartments().stream().filter(p -> p.getDepartmentName().equals("IT"))
                .findFirst()
                .orElseThrow(()-> new NoSuchElementException("No department found") )
                .getLocation().getCountry().getRegion();



    }

    // Display all the departments where the region of department is 'Europe'
    public static List<Department> getAllDepartmentsWhereRegionOfCountryIsEurope() {
        //TODO Implement the method
        return getAllDepartments().stream()
                .filter(p -> p.getLocation().getCountry().getRegion().getRegionName().equals("Europe"))
                .collect(Collectors.toList());
    }

    // Display if there is any employee with salary less than 1000. If there is none, the method should return true
    public static boolean checkIfThereIsNoSalaryLessThan1000() {
        //TODO Implement the method
        return !getAllEmployees().stream().anyMatch(p -> p.getSalary() < 1000);
    }

    // Check if the salaries of all the employees in IT department are greater than 2000 (departmentName: IT)
    public static boolean checkIfThereIsAnySalaryGreaterThan2000InITDepartment() {
        //TODO Implement the method
        return getAllEmployees().stream().filter(p -> p.getDepartment().equals("IT"))
                .allMatch(p -> p.getSalary() > 2000);
    }

    // Display all the employees whose salary is less than 5000
    public static List<Employee> getAllEmployeesWithLessSalaryThan5000() {
        //TODO Implement the method
        return getAllEmployees().stream().filter(p -> p.getSalary() < 5000)
                .collect(Collectors.toList());
    }

    // Display all the employees whose salary is between 6000 and 7000
    public static List<Employee> getAllEmployeesSalaryBetween() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p -> p.getSalary() > 6000 && p.getSalary() < 7000)
                .collect(Collectors.toList());
    }

    // Display the salary of the employee Grant Douglas (lastName: Grant, firstName: Douglas)
    public static Long getGrantDouglasSalary() throws Exception {
        //TODO Implement the method
        return getAllEmployees().stream().filter(p -> p.getFirstName().equals("Douglas") &&
                p.getLastName().equals("Grant")).map(Employee::getSalary).findAny().get();
    }

    // Display the maximum salary an employee gets
    public static Long getMaxSalary() throws Exception {
        //  return getAllEmployees().stream().max(Comparator.comparing(Employee::getSalary))
        //           .map(Employee::getSalary).stream().findFirst().get();
        return getAllEmployees().stream().map(Employee::getSalary)
                .max(Comparator.comparing(Long::longValue)).get();

    }

    // Display the employee(s) who gets the maximum salary
    public static List<Employee> getMaxSalaryEmployee() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .stream()
                .limit(1).collect(Collectors.toList());

    }

    // Display the max salary employee's job
    public static Job getMaxSalaryEmployeeJob() throws Exception {
        //TODO Implement the method
        return getAllEmployees().stream()
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getJob).get();
    }

    // Display the max salary in Americas Region
    public static Long getMaxSalaryInAmericasRegion() throws Exception {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p -> p.getDepartment().getLocation().getCountry().getRegion().getRegionName().equals("Americas"))
                .max(Comparator.comparing(Employee::getSalary))
                .map(Employee::getSalary).get();
    }

    // Display the second maximum salary an employee gets
    public static Long getSecondMaxSalary() throws Exception {
        //TODO Implement the method
        Long maxSalary = getMaxSalary();
        return getAllEmployees().stream().map(Employee::getSalary)
                .filter(p -> p != maxSalary).max(Comparator.comparing(Long::longValue)).get();
    }

    // Display the employee(s) who gets the second maximum salary
    public static List<Employee> getSecondMaxSalaryEmployee() throws Exception {
        //TODO Implement the method
        Long secondMaxSalary = getSecondMaxSalary();
        return getAllEmployees().stream().filter(p -> p.getSalary().equals(secondMaxSalary))
                .collect(Collectors.toList());
    }

    // Display the minimum salary an employee gets
    public static Long getMinSalary() throws Exception {
        //TODO Implement the method
        return getAllEmployees().stream().map(Employee::getSalary)
                .min(Comparator.comparing(Long::longValue)).orElse(0l);

    }

    // Display the employee(s) who gets the minimum salary
    public static List<Employee> getMinSalaryEmployee() throws Exception {
        //TODO Implement the method
        Long minSalary = getMinSalary();
        return getAllEmployees().stream().filter(p -> p.getSalary().equals(minSalary))
                .collect(Collectors.toList());

        /*return getAllEmployees().stream()
                .min(Comparator.comparing(Employee::getSalary))
                .stream()
                .limit(1).collect(Collectors.toList());*/
    }

    // Display the second minimum salary an employee gets
    public static Long getSecondMinSalary() throws Exception {
        //TODO Implement the method
        Long minSalary = getMinSalary();
        return getAllEmployees().stream().map(Employee::getSalary)
                .filter(p -> p != minSalary).min(Comparator.comparing(Long::longValue)).get();
    }

    // Display the employee(s) who gets the second minimum salary
    public static List<Employee> getSecondMinSalaryEmployee() throws Exception {
        //TODO Implement the method
        Long secondMinSalary = getSecondMinSalary();
        return getAllEmployees().stream().filter(p -> p.getSalary().equals(secondMinSalary))
                .collect(Collectors.toList());
    }

    // Display the average salary of the employees
    public static Double getAverageSalary() {
        //TODO Implement the method
        /*return getAllEmployees().stream().map(Employee::getSalary)
                .mapToDouble(p -> p).average().orElse(Double.NaN);
        */

        return getAllEmployees().stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

      /*  return getAllEmployees().stream().map(Employee::getSalary)
                .collect(Collectors.averagingDouble(salary->salary));*/


    }

    // Display all the employees who are making more than average salary
    public static List<Employee> getAllEmployeesAboveAverage() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p -> p.getSalary() > getAverageSalary())
                .collect(Collectors.toList());
    }

    // Display all the employees who are making less than average salary
    public static List<Employee> getAllEmployeesBelowAverage() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p -> p.getSalary() < getAverageSalary())
                .collect(Collectors.toList());
    }

    // Display all the employees separated based on their department id number
    public static Map<Long, List<Employee>> getAllEmployeesForEachDepartment() {
        //TODO Implement the method
        /*Map<Long, List<Employee>> resMap=new HashMap<>();
        resMap= getAllEmployees().stream()
                .collect(Collectors.groupingBy(p->p.getDepartment().getId()));*/

        return getAllEmployees().stream()
                .collect(Collectors.groupingBy(p -> p.getDepartment().getId()));
    }

    // Display the total number of the departments
    public static Long getTotalDepartmentsNumber() {
        //TODO Implement the method
        return getAllDepartments().stream().count();
    }

    // Display the employee whose first name is 'Alyssa' and manager's first name is 'Eleni' and department name is 'Sales'
    public static Employee getEmployeeWhoseFirstNameIsAlyssaAndManagersFirstNameIsEleniAndDepartmentNameIsSales() throws Exception {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p -> p.getFirstName().equals("Alyssa"))
                .filter(p -> p.getManager().getFirstName().equals("Eleni"))
                .filter(p -> p.getDepartment().getDepartmentName().equals("Sales"))
                .findFirst().get();

    }

    // Display all the job histories in ascending order by start date
    public static List<JobHistory> getAllJobHistoriesInAscendingOrder() {
        //TODO Implement the method
        return getAllJobHistories().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate))
                .collect(Collectors.toList());
    }

    // Display all the job histories in descending order by start date
    public static List<JobHistory> getAllJobHistoriesInDescendingOrder() {
        //TODO Implement the method
        return getAllJobHistories().stream()
                .sorted(Comparator.comparing(JobHistory::getStartDate).reversed())
                .collect(Collectors.toList());
    }

    // Display all the job histories where the start date is after 01.01.2005
    public static List<JobHistory> getAllJobHistoriesStartDateAfterFirstDayOfJanuary2005() {
        //TODO Implement the method
        return getAllJobHistories().stream()
                .filter(p -> p.getStartDate()
                        .isAfter(LocalDate.of(2005, 1, 1)))
                .collect(Collectors.toList());
    }

    // Display all the job histories where the end date is 31.12.2007 and the job title of job is 'Programmer'
    public static List<JobHistory> getAllJobHistoriesEndDateIsLastDayOfDecember2007AndJobTitleIsProgrammer() {
        //TODO Implement the method
        return getAllJobHistories().stream()
                .filter(p -> p.getEndDate()
                        .isEqual((LocalDate.of(2007, 12, 31))))
                .filter(p -> p.getJob().getJobTitle().equals("Programmer"))
                .collect(Collectors.toList());

    }

    // Display the employee whose job history start date is 01.01.2007 and
    // job history end date is 31.12.2007 and department's name is 'Shipping'
    public static Employee getEmployeeOfJobHistoryWhoseStartDateIsFirstDayOfJanuary2007AndEndDateIsLastDayOfDecember2007AndDepartmentNameIsShipping() throws Exception {
        //TODO Implement the method
        return getAllJobHistories().stream()
                .filter(p -> p.getStartDate().isEqual(LocalDate
                        .of(2007, 1, 1)))
                .filter(p -> p.getEndDate().isEqual(LocalDate
                        .of(2007, 12, 31)))
                .filter(p -> p.getDepartment().getDepartmentName().equals("Shipping"))
                .map(JobHistory::getEmployee).findFirst()
                .get();

    }

    // Display all the employees whose first name starts with 'A'
    public static List<Employee> getAllEmployeesFirstNameStartsWithA() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p->p.getFirstName().startsWith("A"))
                .collect(Collectors.toList());
    }

    // Display all the employees whose job id contains 'IT'
    public static List<Employee> getAllEmployeesJobIdContainsIT() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p->p.getJob().getId().contains("IT"))
                .collect(Collectors.toList());
    }

    // Display the number of employees whose job title is programmer and department name is 'IT'
    public static Long getNumberOfEmployeesWhoseJobTitleIsProgrammerAndDepartmentNameIsIT() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p -> p.getJob().getJobTitle().equals("Programmer"))
                .filter(p -> p.getDepartment().getDepartmentName().equals("IT"))
                .count();
    }

    // Display all the employees whose department id is 50, 80, or 100
    public static List<Employee> getAllEmployeesDepartmentIdIs50or80or100() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p->p.getDepartment().getId()==50 ||
                        p.getDepartment().getId()==80  ||
                        p.getDepartment().getId()==100)

                .collect(Collectors.toList());
    }

    // Display the initials of all the employees
    // Note: You can assume that there is no middle name
    public static List<String> getAllEmployeesInitials() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .map(p->""+p.getFirstName().charAt(0)
                            +p.getLastName().charAt(0))
                .collect(Collectors.toList());
    }

    // Display the full names of all the employees
    public static List<String> getAllEmployeesFullNames() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .map(p->p.getFirstName()+" "+p.getLastName())
        .collect(Collectors.toList());
    }

    // Display the length of the longest full name(s)
    public static Integer getLongestNameLength() throws Exception {
        //TODO Implement the method
        return getAllEmployees().stream()
                .map(p->p.getFirstName()+" "+p.getLastName())
                .map(p->p.length())
                .max(Comparator.comparing(Integer::intValue)).get();

    }

    // Display the employee(s) with the longest full name(s)
    public static List<Employee> getLongestNamedEmployee() throws Exception{
        //TODO Implement the method
        return getAllEmployees().stream()
               /* .sorted((a,b)->  {
                    int aLength= (a.getFirstName()+" "+a.getLastName()).length();
                    int blength=(b.getFirstName()+" "+b.getLastName()).length();
                    return Integer.compare(aLength, blength);

                }  )*/
                .filter(p-> {
                    int longest= 0;
                    try {
                        longest = getLongestNameLength();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    return (p.getFirstName()+" "+p.getLastName()).length()== longest;

                })
                .collect(Collectors.toList());




    }

    // Display all the employees whose department id is 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIs90or60or100or120or130() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p->p.getDepartment().getId()==90 ||
                        p.getDepartment().getId()==60  ||
                        p.getDepartment().getId()==100  ||
                        p.getDepartment().getId()==120  ||
                        p.getDepartment().getId()==130 )

                .collect(Collectors.toList());
    }

    // Display all the employees whose department id is NOT 90, 60, 100, 120, or 130
    public static List<Employee> getAllEmployeesDepartmentIdIsNot90or60or100or120or130() {
        //TODO Implement the method
        return getAllEmployees().stream()
                .filter(p-> !(p.getDepartment().getId()==90 ||
                        p.getDepartment().getId()==60  ||
                        p.getDepartment().getId()==100  ||
                        p.getDepartment().getId()==120  ||
                        p.getDepartment().getId()==130) )

                .collect(Collectors.toList());
    }

}
