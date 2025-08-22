package com.jey.bugbox.repository;

import com.jey.bugbox.entity.Bug;
import com.jey.bugbox.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BugRepository extends CrudRepository<Bug, Long> {
    List<Bug> findByOwner(User owner);
    Optional<Bug> findByIdAndOwner(Long id, User owner);
    List<Bug> findAll();
    Optional<Bug> findById(Long id);
    List<Bug> findAllByOrderByPriorityAsc();   // ascending order
    List<Bug> findByPriority(int priority);
}
