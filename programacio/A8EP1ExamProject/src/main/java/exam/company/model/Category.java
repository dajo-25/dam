package exam.company.model;

public class Category {
    private String name;
    private int baseSalary;

    public Category() {
        this.name = "";
        this.baseSalary = -1;
    }

    public Category(String name, int baseSalary) {
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public Category(Category category) {
        this.name = category.name;
        this.baseSalary = category.baseSalary;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBaseSalary() {
        return this.baseSalary;
    }

    public void setBaseSalary(int baseSalary) {
        this.baseSalary = baseSalary;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Category)) return false;

        Category category = (Category) obj;
        return this.baseSalary == category.baseSalary && this.name.equals(category.name);
    }

    @Override
    public int hashCode() {
        String str = this.name + this.baseSalary;
        return str.hashCode();
    }

    @Override
    public String toString() {
        return "Categoria " + this.name + " (salari base: " + this.baseSalary + "€)";
    }
}
