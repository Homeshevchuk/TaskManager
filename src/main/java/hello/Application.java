package hello;

import hello.Account.Account;
import hello.Account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;



        import org.springframework.boot.SpringApplication;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.template.Neo4jTemplate;

import java.util.List;


@SpringBootApplication
@EnableNeo4jRepositories
public class Application  implements CommandLineRunner  {
    @Autowired
    AccountRepository accountRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {



    }



}
