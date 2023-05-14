package aiss.GitMiner.controllers;

import aiss.GitMiner.model.Issue;
import aiss.GitMiner.model.Project;
import aiss.GitMiner.repositories.CommentRepository;
import aiss.GitMiner.repositories.CommitRepository;
import aiss.GitMiner.repositories.ProjectRepository;
import aiss.GitMiner.repositories.IssueRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Tag(name = "Project", description = "Project management API")
@RestController
@RequestMapping("/gitminer/projects")
public class ProjectController {
    @Autowired
    ProjectRepository repository;
    @Autowired
    IssueRepository issueRepository;
    @Autowired
    CommentRepository commentRepository;
    @Autowired
    CommitRepository commitRepository;

    @Operation(
            summary = "Post new project to Database",
            description = "Posts a new project to DB and each object to its repository",
            tags= {"Project", "Post"}
    )
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Project create(@Valid @RequestBody Project project)
    {
        issueRepository.saveAll(project.getIssues());
        commitRepository.saveAll(project.getCommits());

        for(Issue i: project.getIssues()){
            commentRepository.saveAll(i.getComments());
        }

        return repository.save(project);
    }

    //GET REQUESTS
    @Operation(
            summary = "Get projects",
            description = "Retrieves projects from database",
            tags= {"Project", "Get"}
    )
    @GetMapping
    public List<Project> getProjects()
    {
        return repository.findAll();
    }
    @Operation(
            summary = "Get project by Id",
            description = "Retrieves project from database with matching id",
            tags= {"Project", "Get", "Id"}
    )
    @GetMapping("/{id}")
    public Project getProject(@Parameter(description = "Id of the Project to retrieve")@PathVariable String id)
    {
        return repository.findById(id).get();
    }
}
