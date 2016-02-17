package hello;

import com.fasterxml.jackson.annotation.JsonCreator;
import hello.Account.Account;
import hello.Account.AccountRepository;
import hello.Account.Friendship;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

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
            ArrayList<Task> taskArrayList= new ArrayList<>();
            ArrayList<Friendship> friendships = new ArrayList<>();
            account.setTaskList(taskArrayList);
            account.setFriendships(friendships);
            repository.save(account);
            return new ResponseEntity<>(HttpStatus.OK);

        }

    }
    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public ResponseEntity addFriend(@RequestBody String friendName, Principal principal){
        Account friendAcc = repository.findByUsername(friendName);
        Account mainAcc = repository.findByUsername(principal.getName());
        Friendship friendship = new Friendship();
        friendship.setApproved(false);
        friendship.setFirstFriendId(mainAcc.getId());
        friendship.setSecondFriendId(friendAcc.getId());
        mainAcc.getFriendships().add(friendship);
        friendAcc.getFriendships().add(friendship);
        if(friendAcc!=null && mainAcc!=null){

            return new ResponseEntity(HttpStatus.OK);
        }
        else{
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }

    }
    @RequestMapping(value = "/userData", method = RequestMethod.GET)
    public ResponseEntity<Account> getUserData(Principal principal){

      if(principal==null){
          return new ResponseEntity<Account>(HttpStatus.UNAUTHORIZED);
      }else {
          Account result = repository.findByUsername(principal.getName());
          return new ResponseEntity<Account>(result,HttpStatus.OK);
      }
    }

}
