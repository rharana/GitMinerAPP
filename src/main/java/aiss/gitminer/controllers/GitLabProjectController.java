package aiss.gitminer.controllers;

import aiss.gitminer.model.Project;
import aiss.gitminer.repositories.GitLabProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/gitminer/projects")
public class GitLabProjectController {
    @Autowired
    GitLabProjectRepository repository;
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Project create(@Valid @RequestBody Project project)
    {
        return repository.save(project);
    }
}
