import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.TreeMap;

public class FileOper {
    private static String Output;
    public static TreeMap<String, Human> fromXml(String xml, String output) {
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
            System.out.println("Неверное содержимое файла");
            System.exit(1);
        }
        Output=output;
        System.out.println("Данные загружены");
        return HumansTree;
    }
    public static void XmlSaveFile(TreeMap<String, Human> HumansTree) {
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
            FileOutputStream fileOutputStream = new FileOutputStream(Output);
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            StreamResult result = new StreamResult(output);
            transformer.transform(domSource, result);
            System.out.println("Коллекция успешно сохранена в исходный файл");
            output.flush();
            output.close();
        } catch (IOException e) {
            System.out.println("Проблемы с файлом или нет доступа к файлу, данные не  сохранены");
        } catch (TransformerException e) {
            System.out.println("Запись с ошибкой");
        } catch (ParserConfigurationException e) {
            System.out.println("Тут ничего не сделаешь, так сказать gg wp");
        }
    }
}
