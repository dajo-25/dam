package exam.company.model;

public class Employee {
    private String dni;
    private String name;
    private int age;
    private String email;

    public Employee() {
        this.dni = "";
        this.name = "";
        this.age = -1;
        this.email ="";
    }

    public Employee(String dni, String name, int age, String email) {
        this.dni = dni;
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public String getDNI() {
        return this.dni;
    }

    public void setDNI(String dni) {
        this.dni = dni;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Employee)) return false;

        Employee employee = (Employee) obj;
        return this.age == employee.age && this.dni.equals(employee.dni)
                && this.name.equals(employee.name) && this.email.equals(employee.email);
    }

    @Override
    public int hashCode() {
        String str = this.dni + this.name + this.age + this.email;
        return str.hashCode();
    }

    @Override
    public String toString() {
        String str = this.name + " (" + this.age + ") - " + this.dni + "\n";
        str += "email: " + this.email;

        return str;
    }
}