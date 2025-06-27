public class TaskBuilder implements Builder {
    private Task task;

    public TaskBuilder() {
        this.task = new Task();
    }

    @Override
    public void setAssignedTo(User user) {
        task.assignedTo = user;
    }

    @Override
    public Task getTask(){
        return task;
    }

    @Override
    public void setStatus(Integer id, String title){
        task.status= Status.ASSIGNED;
        task.id= id;
        task.title= title;
    }


}
