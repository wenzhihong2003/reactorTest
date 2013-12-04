package hello;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * User: wenzhihong
 * Date: 13-12-4
 * Time: 上午11:31
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke {
    int id;
    String joke;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }
}
