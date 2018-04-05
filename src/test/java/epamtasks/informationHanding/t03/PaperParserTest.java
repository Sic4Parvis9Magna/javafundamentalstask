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
    void parseSimpleRefTest(){

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
        assertEquals("19",m.group(1));

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
        int count =0;
        while (m.find()){
            count++;
            System.out.println(m.group());
            nums.addAll(parseNumber(m.group(1)));
        }
        System.out.println(nums);
        System.out.printf("number of matches = %d%n",count);
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
    void findStartCharTest(){
        String text2 = " В ядре атома углерода образовались условия начала строительства бериллиевой и углеродной плоскостей нейтрализации свободных неэлектростатических зарядов \n" +
                "(Рис. 9) протонов. В ядре атома кислорода (Рис. Н. И. Чесуваев) завершается строительство бериллиевой плоскости нейрализации, а в ядре атома неона (Рис. 13) \n" +
                "завершается строительство углеродной плоскости нейтрализации неэлектростатических зарядов протонов. <>;Снрзш авыю.";
      String text3 = " Векторами показаны (Рис. 17) \n" +
              "прямые силы притяжения и диагональные силы отталкивания между разноимёнными и одноимёнными спиновыми неэлетростатическими зарядами протонов \n" +
              "и лёгких нейтронов.</div><p><img width=\"397\" vspace=\"5\" height=\"605\" align=\"middle\" src=\"./Новая фундаментальная физика\n" +
              " (Статья А.Н. Ховалкина)_files/pic17.jpg\" alt=\"\"></p><p>&nbsp;</p><p>&nbsp;</p><div>Рис. 17 &nbsp;\n" +
              "На упрощённой схеме шесть атомов углерода в вершинах правильного шестиугольника совместно образуют минимальную,\n" +
              " первичную структуру элементарной молекулы графита – С6.</div><div>&nbsp;</div><div>Минимальная структура из шести\n" +
              " атомов углерода в элементарной молекуле С6 (Рис. 23) обладает химическими свойствами графита и,\n" +
              " становится зародышевым центром плоской раскрытой молекулы графита С54 (Рис. 18) – чешуйки графита.\n" +
              " Из плоских, раскрытых молекул графита С54 рождаются закрытые молекулы графита С60 – фуллерены (Рис. 22).";
       int end = text2.indexOf("13");
       int match = PaperParser.findStartChar(end-1,text2);
        System.out.printf("found at index %d char %s%n",match,text2.charAt(match));
       assertEquals('В',text2.charAt(match));
         match = PaperParser.findStartChar(text2.length()-1,text2);
        System.out.printf("found at index %d char %s%n",match,text2.charAt(match));
        assertEquals('С',text2.charAt(match));
        match = PaperParser.findStartChar(text3.indexOf("23"),text3);
        System.out.printf("found at index %d char %s%n",match,text3.charAt(match));
        assertEquals('М',text3.charAt(match));
        match = PaperParser.findStartChar(text3.indexOf("(Рис. 22)"),text3);
        System.out.printf("found at index %d char %s%n",match,text3.charAt(match));
        assertEquals('И',text3.charAt(match));
    }
    @Test
    void findEndCharTest(){
        String text3 = " Векторами показаны (Рис. 17) \n" +
                "прямые силы притяжения и диагональные силы отталкивания между разноимёнными и одноимёнными спиновыми неэлетростатическими зарядами протонов \n" +
                "и лёгких нейтронов.</div><p><img width=\"397\" vspace=\"5\" height=\"605\" align=\"middle\" src=\"./Новая фундаментальная физика\n" +
                " (Статья А.Н. Ховалкина)_files/pic17.jpg\" alt=\"\"></p><p>&nbsp;</p><p>&nbsp;</p><div>Рис. 17 &nbsp;\n" +
                "На упрощённой схеме шесть атомов углерода в вершинах правильного шестиугольника совместно образуют минимальную,\n" +
                " первичную структуру элементарной молекулы графита – С6.</div><div>&nbsp;</div><div>Минимальная структура из шести\n" +
                " атомов углерода в элементарной молекуле С6 (Рис. 23) обладает химическими свойствами графита и,\n" +
                " становится зародышевым центром плоской раскрытой молекулы графита С54 (Рис. 18) – чешуйки графита.\n" +
                " Из плоских, раскрытых молекул графита С54 рождаются закрытые молекулы графита С60 – фуллерены (Рис. 22).";

        int match  = PaperParser.findEndChar(text3.indexOf("(Рис. 23)"),text3);
        System.out.printf("found at index %d char %s%n",match,text3.charAt(match-1));
        assertEquals('.',text3.charAt(match-1));
         match  = PaperParser.findEndChar(text3.indexOf("рождаются закрытые молекулы графита С60"),text3);
        System.out.printf("found at index %d char %s%n",match,text3.charAt(match));
        assertEquals('.',text3.charAt(match));
        match  = PaperParser.findEndChar(text3.indexOf("первичную структуру элементарной"),text3);
        System.out.printf("found at index %d char %s%n",match,text3.charAt(match-1));
        assertEquals('.',text3.charAt(match-1));

    }

    @Test
    void parseSentenseTest(){
        PaperParser pp = new PaperParser();
        assertTrue(pp.initialiseContent());
       List<String> sentences = pp.parseSentense(pp.getContent());
       assertTrue(!sentences.isEmpty());
        System.out.printf("Sentence counter = %d%n",sentences.size());
        for (String str:sentences) {
            System.out.println("Is a new sent");
            System.out.println(str);
        }
        System.out.printf("Sentence counter = %d%n",sentences.size());
        assertEquals(120,sentences.size());
    }
}