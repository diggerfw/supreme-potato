package ua.org.klug.planerka.model;

public class Meeting {
    private String when;
    private String where;

    public Meeting(String when, String where) {
        this.when = when;
        this.where = where;
    }

    public String getWhere() {
        return where;
    }

    public String getWhen() {
        return when;
    }
}
