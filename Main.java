import org.w3c.dom.*;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.crypto.Data;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    //private static TreeMap<String, Human> HumansTree = new TreeMap<>();

    /*private static TreeMap<String, Human> fromXml(String xml) {
        TreeMap<String, Human> HumansTree = new TreeMap<String, Human>();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            //Document doc=documentBuilder.parse(new BufferedInputStream(new FileInputStream("C:\\Users\\Киря\\IdeaProjects\\World\\src\\HumansTree.xml")));
            Document doc = documentBuilder.parse(new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8)));
            NodeList ListofHumans = doc.getElementsByTagName("Human");
            for (int i = 0; i < ListofHumans.getLength(); i++) {
                Element human = (Element) ListofHumans.item(i);
                NodeList ListNickNames = human.getElementsByTagName("nickname");
                NodeList ListSurname = human.getElementsByTagName("surname");
                NodeList ListSkill = human.getElementsByTagName("skill");
                Element skill = (Element) ListSkill.item(0);
                NodeList ListNames = skill.getElementsByTagName("name");
                NodeList ListInfous = skill.getElementsByTagName("info");
                NodeList ListStatuses = skill.getElementsByTagName("status");
                HumansTree.put(ListNickNames.item(0).getFirstChild().getNodeValue(),
                        new Human(ListNickNames.item(0).getFirstChild().getNodeValue(), ListSurname.item(0).getFirstChild().getNodeValue(),
                                new Skill(ListNames.item(0).getFirstChild().getNodeValue(), ListInfous.item(0).getFirstChild().getNodeValue(), ListStatuses.item(0).getFirstChild().getNodeValue())));
            }
        } catch (ParserConfigurationException e) {
            System.out.println("Ну тут как бы да");
            System.exit(1);
        } catch (SAXException | IOException l) {
            System.out.println("Можно не надо");
            System.exit(1);
        }
        System.out.println("Данные загружены");
        return HumansTree;
    }

    public static void XmlSaveFile(TreeMap<String, Human> HumansTree, String Myfile) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element My = document.createElement("HumansTree");
            document.appendChild(My);
            for (Human human : HumansTree.values()) {
                Element Creature = document.createElement("Human");
                Element NickName = document.createElement("nickname");
                NickName.appendChild(document.createTextNode(human.getNickname()));
                Creature.appendChild(NickName);
                Element Surname = document.createElement("surname");
                Surname.appendChild(document.createTextNode(human.getSurName()));
                Creature.appendChild(Surname);
                Element Skill = document.createElement("skill");
                Element Skillname = document.createElement("name");
                Skillname.appendChild(document.createTextNode(human.getSkill().getName()));
                Element Skillinfo = document.createElement("info");
                Skillinfo.appendChild(document.createTextNode(human.getSkill().getInfo()));
                Element Skillstatus = document.createElement("status");
                Skillstatus.appendChild(document.createTextNode(human.getSkill().getStatus()));
                Skill.appendChild(Skillname);
                Skill.appendChild(Skillinfo);
                Skill.appendChild(Skillstatus);
                Creature.appendChild(Skill);
                My.appendChild(Creature);
            }
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            DOMSource domSource = new DOMSource(document);
            FileOutputStream fileOutputStream = new FileOutputStream(Myfile);
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            StreamResult result = new StreamResult(output);
            transformer.transform(domSource, result);
            output.flush();
            output.close();
        } catch (IOException e) {
            System.out.println("Проблемы с файлом");
        } catch (TransformerException e) {
            System.out.println("Запись с ошибкой");
        } catch (ParserConfigurationException e) {
            System.out.println("Тут ничего не сделаешь, так сказать gg wp");
        }
    }

    public static TreeMap<String, Human> give() {
        return HumansTree;
    }*/
    public static void main(String[] args) {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                FileOper.XmlSaveFile(TreeCol.get());
            } catch (Exception e) {
                System.err.println("Данные работы программы не сохранены");
            }
        }));
        String s = "";
        try {
            if (args.length > 1 | args.length == 0) {
                System.out.println("Нужно ввести название одного файла");
                System.exit(1);
            } else {
            //FileInputStream Try = new FileInputStream("C:\\Users\\Киря\\IdeaProjects\\World\\src\\HumansTree.xml");
            FileInputStream Try = new FileInputStream(args[0]);
            BufferedInputStream MyTree = new BufferedInputStream(Try);
            if (MyTree.available() > 0) {
                byte[] b = new byte[MyTree.available()];
                MyTree.read(b);
                String XmlTree = new String(b);
                TreeCol.set(FileOper.fromXml(XmlTree,args[0]));
                //TreeCol.set(FileOper.fromXml(XmlTree,"C:\\Users\\Киря\\IdeaProjects\\World\\src\\HumansTree.xml"));
            } else System.out.println("Документ пуст");
        }
        } catch (FileNotFoundException e) {
            System.out.println("По указанному пути нет файла или нет прав доступа к нему");
            System.exit(1);
        } catch (IOException e) {
            System.out.println("Файл пуст или нет прав для доступа к файлу");
            System.exit(1);
        }
        System.out.print("Enter your command, please without bugs: ");
        try {
            while (!"exit".equals(s)) {
                Scanner Input = new Scanner(System.in);
                s = Input.nextLine();
                s = s.trim();
                //ParserJson MyString = new ParserJson(s, HumansTree, date, args[0]);
                ParserJson MyString = new ParserJson(s);
                MyString.parser();
                MyString.work();
            }
        } catch (NoSuchElementException e) {
            //XmlSaveFile(HumansTree, args[0]);
            FileOper.XmlSaveFile(TreeCol.get());
            System.exit(1);
        }
    }
    /*EnvironmentalPhenomenon rain = new EnvironmentalPhenomenon("Дождь");
        rain.addAction(new Action("Капать", "Увлажняет объекты", -1));
        rain.addAction(new Action("Шелестеть по крыше", "Издает звук удара капли о крышу", -2));
        EnvironmentalPhenomenon silence = new EnvironmentalPhenomenon("Тишина");
        rain.doAction(person1);
        skills1.add(new Skill("Зажигать трубку", "", "Зажжена"));
        baby1.setSkills(skills1);
        baby2.setSkills(skills1);
        Object pipe = new Object("Трубка", "Не зажжена");
        baby1.activateSkill(pipe, "Зажигать трубку");
        person1.addSkill(new Skill("Открыть дверь", "", "Открыта"));
        person1.addSkill(new Skill("Забарабанить о дверь", "", "Сломана"));
        Object door = new Object("Дверь", "Закрыта", 60, 220);
        Object flowers = new Object("Цветы", "Увядшие");
        Object clock = new Object("Часы", "Не работают");
        Object beans = new Object("Бобы Фильфьонки", "Нескушанные");
        person1.activateSkill(door, "Открыть дверь");
        person1.addSkill(new Skill("Войти в дверь", "Вошел в домик", "Открыта"));
        person1.Move(door, "Войти в дверь");
        EnvironmentalPhenomenon thunder=new EnvironmentalPhenomenon("");
        System.out.println(thunder.getName());
        baby1.addSkill(new Skill("Сжечь", "Burn them all", "Сожжена"));
        class Surface {
                private String name;
                public Surface(String name){
                    this.name=name;
                    System.out.println("Теперь вот такая поверхность "+name);
                }
                public void setSurface(String name) {
                    this.name=name;
                }
                public void getSurface(){
                    System.out.println(this.name);
                }
                public void changeheight(Human human,int height) {
                    human.setHeight(human.getHeight() - height);
                }
        }
        Surface Earth=new Surface("Земляная");
        Earth.changeheight(baby1,16);
        Human m = new Human ("Санта", 666) {
            public void Smthsaying(String present, Human human){
                System.out.println("Санта дарит "+present+" "+human.getSurName());
                human.changeMood(666);
            }
        };
        m.Smthsaying("Куклу", person2);
        Human.Query query=new Human.Query();
        query.Checkskill("Быть человеком", person2);
        Human.Powder powder=baby1.new Powder("Черный","Дымный");
        powder.pour(person1);
        String a="b";
        String c=m.getClass().getName();
        System.out.println(c);
        printHuman(person1,Human::Check);
        try {
            if (Human.class.getDeclaredMethod("getMood").getAnnotations()==null){
                System.out.println("wdwd");
            }
            System.out.println(Human.class.getDeclaredMethod("changeMood", int.class).getAnnotation(Override.class));
            System.out.println(Human.class.getDeclaredMethod("getMood").getAnnotations());
            System.out.println(Human.class.getAnnotation(Rara.class));
        }catch(NoSuchMethodException e){

        }
        */
    //printHuman(person1,human -> person1.getHeight()>110);
    //sun.reflect.annotation.AnnotatedTypeFactory$AnnotatedTypeBaseImpl@3d075dc0
        /*String a="b";
        String b=new String("b");
        String c="b";
        if (a==new String("b")) System.out.println("adwd");
        if (a==b) System.out.println("dw");String result = "";
        result += " powerfulCode ".trim() == "powerfulCode" ? "0" : "1";
        System.out.println(result);*/
        /*Cat Troya=new Cat();
        Class cl=Troya.getClass();
        Rara properties=Troya.getClass().getAnnotation(Rara.class);
        System.out.println(properties.massa());*/
       /* final Integer a=new Integer(5);
        try {
            Field field = a.getClass().getDeclaredField("SIZE");
            field.setAccessible(true);
            field.set(a,676);
        }catch (NoSuchFieldException|IllegalAccessException e){
        }
        System.out.println(a);*/
        /*Pets a=new Pets(2);
        System.out.println(a.geti());
        try {
            Field field = a.getClass().getDeclaredField("i");
            field.setAccessible(true);
            field.set(a,6);
        }catch (NoSuchFieldException|IllegalAccessException e){
            e.printStackTrace();
        }
        System.out.println(a.geti());*/
    /*}
    static void printHuman(Human h,Checker c){
        if (c.check(h)){
            System.out.println("Hi");
        }*/
}