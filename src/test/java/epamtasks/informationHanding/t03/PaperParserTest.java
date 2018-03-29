package epamtasks.informationHanding.t03;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

class PaperParserTest {
    static String text = "Графит, одно из самых мягких природных веществ, " +
            "непрозрачен имеет металлический блеск, проводит электрический ток. " +
            "В графите атомы углерода располагаются слоями, " +
            "слои легко расщепляются превращаясь в чешуйки (Рис. 18). " +
            "Почему в структуре графита, ядра атомов углерода (Рис. 18)" +
            " расположены в вершинах правильных шестиугольников?" +
            " Свободные спиновые неэлектростатические заряды протонов и" +
            " лёгких нейтронов в трёх альфах ядра атома углерода взаимно" +
            " ориентированы в трёх направлениях – плоскостях нейтрализации." +
            " Ориентация трёх альф (Рис. 9) в ядерной трубке – ядре углерода," +
            " с взаимным относительным смещением в 600 определяет форму силового" +
            " геометрического взаимодействия атомов углерода в графите.";
    static String simpleEng = "(Rex. 9)";
    static String simpleRus = "(Рис. 9)";

    @Test
    void parseTextTest1(){
        String regexp = "\\(\\w+\\.\\s+(\\d+)\\)";
        String regexp2 = "\\([а-яА-Я]+\\.\\s+(\\d+)\\)";
        String regexp3 = "\\(Рис\\.\\s+(\\d+)\\)";
        Pattern p = Pattern.compile(regexp3);
        Matcher m= p.matcher(text);
       // assertTrue(m.find());

        while (m.find()) {
            System.out.println(m.group());
            System.out.println(m.group(1));
        }
    }

    @Test
    void parseTextTest2(){

        String regexp = "[\\(Рис\\.\\s+(\\d+)\\)]";

        String allCharExPoint2 = "[^!?[А-Я&&[^Р]]]*";
        String my =" расположены в <..>4252yijls вершинах (Рис. 9) правильных шестиугольников ?";
        String sentenceRegexp2 = "([А-Я]"+allCharExPoint2+"[\\.?!])\\s*[А-Я]";
        Pattern p = Pattern.compile(sentenceRegexp2);
        Matcher m= p.matcher(text);
       //assertTrue(m.find());
        int i=0;
        int sentenceCounter =0;
        while (m.find(i)) {
            System.out.printf("You find %d sentense%n",sentenceCounter++);
            i = m.end()-1;
            System.out.println(m.group(1));

        }
    }

    @Test
    void parseText3(){
        PaperParser parser = new PaperParser()
                .initialiseContent();
        System.out.println(parser.getContent());
        String text = parser.getContent();
        String regexp = "\\(Рис\\.([и\\s+\\d+]*)\\)";
        String allCharExPoint2 = "[^!?[А-Я&&[^Р]]]*";
        String sentenceRegexp2 = "([А-Я]"+allCharExPoint2+"[\\.?!])\\s*[А-Я]";
        String sentenceRegexp3 = "([А-Я][["+regexp+"]*[^!\\?]*]*[\\.!\\?]{1})\\s[[А-Я]\\<\\&]";
        Pattern p = Pattern.compile(sentenceRegexp3);
        Matcher m= p.matcher(text);
        int i=0;
        int sentenceCounter =0;
        while (m.find(i)) {
            i = m.end(1);
            System.out.println(m.group(1)+"\n");

        }
    }


}