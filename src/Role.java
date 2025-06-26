import java.util.List;

public class Role {
    Integer id;
    String roleName;
    List<Permissions> perms;

    public Role (Integer id, String roleName, List<Permissions> perms) {
        this.id = id;
        this.roleName = roleName;
        this.perms = perms;
    }
}
