package mx.edu.greengates.questionapp.data.model;

import java.util.List;

public class Questions {

    private final List<Question> questions;

    public Questions(List<Question> questions){
        this.questions = questions;
    }

    public List<Question> getQuestions(){
        return questions;
    }

    public Question getQuestion(int index){
        return questions.get(index);
    }

    public void addQuestion(Question question){
        questions.add(question);
    }

}
