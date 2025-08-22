package com.jey.bugbox.controller;

import com.jey.bugbox.request.BugRequest;
import com.jey.bugbox.response.BugResponse;
import com.jey.bugbox.service.BugService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User - Reporting bugs REST API Endpoints")
@RestController
@RequestMapping("/bugs")
public class BugController {

    private final BugService bugService;

    public BugController(BugService bugService) {
        this.bugService = bugService;
    }

    @Operation(summary = "Get all bugs", description = "Fetch all bugs from signed-in user")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<BugResponse> getCurrentUserBugs() {
        return bugService.getCurrentUserBugs();
    }

    @Operation(summary = "Create bug", description = "Create bug for the signed-in user")
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public BugResponse createBug(@Valid @RequestBody BugRequest bugRequest) {
        return bugService.createBug(bugRequest);
    }

    @Operation(summary = "Update user details of the existing bug")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public BugResponse updateBugUserDetails(@PathVariable @Min(1) long id, @Valid @RequestBody BugRequest bugRequest){
        return bugService.updateBugUserDetails(id, bugRequest);
    }

}












