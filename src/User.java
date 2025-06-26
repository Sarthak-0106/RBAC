import java.util.List;

public class User {
    Integer id;
    String name;
    Role role;

    public User(Integer id, String name) {
        this.id = id;
        this.name= name;
    }

    public void addRole(List<Permissions> p, RoleName roleName) {
        this.role = new Role(1, roleName, p);
    }
}
