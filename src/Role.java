import java.util.List;

public class Role {
    Integer id;
    List<Permissions> perms;
    String name;

    public Role (Integer id, String name, List<Permissions> perms) {
        this.id = id;
        this.name = name;
        this.perms = perms;
    }
}
