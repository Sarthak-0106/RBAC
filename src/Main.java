import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<User> users = new ArrayList<>();
    public static List<List<Permissions>> availableRoles = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("This is the start");

        Scanner sc = new Scanner(System.in);

        List<Permissions> admin = new ArrayList<>();
        admin.add(Permissions.CREATE_TASK);
        admin.add(Permissions.VIEW_ROLE);
        admin.add(Permissions.DELETE_TASK);
        admin.add(Permissions.DELETE_USER);
        admin.add(Permissions.UPDATE_USER);

        List<Permissions> manager = new ArrayList<>();
        manager.add(Permissions.DELETE_TASK);
        manager.add(Permissions.UPDATE_USER);
        manager.add(Permissions.UPDATE_TASK);

        List<Permissions> contributor = new ArrayList<>();
        contributor.add(Permissions.UPDATE_TASK);

        availableRoles.add(admin);
        availableRoles.add(manager);
        availableRoles.add(contributor);

        User user = new User(1, "Sarthak");
        user.addRole(admin, "Admin");
        User user2 = new User(2, "Sarthak2");
        user2.addRole(manager, "Manager");
        User user3= new User(3, "Sarthak3");
        user3.addRole(contributor, "Contributor");

        users.add(user);
        users.add(user2);
        users.add(user3);

        login();
    }

    public static void adminFunction(){
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Enter a choice");
            System.out.println("1 : add a user");
            System.out.println("2 : view all users");
            System.out.println("3: Add Role to a user");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter User ID");
                    int id = sc.nextInt();
                    System.out.println("Enter Name");
                    sc.nextLine();
                    String name = sc.nextLine();

                    User user = new User(id, name);
                    users.add(user);
                    System.out.println("user created" + " " + user.name);
                    break;

                case 2:
                    for (User u : users) {
                        System.out.println(u.name);
                        if (u.role != null) {
                            System.out.println("Role: " + u.role.roleName);
                        } else {
                            System.out.println("Role hasn't been assigned yet !");
                        }
                    }
                    break;

                case 3:
                    System.out.println("Enter a user id");
                    id = sc.nextInt();
                    User current = null;
                    for(User u : users) {
                        if(u.id == id) {
                            current = u;
                        }
                    }

                    if(current != null) {
                        System.out.println("Enter a role 1: Admin, 2: Manager, 3: Contributor");
                        int role = sc.nextInt();
                        String roleName = "";
                        if (role == 1) {
                            roleName = "Admin";
                        } else if (role == 2) {
                            roleName = "Manager";
                        } else if (role == 3) {
                            roleName = "Contributor";
                        } else {
                            System.out.println("Invalid Choice");
                            break;
                        }

                        current.addRole(availableRoles.get(role - 1), roleName);
                        System.out.println("Role for User" + " " + current.id + " " + current.name + " has been updated to" + " " + current.role.roleName);
                    } else {
                            System.out.println("Invalid User ID");
                    }
                    break;

                case 4:
                    System.out.println("Going to login side");
                    login();
                    return;

                case 5:
                    System.out.println("Exited bye !");
                    return;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public static void login(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your UserID");
        User currentUser = null;
        int id = sc.nextInt();
        for(User u: users) {
            if(u.id == id) {
                currentUser = u;
            }
        }
        if(currentUser != null) {
                if (currentUser.role != null) {
                    switch (currentUser.role.roleName) {
                        case "Admin" -> {
                            System.out.println("Hello " + currentUser.name + " You are a " + currentUser.role.roleName);
                            adminFunction();
                        }
                        case "Manager" -> {
                            System.out.println("Hello " + currentUser.name + " You are a Manager");
                            adminFunction();
                        }
                        case "Contributor" -> {
                            System.out.println("Hello " + currentUser.name + " You are a Contributor" );
                            adminFunction();
                        }
                    }
                } else {
                    System.out.println("Contact admin to assign you role");
                    login();
                }
        } else {
            System.out.println("Invalid User id");
        }
    }
}

