package project.scarfino.ImageDB.models.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import project.scarfino.ImageDB.models.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findByUsername(String username);
}