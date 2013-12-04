package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import reactor.event.Event;
import reactor.function.Consumer;

import java.util.concurrent.CountDownLatch;

/**
 * User: wenzhihong
 * Date: 13-12-4
 * Time: 上午11:35
 */
@Service
public class Receiver implements Consumer<Event<Integer>> {

    @Autowired
    CountDownLatch latch;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public void accept(Event<Integer> ev) {
        JokeResource jokeResource = restTemplate.getForObject("http://api.icndb.com/jokes/random", JokeResource.class);
        System.out.println("joke " + ev.getData() + ": " + jokeResource.getValue().getJoke());
        latch.countDown();
    }
}
