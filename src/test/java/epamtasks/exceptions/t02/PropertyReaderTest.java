package epamtasks.exceptions.t02;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

class PropertyReaderTest {


    @Test
    void initialiseResourceTest(){
        String bundle = "QuestionsBundle";
        String wrongBundle= "notPresent";
        Locale locale = new Locale("ru","RU");
        PropertyReader pr = new PropertyReader();
        System.out.println(pr);
       assertTrue(pr.initialiseResource(bundle,locale));
        System.out.println(pr);
        pr.clearContent();
        assertFalse(pr.initialiseResource(wrongBundle,locale));
        System.out.println(pr);
        assertTrue(pr.initialiseResource(bundle,new Locale("en")));
        System.out.println(pr);
        pr.clearContent();
        assertTrue(pr.initialiseResource(bundle,new Locale("ru")));
        System.out.println(pr);
        pr.clearContent();
        assertTrue(pr.initialiseResource(bundle,new Locale("en","US")));
        System.out.println(pr);
    }

    @Test
    void getValByKey(){
        PropertyReader pr = new PropertyReader();
        assertTrue(pr.initialiseResource("QuestionsBundle",new Locale("en")));
        System.out.println(pr);
        assertTrue(pr.releaseKey("q1"));
        System.out.println(pr.getKeyContent());
        assertTrue(pr.releaseKey("a1"));
        System.out.println(pr.getKeyContent());
        assertFalse(pr.releaseKey("wrongKey"));
    }

    @Test
    void initialiseResourceTest2(){
        PropertyReader pr= new PropertyReader();
        String fileName = "QuestionsBundle";
        assertTrue(pr.initialiseResource(fileName));
        System.out.println(pr);
        System.out.println(Locale.getDefault().getCountry());
    }

}