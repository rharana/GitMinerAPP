package aiss.GitMiner.repositories;

import aiss.GitMiner.model.Issue;
import aiss.GitMiner.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssueRepository extends JpaRepository<Issue, String> {

}
