package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;



        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.Repository;
import org.springframework.http.HttpMethod;


@SpringBootApplication
    public class Application implements CommandLineRunner{
    @Autowired
    TaskRepository repository;
    @Autowired
    AccountRepository accountRepository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Task(1,"It works"));
        repository.save(new Task(2,"It doesnt work"));
        repository.save(new Task(3,"It works"));



        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Task task : repository.findAll()) {
            System.out.println(task);
        }
        System.out.println();
    }
}
