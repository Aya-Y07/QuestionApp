package mx.edu.greengates.questionapp.data.model;

import java.util.List;

public class Users_record {
    private final List<User_record> mistakes ;

    public Users_record(List<User_record> mistakes){
        this.mistakes = mistakes;
    }

    public List<User_record> getMistakes(){
        return mistakes;
    }

    public User_record getMistakes(int index){
        return mistakes.get(index);
    }

    public void addMistakes(User_record mistake){
        mistakes.add(mistake);
    }

}
