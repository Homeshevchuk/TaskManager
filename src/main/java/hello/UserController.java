package hello;

import hello.Account.Account;
import hello.Account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

/**
 * Created by Bogdan on 2/12/2016.
 */
@RestController

public class UserController {
    @Autowired
    AccountRepository repository;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<String> addUser(@RequestBody Account account ) {
        if(repository.findByUsername(account.getUsername())!=null)
        {
            return new ResponseEntity("{\"value\":\"Taken\"}",HttpStatus.BAD_REQUEST);
        }else{
            account.setTaskList(new ArrayList<>());
            account.setFriends(new ArrayList<>());
            repository.save(account);
            return new ResponseEntity(HttpStatus.OK);

        }
    }

    @RequestMapping(value = "/userData", method = RequestMethod.GET)

    public ResponseEntity<Account> getUserData(Principal principal){
        if(principal==null){
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }else {
            Account result = repository.findByUsername(principal.getName());
            result.setFriends(null);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
    @RequestMapping(value = "/getFriends", method = RequestMethod.GET)
    public ResponseEntity<Map<String,ArrayList<String>>> getFriends(Principal principal){
        Map<String,ArrayList<String>> result = new HashMap<>();
        result.put("requestsFromUser", new ArrayList<>());
        result.put("requestsToUser", new ArrayList<>());
        result.put("friends", new ArrayList<>());

        for (Account account :  repository.getRequestsToUser(principal.getName())){
            result.get("requestsToUser").add(account.getUsername());

        }
        for(Account account: repository.getRequestsFromUser(principal.getName())){
            result.get("requestsFromUser").add(account.getUsername());
        }
        for(Account account: repository.getFriends(principal.getName())){
            result.get("friends").add(account.getUsername());
        }
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @RequestMapping(value = "/addFriend", method = RequestMethod.POST)
    public ResponseEntity addFriend(@RequestBody String friendName,Principal principal){
     if(friendName!=null){
         Account friendFrom = repository.findByUsername(principal.getName());
         Account friendTo = repository.findByUsername(friendName);
         if(friendFrom.getFriends()==null){
             friendFrom.setFriends(new ArrayList<>());
         }
         friendFrom.getFriends().add(friendTo);
         repository.save(friendFrom);
         return new ResponseEntity(HttpStatus.OK);

     }else {
         return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
     }
    }
    @RequestMapping(value = "/deleteFriend", method = RequestMethod.POST)
    public ResponseEntity deleteFriend(@RequestBody String exFriendName,Principal principal){
        if(exFriendName!=null){
            Account user = repository.findByUsername(principal.getName());
            Account exFriend = repository.findByUsername(exFriendName);
            user.getFriends().remove(exFriend);
            exFriend.getFriends().remove(user);
            repository.save(user);
            repository.save(exFriend);
            return new ResponseEntity(HttpStatus.OK);

        }else {
            return new ResponseEntity<Account>(HttpStatus.BAD_REQUEST);
        }
    }
}
