package exam.company.model;

public class Payroll {
    private int irpf;
    private int month;
    private int year;
    private Category category;

    public Payroll() {
        this.irpf = 0;
        this.month = 0;
        this.year = 0;
        this.category = new Category();
    }

    public Payroll(int irpf, int month, int year, Category category) {
        this.irpf = irpf;
        this.month = month;
        this.year = year;
        this.category = new Category(category);
    }

    public int getIrpf() {
        return this.irpf;
    }

    public void setIrpf(int irpf) {
        this.irpf = irpf;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Category getCategory() {
        return this.category;
    }

    public int getRealSalary() {
        return (int) (this.category.getBaseSalary() * (1 - this.irpf/100.0f));
    }

    public void setCategory(Category category) {
        this.category = new Category(category);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Payroll)) return false;

        Payroll payroll = (Payroll) obj;

        return this.irpf == payroll.irpf && this.month == payroll.month
                && this.year == payroll.year && this.category.equals(payroll.category);
    }

    @Override
    public int hashCode() {
        String str = this.irpf + "" + this.month + "" + this.year + this.category.toString();
        return str.hashCode();
    }

    @Override
    public String toString() {
        String str = "Nòmina corresponent al mes " + this.month + "/" + this.year + "\n";
        str += "IRPF: " + this.irpf + "\n";
        str += this.category.toString() + "\n";
        str += "Sou net: " + this.getRealSalary();
        return str;
    }
}
