import java.util.Objects;

/**
 * 具体实现类，实现一个克隆自身的操作
 */
public class ConcretePrototype implements Prototype, Cloneable {
    private Integer id;
    private String name;
    private Message message;

    ConcretePrototype(Integer id, String name, Message message) {
        super();
        this.id = id;
        this.name = name;
        this.message = message;
    }



    @Override
    public ConcretePrototype clone() {
        try {
            ConcretePrototype prototype = (ConcretePrototype)super.clone();
            prototype.message = this.message.clone();
            return prototype;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        ConcretePrototype prototype = new ConcretePrototype(1, "o1", new Message(18));
        System.out.println("source obj is " + prototype);
        System.out.println("source obj hashcode is " + prototype.hashCode());

        ConcretePrototype clone = prototype.clone();
        System.out.println("target obj is " + clone);
        System.out.println("target obj hashcode is " + clone.hashCode());


        System.out.println("source and target is equals ? " + Objects.equals(prototype, clone));

    }

    @Override
    public String toString() {
        return "Prototype{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", message=" + message +
                '}';
    }
}
