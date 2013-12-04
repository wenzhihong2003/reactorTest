package hello;

/**
 * User: wenzhihong
 * Date: 13-12-4
 * Time: 上午11:34
 */
public class JokeResource {
    String type;
    Joke value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Joke getValue() {
        return value;
    }

    public void setValue(Joke value) {
        this.value = value;
    }
}
