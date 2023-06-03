package top.yh.datastorage.entity;

/**
 * @user
 * @date
 */
public class User {
    private int id;
    private String name;
    private int age;
    private int tall;
    private float weight;
    private boolean wed;

    public User() {
    }

    public User(String name, int age, int tall, float weight, boolean wed) {
        this.name = name;
        this.age = age;
        this.tall = tall;
        this.weight = weight;
        this.wed = wed;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", tall=" + tall +
                ", weight=" + weight +
                ", wed=" + wed +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTall() {
        return tall;
    }

    public void setTall(int tall) {
        this.tall = tall;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public boolean isWed() {
        return wed;
    }

    public void setWed(boolean wed) {
        this.wed = wed;
    }
}
