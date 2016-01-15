package hello;

import org.springframework.data.repository.CrudRepository;


/**
 * Created by Bogdan on 1/9/2016.
 */
public interface AccountRepository extends CrudRepository<Account,Long> {
}

