package accounts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
@PersistenceUnit
@SuppressWarnings("UnusedDeclaration")
public class UserProfile implements Serializable {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @SuppressWarnings("UnusedDeclaration")
    public UserProfile() {
    }


    public UserProfile(long id,String login, String pass) {
        this.setId(id);
        this.login = login;
        this.password = pass;
    }

    public UserProfile(String login, String password)
    {
        this.setId(-1);
        this.setLogin(login);
        this.setPassword(password);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
