package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;



        import org.springframework.boot.SpringApplication;
        import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.repository.Repository;

@SpringBootApplication
    public class Application implements CommandLineRunner{
    @Autowired
    TaskRepository repository;
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    @Override
    public void run(String... args) throws Exception {
        repository.save(new Task(1,"It works"));
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (Task task : repository.findAll()) {
            System.out.println(task);
        }
        System.out.println();
    }
}