package hello;

import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.data.neo4j.config.Neo4jConfiguration;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.server.Neo4jServer;
import org.springframework.data.neo4j.server.RemoteServer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableNeo4jRepositories(basePackages = "hello")
@EnableTransactionManagement
public class MyConfiguration extends Neo4jConfiguration {

    @Bean
    public Neo4jServer neo4jServer() {
        return new RemoteServer("https://neo-hosea-cremin-peachpuff-56c58bc586f0a.do-stories.graphstory.com:7473","56c58c0a49a66","SrXxl6EWcUNiIT5vCZDcAmkDYznOmRMCIMLWt1os");
    }

    @Bean
    public SessionFactory getSessionFactory() {
        // with domain entity base package(s)
        return new SessionFactory("hello");
    }

    // needed for session in view in web-applications
    @Bean
    public Session getSession() throws Exception {
        return super.getSession();
    }

}