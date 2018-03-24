package epamtasks.informationHanding.t01;

import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class CrazyLoggerTest {
    LocalDateTime dateTime= LocalDateTime.now() ;
    DateTimeFormatter  formatter =  DateTimeFormatter.ofPattern("dd-MM-YYYY:HH-mm - ");
    @Test
    void getLogTest() {
        System.out.println("Test \"getLog\"");
        CrazyLogger logger1 = new CrazyLogger();
        StringBuilder message = new StringBuilder(dateTime.format(formatter))
                .append("Log was created.");
        System.out.println(message);
        System.out.println(logger1.getLog());
        assertTrue(logger1.getLog().toString()
                .contains(message.toString()));
        assertTrue(message.toString().trim()
                .contains(logger1.getLog().toString().trim()));

    }

    @Test
    void addMessageTest(){
        System.out.println("Test \"addMessage\"");
        CrazyLogger logger2 = new CrazyLogger();
        String message = "New log message!";
        String message2 = "Second log message!";
        logger2.addMessage(message).printLogContent();
        assertTrue(logger2.getLog().toString()
                .contains(message.toString()));
        logger2.addMessage(message2).printLogContent();
        assertTrue(logger2.getLog().toString()
                .contains(message2.toString()));

    }

    @Test
    void isPresentContentTest(){
        CrazyLogger logger3 = new CrazyLogger();
        String message = "1984";
        String message2 = "666";
        System.out.println("Test \"isPresentContent\"");
        logger3.addMessage(message)
                .addMessage(message2)
                .addMessage("content")
        .printLogContent();
        assertTrue(logger3.isPresentContent(message));
        assertTrue(logger3.isPresentContent(message2));
        assertTrue(logger3.isPresentContent(" content"));
        assertTrue(logger3.isPresentContent("content"));
        assertFalse(logger3.isPresentContent(" content "));
        assertFalse(logger3.isPresentContent("2048 "));

    }

    @Test
    void isPesentDateWithContentTest(){
        LocalDateTime dateTime1 = LocalDateTime.now();
        CrazyLogger logger4 = new CrazyLogger()
                .addMessage("Date")
                .addMessage("and time API")
                .printLogContent();
        assertTrue(logger4.isPesentContentWithDAT(dateTime1));
        LocalDateTime dateTime2 = dateTime1.plusMinutes(26);
       assertFalse(logger4.isPesentContentWithDAT(dateTime2));
    }

    @Test
    void regexpTest1(){

        String regexp ="(\\d{2}-\\d{2}-\\d{4}:\\d{2}-\\d{2} - )(.*)";
        Pattern p = Pattern.compile(regexp);
        Matcher matcher = p.matcher(dateTime.format(formatter).toString());
        System.out.println(dateTime.format(formatter).toString());
        assertTrue(matcher.find());
        System.out.println(matcher.group(1));
        System.out.println(matcher.group(2));
    }
    @Test
    void regexpTest2(){

        String regexp ="(\\d{2}-\\d{2}-\\d{4}:\\d{2}-\\d{2} - )(.*)";
        Pattern p = Pattern.compile(regexp);
        CrazyLogger crazyLogger = new CrazyLogger();
        Matcher matcher = p.matcher(crazyLogger.getLog());

        assertTrue(matcher.find());
        assertEquals("Log was created.",matcher.group(2));
        System.out.println(matcher.group(2));
    }

    @Test
    void regexpTest3(){

        String regexp ="(\\d{2}-\\d{2}-\\d{4}:\\d{2}-\\d{2} - )(.*)";
        Pattern p = Pattern.compile(regexp);
        CrazyLogger crazyLogger = new CrazyLogger()
                .addMessage("Masha")
                .addMessage("run application\n")
                .addMessage("\t divide strings");
        String[] strings = {"Log was created.",
                "Masha",
                "run application",
                "\t divide strings"};
        int i=0;
        String catchedMessage;
        Matcher matcher = p.matcher(crazyLogger.getLog());
        while(matcher.find() && i < strings.length){
            assertEquals(strings[i++],catchedMessage = matcher.group(2));
            System.out.println( catchedMessage);
        }

    }

    @Test
    void parseByDateAndTimeTest(){
        LocalDateTime dateTime1= LocalDateTime.now() ;
        CrazyLogger logger = new CrazyLogger();
        System.out.println(logger.getMessageWithDAT(dateTime));


        logger.addMessage("some maessage");
        try {
            Thread.sleep(60*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.addMessage("new");
        logger.addMessage("super");

        assertEquals("new",logger.getMessageWithDAT(dateTime1.now()));

    }

    @Test
    void getFullMessageWithContent(){
        CrazyLogger logger = new CrazyLogger()
                .addMessage("my new message")
                .addMessage("second mes")
                .addMessage("third message");
        assertEquals("third message",
                logger.getFullMessageWithContent("third"));
        assertEquals("second mes",
                logger.getFullMessageWithContent("sec"));
        assertEquals("my new message",
                logger.getFullMessageWithContent("new"));

        assertEquals("NOT_FOUND",
                logger.getFullMessageWithContent("666"));


    }

}