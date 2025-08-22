package com.jey.bugbox.controller;

import com.jey.bugbox.response.BugResponse;
import com.jey.bugbox.service.AdminService;
import com.jey.bugbox.service.BugService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Admin - Bug management REST API Endpoints")
@RestController
@RequestMapping("/admin/bugs")
public class AdminBugManagementController {

    private final AdminService adminService;
    private final BugService bugService;

    public AdminBugManagementController(AdminService adminService, BugService bugService) {
        this.adminService = adminService;
        this.bugService = bugService;
    }

    @Operation(summary = "Delete a bug")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteBug(@PathVariable @Min(1) long id) {
        bugService.deleteBug(id);
    }

    @Operation(summary = "Toggle bug resolved status")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/status")
    public BugResponse toggleBugResolved(@PathVariable @Min(1) long id) {
        return bugService.toggleBugResolved(id);
    }

    @Operation(summary = "Get all bugs")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<BugResponse> getAllBugs() {
        return bugService.getAllBugs();
    }

    @Operation(summary = "Get all bugs by priority order")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/priority")
    public List<BugResponse> getAllBugsSortedByPriority() {
        return bugService.getAllBugsSortedByPriority();
    }

//    @Operation(summary = "Get all bugs by specifying priority")
//    @ResponseStatus(HttpStatus.OK)
//    @GetMapping("/{id}/priority")
//    public List<BugResponse> getAllBugsByPriority(@PathVariable @Min(1) int priority) {
//        return bugService.getAllBugsByPriority(priority);
//    }

    @Operation(summary = "Assign bug to a developer")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}/assign")
    public BugResponse assignBug(@PathVariable @Min(1) long id, @RequestBody String name) {
        return bugService.assignBug(id, name);
    }

}
