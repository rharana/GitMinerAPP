package aiss.GitMiner.controllers;

import aiss.GitMiner.model.Comment;
import aiss.GitMiner.model.Issue;
import aiss.GitMiner.repositories.IssueRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Issue", description = "Issue management API")
@RestController
@RequestMapping("/gitminer/issues")
public class IssueController {
    @Autowired
    IssueRepository repository;


    //GET REQUESTS
    @Operation(
            summary = "Get issues",
            description = "Retrieves issues from database",
            tags= {"Issue", "Get"}
    )
    @GetMapping
    public List<Issue> getIssue(@Parameter(description = "Id of the author of the Issues to retrieve") @RequestParam(required = false) String authorId,
                                @Parameter(description = "State of Issues to retrieve") @RequestParam(required = false) String state)
    {
        if(authorId == null && state == null){
            return repository.findAll();
        }
        else if(authorId != null){
            return collectByAuthorId(authorId);
        }
        else if(state != null){
            return collectByState(state);
        }
        else{
            return null;
        }
    }
    //Auxiliary function
    private List<Issue> collectByAuthorId(String authorId){
        return repository.findAll().stream()
                .filter(x -> x.getAuthor().getId().equals(authorId))
                .collect(Collectors.toList());
    }
    //Auxiliary function
    private List<Issue> collectByState(String state){
        return repository.findAll().stream()
                .filter(x -> x.getState().equals(state))
                .collect(Collectors.toList());
    }

    @Operation(
            summary = "Get Issue by Id",
            description = "Retrieves issue from database with matching id",
            tags= {"Issue", "Get", "Id"}
    )
    @GetMapping("/{id}")
    public Issue getIssueById(@Parameter(description = "Id of the Issue to retrieve")@PathVariable String id)
    {
        return repository.findById(id).get();
    }

    @Operation(
            summary = "Get issue's comments",
            description = "Retrieves comments from Issue with matching Issue id",
            tags= {"Issue", "Get", "Id", "Comment"}
    )
    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@Parameter(description = "Id of the Issue to get its comments")@PathVariable String id)
    {
        return repository.findById(id).get().getComments();
    }
}
