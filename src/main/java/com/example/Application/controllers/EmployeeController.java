package com.example.Application.controllers;

import com.example.Application.models.employee;
import com.example.Application.models.employeeDTO;
import com.example.Application.services.EmployeesRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired // Enables dependency injection for java classes
    private EmployeesRepository repo;

    @GetMapping({"", "/"})
    public String showEmployee(Model model) {
        List<employee> employees = repo.findAll(Sort.by(Sort.Direction.DESC, "id"));
        model.addAttribute("employees", employees);
        return "employees/index";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("employeeDTO", new employeeDTO());
        return "employees/createEmployee";
    }

    @PostMapping("/create")
    public String addEmployee (
            @Valid @ModelAttribute employeeDTO employeeDTOs, BindingResult result ) {

        if (result.hasErrors()) {
            return "employees/createEmployee";
        }

        employee employees = new employee();
        employees.setName(employeeDTOs.getName());
        employees.setSurname(employeeDTOs.getSurname());
        employees.setAge(employeeDTOs.getAge());
        employees.setPhone(employeeDTOs.getPhone());
        employees.setEmail(employeeDTOs.getEmail());

        repo.save(employees);

        return "redirect:/employees";
    }

    @GetMapping("/upload")
    public String showUploadForm() {
        return "employees/uploadEmployee";
    }


    @GetMapping("/edit")
    public String editPage(Model model, @RequestParam int id) {

        try {
            employee employees = repo.findById(id).get();
            model.addAttribute("employees", employees);

            employeeDTO employeeDTOs = new employeeDTO();
            employeeDTOs.setName(employees.getName());
            employeeDTOs.setSurname(employees.getSurname());
            employeeDTOs.setAge(employees.getAge());
            employeeDTOs.setPhone(employees.getPhone());
            employeeDTOs.setEmail(employees.getEmail());

            model.addAttribute("employeeDTOs", employeeDTOs);

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
            return "redirect:/employees";
        }
        return "employees/editEmployee";
    }

    @PostMapping("/edit")
    public String editEmployee(Model model, @RequestParam int id,
                               @Valid @ModelAttribute employeeDTO employeeDTOs,
                               BindingResult result) {

        try {
            employee employees = repo.findById(id).get();
            model.addAttribute("employees", employees);

            if (result.hasErrors()) {
                return "employees/editEmployee";
            }

            employees.setName(employeeDTOs.getName());
            employees.setSurname(employeeDTOs.getSurname());
            employees.setAge(employeeDTOs.getAge());
            employees.setPhone(employeeDTOs.getPhone());
            employees.setEmail(employeeDTOs.getEmail());

            repo.save(employees);

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return "redirect:/employees/";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam int id) {

        try {
            employee employees = repo.findById(id).get();
            repo.delete(employees);

        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

        return "redirect:/employees/";
    }
}
