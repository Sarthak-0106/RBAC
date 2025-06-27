public class TaskDirector {
    public void construct(Builder builder, User user) {
        builder.setAssignedTo(user);
        builder.getTask();
        builder.setStatus(user.id, "");
    }

}
