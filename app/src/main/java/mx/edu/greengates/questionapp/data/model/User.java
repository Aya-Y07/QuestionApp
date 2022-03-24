package mx.edu.greengates.questionapp.data.model;


public class User {

    public String username;
    public String userId;
    public String password;
    public String emailaddress;
    public String surname;
    public String firstName;
    public String phone_Number;

    public User(String username, String userId, String password, String emailaddress, String surname, String firstName, String phone_Number){

        this.username = username;
        this.userId = userId;
        this.password = password;
        this.emailaddress = emailaddress;
        this.firstName = firstName;
        this.surname = surname;
        this.phone_Number = phone_Number;
    }

    public String getUsernamet(){return username;}
    public String getUserId(){ return userId;}
    public String getEmailaddress(){return emailaddress;}
    public String getFirstName(){return firstName;}
    public String getSurname(){return surname;}
    public String getPhone_Number(){return phone_Number;}
    public String getPassword(){return password;}


    @Override
    public String toString(){
        return "Question{" + "Username ='" + username + '\'' + ", User ID = '" + userId + '\'' + ", Email address = '" + emailaddress + '\'' + ", First Name = '" + firstName + '\'' + ", Surname = '" + surname + '\'' + ", Phone number = '" + phone_Number + '\'' + '}';
    }
}

