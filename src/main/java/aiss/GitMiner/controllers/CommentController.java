package aiss.GitMiner.controllers;

import aiss.GitMiner.model.Comment;
import aiss.GitMiner.repositories.CommentRepository;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Comments", description = "Comment management API")
@RestController
@RequestMapping("/gitminer/comments")
public class CommentController {
    @Autowired
    CommentRepository repository;

    //GET REQUESTS
    @GetMapping
    public List<Comment> getComments()
    {
        return repository.findAll();
    }
    @GetMapping("/{id}")
    public Comment getIssueById(@Parameter(description = "Id of the Comment to retrieve")@PathVariable String id)
    {
        return repository.findById(id).get();
    }
}
