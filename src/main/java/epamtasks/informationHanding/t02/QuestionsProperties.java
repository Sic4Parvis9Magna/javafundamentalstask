package epamtasks.informationHanding.t02;


import lombok.extern.log4j.Log4j2;

import java.util.*;

public class QuestionsProperties {
    private static Scanner scanner;
    private static String bundleName;
    private Locale locale;
    private List<String> questions;
    private List<String> answers;

    static {
        bundleName= "QuestionsBundle";
        scanner = new Scanner(System.in);
    }
    public QuestionsProperties(){
         locale = Locale.getDefault();
         questions = new ArrayList<>();
         answers = new ArrayList<>();
    }

    public QuestionsProperties initialiseProperties(Locale locale ){
        this.locale = locale;
        ResourceBundle labels = ResourceBundle.getBundle(bundleName,locale);
        Enumeration bundleKeys = labels.getKeys();
        int counter =1;
        while (bundleKeys.hasMoreElements()) {

            String key = (String) bundleKeys.nextElement();
            String value = labels.getString(key);
            if(counter%2 != 0) {
                questions.add(value);
            }else {
                answers.add(value);
            }
            counter++;
        }
        return this;
    }
    public  QuestionsProperties printQuestions(){

       for (int i =0 ; i < questions.size(); i++){
           System.out.printf("%d: %s%n",i,questions.get(i));
       }
       System.out.println();
       return this;
    }
    public QuestionsProperties printAnswer(int numberOfQuestion){
        System.out.printf("%s%n",answers.get(numberOfQuestion));
        return this;
    }
    public int getNumberOfQuestions(){
        return questions.size();
    }

    public static int getUserAns(String message,int min,int max){
        System.out.println(message);
        int ans;
        do{
            ans=scanner.nextInt();
        }while(ans < min || ans > max);
        return ans;
    }
    public static void main(String... args) {
        QuestionsProperties properties = new QuestionsProperties();
        int language = getUserAns("1.English\n2.Russian",1,2);
        properties.initialiseProperties((language>1)?new Locale("ru","RU")
                :new Locale("en","US"))
                .printQuestions();
        int question = getUserAns((language>1)?"Выберите нормер вопроса\n":"Choose question's number\n",
                0,properties.getNumberOfQuestions());
        properties .printAnswer(question);
    }
}
