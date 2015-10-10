package hello;

public class Greeting {

    private final long id;
    private final String content;
    private final long age;

    public Greeting(long id, String content, long age) {
        this.id = id;
        this.content = content;
        this.age = age;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
    
    public long getAge() {
        return age;
    }
}
