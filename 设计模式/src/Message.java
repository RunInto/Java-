public class Message implements Cloneable {
    private int age;
    Message(int age) {
        this.age = age;
    }

    @Override
    public Message clone() {
        try {
            return (Message) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
