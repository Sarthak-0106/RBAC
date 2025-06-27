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
        user.addRole(admin, "ADMIN");
        User user2 = new User(2, "Sarthak2");
        user2.addRole(manager, "MANAGER");
        User user3= new User(3, "Sarthak3");
        user3.addRole(contributor, "CONTRIBUTOR");

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
            System.out.println("3 : Add Role to a user");
            System.out.println("4 : Go to Login");
            System.out.println("5 : Exit");
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

                    createRole(user);

                    break;

                case 2:
                    for (User u : users) {
                        System.out.println(u.name);
                        if (u.role != null) {
                            System.out.println("Role: " + u.role.name);
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
                        String roleName = null;
                        if (role == 1) {
                            roleName = "ADMIN";
                        } else if (role == 2) {
                            roleName = "MANAGER";
                        } else if (role == 3) {
                            roleName = "CONTRIBUTOR";
                        } else {
                            System.out.println("Invalid Choice");
                            break;
                        }

                        current.addRole(availableRoles.get(role - 1), roleName);
                        System.out.println("Role for User" + " " + current.id + " " + current.name + " has been updated to" + " " + current.role.name);
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

    public static void createRole(User user) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1 : Create new role, 2 : Add existing role");
        int ch = sc.nextInt();

        if (ch == 1) {
            System.out.println("Enter the name for role");
            sc.nextLine();
            String name = sc.nextLine();

            Permissions[] allPerms = Permissions.values();
            System.out.println("Select permissions to add (by number):");

            for (int i = 0; i < allPerms.length; i++) {
                System.out.println((i + 1) + ". " + allPerms[i]);
            }

            System.out.println("Enter comma-separated numbers (e.g., 1,3,5):");
            String input = sc.nextLine();

            List<Permissions> perms = new ArrayList<>();

            try {
                String[] parts = input.split(",");
                for (String part : parts) {
                    int choice = Integer.parseInt(part.trim());
                    if (choice >= 1 && choice <= allPerms.length) {
                        perms.add(allPerms[choice - 1]); // index is (choice - 1)
                    } else {
                        System.out.println("Invalid choice: " + choice);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input format.");
                return;
            }

            Role role = new Role(4, name, perms);
            user.addRole(role);
            System.out.println("Created role: " + role.name + " with permissions: " + perms);
        } else if (ch == 2) {
            System.out.println("Enter a role 1: Admin, 2: Manager, 3: Contributor");
            int role = sc.nextInt();
            String roleName = null;
            if (role == 1) {
                roleName = "ADMIN";
            } else if (role == 2) {
                roleName = "MANAGER";
            } else if (role == 3) {
                roleName = "CONTRIBUTOR";
            } else {
                System.out.println("Invalid Choice");
            }
            user.addRole(availableRoles.get(role - 1), roleName);
        } else {
            System.out.println("Invalid Choice");
        }
    }

    public static void managerFunction(){
        System.out.println("This is Manager functions");

        Scanner sc = new Scanner(System.in);
        System.out.println("1: Create Task");

        User user = users.get(2);

        TaskBuilder taskBuilder = new TaskBuilder();
        TaskDirector taskDirector = new TaskDirector();

        taskDirector.construct(taskBuilder, user);
        Task task = taskBuilder.getTask();

//        task.updateStatus(Status.COMPLETED);
        task.display();

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
                    switch (currentUser.role.name) {
                        case "ADMIN" -> {
                            System.out.println("Hello " + currentUser.name + " You are a " + currentUser.role.name);
                            adminFunction();
                        }
                        case "MANAGER" -> {
                            System.out.println("Hello " + currentUser.name + " You are a Manager");
                            managerFunction();
                        }
                        case "CONTRIBUTOR" -> {
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

