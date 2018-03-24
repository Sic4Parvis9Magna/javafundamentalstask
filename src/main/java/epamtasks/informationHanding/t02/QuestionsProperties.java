package epamtasks.informationHanding.t02;


import java.util.Enumeration;
import java.util.Locale;
import java.util.ResourceBundle;

public class QuestionsProperties {

    private static String bundleName = "QuestionsBundle";

   public static void printQuestions(Locale locale){
        ResourceBundle labels = ResourceBundle.getBundle(bundleName,locale);
        Enumeration bundleKeys = labels.getKeys();
        int counter1 =1;
        int counter2=1;
       while (bundleKeys.hasMoreElements()) {

               String key = (String) bundleKeys.nextElement();
               String value = labels.getString(key);
           if(counter1%2 != 0) {
               System.out.printf("%d: %s%n", counter2++, value);
           }
           counter1++;
       }
       System.out.println();
    }

    public static void main(String... args) {
        ResourceBundle labels = ResourceBundle.getBundle(bundleName,new Locale("ru"));
        Enumeration bundleKeys = labels.getKeys();
        Locale loc = new Locale("en","US");
       printQuestions(loc);


    }
}
