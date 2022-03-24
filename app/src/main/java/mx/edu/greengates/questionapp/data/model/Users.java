package mx.edu.greengates.questionapp.data.model;

import java.util.List;

public class Users {

    private final List<User> users;

    public Users(List<User> users){
        this.users = users;
    }

    public List<User> getUsers(){
        return users;
    }

    public User getUser(int index){
        return users.get(index);
    }

    public void addUser(User user){
        users.add(user);
    }

}
