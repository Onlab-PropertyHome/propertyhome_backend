package hu.bme.aut.onlabpropertyhome.model;

public class UserDTO {
    private String name;
    private String email;
    private String password;
    private String tel;

    public UserDTO() { }
    public UserDTO(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.tel = user.getTel();
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return this.name;
    }
    public String getEmail() {
        return this.email;
    }
    public String getPassword() {
        return this.password;
    }
    public String getTel() {
        return this.tel;
    }
}
