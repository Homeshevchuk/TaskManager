package hello.Account;

import org.springframework.context.annotation.Bean;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Bogdan on 1/9/2016.
 */
public interface AccountRepository extends CrudRepository<Account,Long> {
   Account findByUsername(String username);
   List<Account> findByRole(String role);
}

