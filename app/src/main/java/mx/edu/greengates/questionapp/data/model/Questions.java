package mx.edu.greengates.questionapp.data.model;

import java.util.ArrayList;
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

    public List<String> getAllTopics(){
        List<String> topics = new ArrayList<>();
        for( Question question: questions){
            if(!topics.contains(question.getId())){
                topics.add(question.getId());
            }
        }
        return topics;
    }

    public List<Question> getAllQuestionsByTopic(String topic){
        List<Question> topicQuestions = new ArrayList<>();
        for( Question question: questions){
            if((question.getId().compareTo(topic)==0)){
                topicQuestions.add(question);
            }
        }
        return topicQuestions;
    }
}
