package aiss.GitMiner.repositories;

import aiss.GitMiner.model.Comment;
import aiss.GitMiner.model.Commit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommitRepository extends JpaRepository<Commit, String> {
}

