package epamtasks.informationHanding.t01;



import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CrazyLogger {
   private StringBuilder log;
   private static LocalDateTime localDateTime;
   private static final DateTimeFormatter formatter;

   static {
       formatter =  DateTimeFormatter.ofPattern("dd-MM-YYYY:HH-mm - ");
       localDateTime = LocalDateTime.now();
   }

    public CrazyLogger(){
        log = new StringBuilder();

        log.append(localDateTime.format(formatter)).append("Log was created.\n");
    }

    public boolean isPresentContent(String message){
        return log.toString().contains(message);
    }

    public boolean isPesentContentWithDAT(LocalDateTime dateTime){
        return isPresentContent(dateTime.format(formatter));
    }

    public CrazyLogger addMessage(String message){
        log.append(localDateTime.now().format(formatter))
                .append(message).append('\n');
        return  this;
    }
    public String getMessageWithDAT(LocalDateTime dateTime){
        if(!isPesentContentWithDAT(dateTime)) return "NOT_PRESENT";
        Matcher matcher = getLogMatcher();
        while(matcher.find()){
            System.out.println( matcher.group(1));
            if(matcher.group(1).contains(dateTime.format(formatter)))
                return matcher.group(2);
        }
        return "NOT_FOUND";
    }
    public String getFullMessageWithContent(String content){
       if(!isPresentContent(content))return "NOT_FOUND";
       Matcher matcher = getLogMatcher();
        while(matcher.find()){
            if(matcher.group(2).contains(content))
                return matcher.group(2);
        }
        return "NOT_FOUND";
    }
    private  Matcher getLogMatcher(){
        String regexp ="(\\d{2}-\\d{2}-\\d{4}:\\d{2}-\\d{2} - )(.*)";
               return  Pattern.compile(regexp).matcher(getLog().toString());
    }
    public StringBuilder getLog() {
        return log;
    }

    public CrazyLogger printLogContent(){
        System.out.print(log);
        return  this;
    }



}
