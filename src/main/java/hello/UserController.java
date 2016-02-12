package hello;

import com.fasterxml.jackson.annotation.JsonCreator;
import hello.Account.Account;
import hello.Account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Bogdan on 2/12/2016.
 */
@RestController

public class UserController {
    @Autowired
    AccountRepository repository;
    /*@RequestMapping(value = "/register",produces = "application/json", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity addUser(@RequestParam ("name") String username, @RequestParam("password") String password ) {
        repository.save(new Account(username,password));
        if(repository.findByUsername(username)!=null){
          System.out.println(repository.findByUsername(username));

        }
        return new ResponseEntity(HttpStatus.OK);
    }*/
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody Account account ) {
        if(repository.findByUsername(account.getUsername())!=null)
        {

            return new ResponseEntity<>("{\"value\":\"Taken\"}",HttpStatus.BAD_REQUEST);
        }else{
            repository.save(account);
            return new ResponseEntity<>(HttpStatus.OK);

        }

    }
}
