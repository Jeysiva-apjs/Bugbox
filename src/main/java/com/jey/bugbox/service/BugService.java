package com.jey.bugbox.service;

import com.jey.bugbox.request.BugRequest;
import com.jey.bugbox.response.BugResponse;

import java.util.List;

public interface BugService {
    List<BugResponse> getCurrentUserBugs();
    BugResponse createBug(BugRequest bugRequest);
    BugResponse updateBugUserDetails(long id, BugRequest bugRequest);

    List<BugResponse> getAllBugs();
    List<BugResponse> getAllBugsSortedByPriority();
    List<BugResponse> getAllBugsByPriority(int priority);
    BugResponse assignBug(long id, String assignedTo);
    BugResponse toggleBugResolved(long id);
    void deleteBug(long id);

}
