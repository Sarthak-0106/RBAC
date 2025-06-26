import java.util.List;

public class Role {
    Integer id;
    RoleName roleName;
    List<Permissions> perms;

    public Role (Integer id, RoleName roleName, List<Permissions> perms) {
        this.id = id;
        this.roleName = roleName;
        this.perms = perms;
    }
}
