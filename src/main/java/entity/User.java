package entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
@SuppressWarnings("UnusedDeclaration")
@Entity
@Table(name = "users")
public class User {
    private static final long serialVersionUID = -8706689714326132798L;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password", unique = true, updatable = false)
    private String password;

    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public User() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public User(long id, String login, String password) {
        this.setId(id);
        this.setLogin(login);
        this.setPassword(password);
    }


    @SuppressWarnings("UnusedDeclaration")
    public String getLogin() {
        return login;
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDataSet{" +
                "id=" + id +
                ", login=" + login +
                ", password=" + password
                + '\'' +
                '}';
    }
}