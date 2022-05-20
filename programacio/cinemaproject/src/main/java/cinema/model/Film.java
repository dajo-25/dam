package cinema.model;

//https://www.jetbrains.com/help/idea/postgresql.html
//https://stackoverflow.com/questions/63723867/how-to-add-a-postgresql-jdbc-driver-to-intellij

public class Film implements Comparable<Film> {
    private String title;
    private String description;
    private int duration;

    public Film() {
        this.title = "";
        this.description = "";
        this.duration = 0;
    }

    public Film(String title, String description, int duration) {
        this.title = title;
        this.description = description;
        this.duration = duration;
    }

    public Film(Film film) {
        this.title = film.title;
        this.description = film.description;
        this.duration  = film.duration;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public int hashCode() {
        String attributes = this.title + this.description + this.duration;
        return attributes.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        else if(obj instanceof Film) {
            Film film = (Film) obj;
            return this.title.equals(film.title) && this.description.equals(film.description) && this.duration == film.duration;
        }
        return false;
    }

    @Override
    public String toString() {
        String str = this.title + " (" + this.duration + ")\n";
        str += this.description;
        return str;
    }

    @Override
    public int compareTo(Film film) {
        int titleComp = this.title.compareTo(film.title);
        int descComp = this.description.compareTo(film.description);
        int durationComp = this.duration - film.duration;
        if(titleComp == 0) {
            if(descComp == 0) {
                return durationComp;
            }
            return descComp;
        }
        return titleComp;

        /*String tattributes = this.title + this.description + this.duration;
        String fattributes = film.title + film.description + film.duration;
        return tattributes.compareTo(fattributes);*/
    }
}
