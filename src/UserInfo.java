public class UserInfo {
    private String first_name;
    private String last_name;
    private String email;
    private String presents;
    private int id;

    public UserInfo(String first_name, String last_name, String email, String presents, int id) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.presents = presents;
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPresents() {
        return presents;
    }

    public void setPresents(String presents) {
        this.presents = presents;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
