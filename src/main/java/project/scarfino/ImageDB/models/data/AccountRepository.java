package project.scarfino.ImageDB.models.data;

import jakarta.transaction.Transactional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.scarfino.ImageDB.models.Account;


@Repository
@Transactional
public interface AccountRepository extends CrudRepository<Account, Integer> {
}