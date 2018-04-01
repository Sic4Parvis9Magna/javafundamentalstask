package epamtasks.informationHanding.t03;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static epamtasks.informationHanding.t03.PaperParser.parseNumber;
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
            " Ориентация трёх альф (Рис. 19) в ядерной трубке – ядре углерода," +
            " с взаимным относительным смещением в 600 определяет форму силового" +
            " геометрического взаимодействия атомов углерода в графите.";
    static String simpleEng = "(Rex. 9)";
    static String simpleRus = "(Рис. 9)";

    @Test
    void parseSimpeRefTest(){

        String regexp = "\\(Рис\\.\\s+(\\d+)\\)";
        Pattern p = Pattern.compile(regexp);
        Matcher m= p.matcher(text);
        assertTrue(m.find());
        assertEquals("(Рис. 18)",m.group());
        System.out.printf("group#0:%s%ngroup#1:%s%n",m.group(),m.group(1));
        assertTrue( m.find());
        assertEquals("18",m.group(1));
        System.out.printf("group#0:%s%ngroup#1:%s%n",m.group(),m.group(1));
        if(m.find())
                System.out.printf("group#0:%s%ngroup#1:%s%n",m.group(),m.group(1));


    }

    @Test
    void parseNumberTest(){
        String ex1 = "(Рис. 9)";
        String ex2 = "(Рис. 9 и 10)";
        String ex3 = " 9, 10 и 11";
       assertTrue(parseNumber(ex1).contains(9));
       assertTrue(parseNumber(ex2).contains(10));
        assertTrue(parseNumber(ex3).contains(11));

    }

    @Test
    void parseHardRefText() {
        String text1 = "Ориентация трёх альф (Рис. 19,20, 47, 8 и 15) в ядерной трубке – ядре углерода,";
        String regexp4 ="\\(Рис\\.([и\\s+\\d+\\,+]*)\\)" ;
        Pattern p = Pattern.compile(regexp4);
        Matcher m= p.matcher(text1);
        assertTrue(m.find());
        System.out.println(m.group(1));
        List<Integer> num = parseNumber(m.group(1));
        assertTrue(num.contains(19));
        assertTrue(num.contains(20));
        assertTrue(num.contains(47));
        assertTrue(num.contains(15));
        assertTrue(num.contains(8));
        System.out.println(num);

    }
    @Test
    void parseHardRefText2() {
        PaperParser pp = new PaperParser();
        assertTrue(pp.initialiseContent());
        String regexp4 ="\\(Рис\\.([и\\s+\\d+\\,+]*)\\)" ;
        Pattern p = Pattern.compile(regexp4);
        Matcher m= p.matcher(pp.getContent());
        Set<Integer> nums = new HashSet<>();
        while (m.find()){
            System.out.println(m.group());
            nums.addAll(parseNumber(m.group(1)));
        }
        System.out.println(nums);
        assertTrue(nums.contains(26));
        assertTrue(nums.contains(18));
    }

    @Test
    void isSeqRefPaperTest(){
        PaperParser pp = new PaperParser();
        assertTrue(pp.initialiseContent());
        System.out.println( pp.getContent());
        assertFalse(pp.isSeqRefPaper());
    }

    @Test
    void parseTextForSentenceTest1(){
        PaperParser parser = new PaperParser();
        assertTrue(parser.initialiseContent());
        String text1 = parser.getContent();
        String regexp = "[\\(Рис\\.\\s+(\\d+)\\)]";
        String allCharExPoint2 = "[[\\(.+\\)]^!?]*";
        String my =" расположены в <..>4252yijls вершинах (Рис. 9) правильных шестиугольников ?";
        String sentenceRegexp2 = "([А-Я][^<[ис\\.]]"+allCharExPoint2+"[\\.?!])\\s*[А-Я\\<\\&]";
        Pattern p = Pattern.compile(sentenceRegexp2);
        Matcher m= p.matcher(text1);
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
    void parseForSentenceText2(){
        PaperParser parser = new PaperParser();
        assertTrue(parser.initialiseContent());

        String textEx ="В атоме гелия (Рис. 7) силам притяжения между разнополярными зарядами на концах спиновых трубок протона и электрона противодействуют силы отталкивания между однополярными концевыми зарядами в спиновых трубках."+
                " Слабые силы притяжения между элементарными электростатическими зарядами противоположного знака в электроне и протоне способны только удерживать электроны в структуре атома на некотором расстоянии."+
                " Мощные силы отталкивания между однополярными спиновыми зарядами на концах элементарных трубок в электроне и протоне противодействуют бесконечному сближению и поэтому электроны не «падают» на протоны в ядрах атомов. ";

        //System.out.println(parser.getContent());
        System.out.println(textEx);
        String text = parser.getContent();
        String regexp4 ="\\(Рис\\.([и\\s+\\d+\\,+]*)\\)" ;
        String superRegexp = "[^\\(][А-Я][^\\.\\&\\<][^\\!\\?]*[\\.\\!\\?]\\s?[[А-Я]\\&\\<]";
        Pattern p = Pattern.compile(superRegexp);
        Matcher m= p.matcher(text);

        int i =0;
        while (m.find(i)  ) {
            i=m.end()-2;

            System.out.println("\nNew Sentense\n");
            System.out.println(m.group());

        }
    }


}