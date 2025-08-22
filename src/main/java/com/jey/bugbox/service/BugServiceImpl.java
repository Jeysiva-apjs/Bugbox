package com.jey.bugbox.service;

import com.jey.bugbox.entity.Bug;
import com.jey.bugbox.entity.User;
import com.jey.bugbox.repository.BugRepository;
import com.jey.bugbox.request.BugRequest;
import com.jey.bugbox.response.BugResponse;
import com.jey.bugbox.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BugServiceImpl implements BugService {

    private final BugRepository bugRepository;

    private final FindAuthenticatedUser findAuthenticatedUser;

    public BugServiceImpl(BugRepository bugRepository, FindAuthenticatedUser findAuthenticatedUser) {
        this.bugRepository = bugRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BugResponse> getCurrentUserBugs() {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        return bugRepository.findByOwner(currentUser)
                .stream()
                .map(this::convertToBugResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<BugResponse> getAllBugs() {
        return bugRepository.findAll()
                .stream()
                .map(this::convertToBugResponse)
                .toList();
    }




    @Override
    @Transactional
    public BugResponse createBug(BugRequest bugRequest) {
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        Bug bug = new Bug(
                bugRequest.getTitle(),
                bugRequest.getDescription(),
                bugRequest.getPriority(),
                false,
                "",
                currentUser,
                ""
        );

        Bug savedBug = bugRepository.save(bug);

        return convertToBugResponse(savedBug);
    }

    @Override
    @Transactional(readOnly = true)
    public List<BugResponse> getAllBugsByPriority(int priority) {
        return bugRepository.findByPriority(priority)
                .stream()
                .map(this::convertToBugResponse)
                .toList();
    }



    @Override
    @Transactional
    public BugResponse updateBugUserDetails(long id, BugRequest bugRequest){
        User currentUser = findAuthenticatedUser.getAuthenticatedUser();

        Bug bug = bugRepository.findByIdAndOwner(id, currentUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bug not found"));

        bug.setTitle(bugRequest.getTitle());
        bug.setDescription(bugRequest.getDescription());
        bug.setPriority(bugRequest.getPriority());

        Bug updatedBug = bugRepository.save(bug);

        return convertToBugResponse(updatedBug);
    }


    @Override
    @Transactional
    public BugResponse toggleBugResolved(long id) {

        Optional<Bug> bug = bugRepository.findById(id);

        if (bug.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bug not found");
        }

        bug.get().setResolved(!bug.get().isResolved());
        Bug updatedBug = bugRepository.save(bug.get());

        return convertToBugResponse(updatedBug);
    }

    @Override
    public BugResponse assignBug(long id, String assignedTo) {

        Optional<Bug> bug = bugRepository.findById(id);

        if (bug.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bug not found");
        }

        bug.get().setAssignedTo(assignedTo);
        Bug updatedBug = bugRepository.save(bug.get());

        return convertToBugResponse(updatedBug);

    }

    @Override
    @Transactional(readOnly = true)
    public List<BugResponse> getAllBugsSortedByPriority() {
        return bugRepository.findAllByOrderByPriorityAsc()
                .stream()
                .map(this::convertToBugResponse)
                .toList();
    }


    @Override
    public void deleteBug(long id) {

        Optional<Bug> bug = bugRepository.findById(id);

        if (bug.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bug not found");
        }

        bugRepository.delete(bug.get());

    }

    private BugResponse convertToBugResponse(Bug bug) {
        return new BugResponse(
                bug.getId(),
                bug.getTitle(),
                bug.getDescription(),
                bug.getPriority(),
                bug.isResolved(),
                bug.getComments(),
                bug.getAssignedTo()
        );
    }
}







