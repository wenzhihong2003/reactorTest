package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import reactor.core.Environment;
import reactor.core.Reactor;
import reactor.core.spec.Reactors;
import static reactor.event.selector.Selectors.*;

import java.util.concurrent.CountDownLatch;

/**
 * 根据 http://spring.io/guides/gs/messaging-reactor/ 教程做的例子.
 * User: wenzhihong
 * Date: 13-12-4
 * Time: 下午1:12
 */
@Configurable
@EnableAutoConfiguration
@ComponentScan
public class Application implements CommandLineRunner {

    @Bean
    Environment env() {
        return new Environment();
    }

    @Bean
    Reactor createReactor(Environment env) {
        return Reactors.reactor()
                .env(env)
                .dispatcher(Environment.THREAD_POOL)
                .get();
    }

    @Autowired
    private Reactor reactor;

    @Autowired
    private Receiver receiver;

    @Autowired
    private Publisher publisher;

    @Bean
    Integer numberOfJokes() {
        return 10;
    }

    @Bean
    public CountDownLatch latch(Integer numberOfJokes) {
        return new CountDownLatch(numberOfJokes);
    }

    @Override
    public void run(String... args) throws Exception {
        reactor.on($("jokes"), receiver);
        publisher.publishJokes();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
