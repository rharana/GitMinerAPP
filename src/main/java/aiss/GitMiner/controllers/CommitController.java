package aiss.GitMiner.controllers;

import aiss.GitMiner.model.Comment;
import aiss.GitMiner.model.Commit;
import aiss.GitMiner.model.Issue;
import aiss.GitMiner.repositories.CommentRepository;
import aiss.GitMiner.repositories.CommitRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Commit", description = "Commit management API")
@RestController
@RequestMapping("/gitminer/commits")
public class CommitController {
    @Autowired
    CommitRepository repository;

    //GET REQUESTS
    @Operation(
            summary = "Get Commits",
            description = "Retrieves commits from database",
            tags= {"Commit", "Get"}
    )
    @GetMapping
    public List<Commit> getCommits(@Parameter(description = "Email Addres of the Commits to retrieve's author")@RequestParam(required = false) String emailAddress)
    {
        if(emailAddress != null){
            return repository.findAll().stream()
                    .filter(x->x.getAuthorEmail().equals(emailAddress))
                    .collect(Collectors.toList());
        }
        else{
            return repository.findAll();
        }
    }

    @Operation(
            summary = "Get Commit by Id",
            description = "Retrieves commit from database with matching id",
            tags= {"Commit", "Get", "Id"}
    )
    @GetMapping("/{id}")
    public Commit getIssueById(@Parameter(description = "Id of the Commit to retrieve")@PathVariable String id)
    {
        return repository.findById(id).get();
    }
}
