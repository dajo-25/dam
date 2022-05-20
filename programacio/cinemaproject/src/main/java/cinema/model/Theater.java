package cinema.model;

public class Theater implements Comparable<Theater> {
    private int number;
    private String name;
    private int capacity;

    public Theater() {
        this.number = 0;
        this.name = "";
        this.capacity = 0;
    }

    public Theater(int number, String name, int capacity) {
        this.number = number;
        this.name = name;
        this.capacity = capacity;
    }

    public Theater(Theater theater) {
        this.number = theater.number;
        this.name = theater.name;
        this.capacity = theater.capacity;
    }

    public int getNumber() {
        return this.number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return this.capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public int hashCode() {
        Integer num = this.number;
        return num.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        else if(obj instanceof Theater) {
            Theater theater = (Theater) obj;
            return this.number == theater.number;
        }
        return false;
    }

    @Override
    public String toString() {
        return this.number + ". " + this.name + " (" + this.capacity + ")";
    }

    @Override
    public int compareTo(Theater theater) {
        return this.number - theater.number;
    }
}
