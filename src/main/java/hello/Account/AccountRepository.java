package hello.Account;

import org.springframework.data.neo4j.annotation.Query;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Bogdan on 1/9/2016.
 */
@Repository
public interface AccountRepository extends GraphRepository<Account> {
    Account findByUsername(String username);

    @Query("MATCH (n:Account )-[:FriendWith]->(m:Account {Username:{user}}) WHERE not(m)-[:FriendWith]->(n) RETURN n")
    List<Account> getRequestsToUser(@Param("user") String username);

    @Query("MATCH (n:Account {Username:{user}})-[:FriendWith]->(m:Account ) WHERE not(m)-[:FriendWith]->(n) RETURN m")
    List<Account> getRequestsFromUser(@Param("user") String username);


    @Query("MATCH (n:Account {Username:{user}})-[:FriendWith]->(m:Account ) WHERE (m)-[:FriendWith]->(n) RETURN m")
    List<Account> getFriends(@Param("user") String username);
}

