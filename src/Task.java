public class Task {
    Integer id;
    String title;
    Status status;
    String desc;
    User assignedTo;

    public void display() {
        System.out.println("This is created by builder and task is assigned to " + this.assignedTo.name + " " + this.title + " Status of task " + this.status);
    }

    public Integer getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public Status getStatus() {
        return  this.status;
    }

    public String getDesc() {
        return  this.desc;
    }

    public void updateStatus(Status status) {
        this.status = status;
    }


}
