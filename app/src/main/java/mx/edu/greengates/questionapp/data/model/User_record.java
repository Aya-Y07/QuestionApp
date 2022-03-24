package mx.edu.greengates.questionapp.data.model;

public class User_record {
    private String id;
    private String userId;
    private String mistaken_question_id;
    private String score;
    private String accuracy_rate;
    private String time_taken;
    private String date;


    public User_record(String id, String userId, String mistaken_question_id, String score, String accuracy_rate, String time_taken, String date){

        this.id = id;
        this.userId = userId;
        this.mistaken_question_id = mistaken_question_id;
        this.score = score;
        this.accuracy_rate = accuracy_rate;
        this.time_taken = time_taken;
        this.date = date;


    }
    public String getId(){return id;}
    public String getUserId(){ return userId;}
    public String getMistaken_question_id(){ return mistaken_question_id;}
    public String getScore(){ return score;}
    public String getAccuracy_rate(){ return accuracy_rate;}
    public String getTime_taken(){ return time_taken;}
    public String getDate(){ return date;}

    @Override
    public String toString(){
        return "Question{" + "id ='" + id + '\'' + ", user ID = '" + userId  + '\'' + ", Mistaken Question ID = '" + mistaken_question_id  + '\'' + ", Score = '" + score  + '\'' + ", Accuracy rate = '" + accuracy_rate  + '\'' + ", Time Taken = '" + time_taken  + '\'' + ", Date = '" + date  + '\'' + '}';
    }
}
