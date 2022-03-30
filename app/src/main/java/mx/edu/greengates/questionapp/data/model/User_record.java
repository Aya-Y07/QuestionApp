package mx.edu.greengates.questionapp.data.model;

public class User_record {
    private String id;
    private String username;
    private String score;
    private String accuracy_rate;
    private String time_taken;
    private String date;


    public User_record(String id, String username, String score, String accuracy_rate, String time_taken, String date){

        this.id = id;
        this.username = username;
        this.score = score;
        this.accuracy_rate = accuracy_rate;
        this.time_taken = time_taken;
        this.date = date;


    }
    public String getId(){return id;}
    public String getUsername(){ return username;}
    public String getScore(){ return score;}
    public String getAccuracy_rate(){ return accuracy_rate;}
    public String getTime_taken(){ return time_taken;}
    public String getDate(){ return date;}

    @Override
    public String toString(){
        return "Question{" + "id ='" + id + '\'' + ", User Name = '" + username  + '\'' +  ", Score = '" + score  + '\'' + ", Accuracy rate = '" + accuracy_rate  + '\'' + ", Time Taken = '" + time_taken  + '\'' + ", Date = '" + date  + '\'' + '}';
    }
}
