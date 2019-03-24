import java.util.Collections;
import java.util.Date;
import java.util.TreeMap;

public class TreeCol {
    private static TreeMap<String, Human> HumansTree = new TreeMap<>();
    private static Date date = new Date();
    public static TreeMap<String, Human> get() {
        return HumansTree;
    }
    public static void set(TreeMap<String, Human> Tree) {
        HumansTree=Tree;
    }
    /**
     * <p>Показывает содержимое коллекции</p>
     */
    public static void show(){
        System.out.println(HumansTree.entrySet().toString());
    }
    /**
     * <p>список и описание команд</p>
     */
    public static void help(){
        System.out.println("insert {String key} {element} - команда добавления нового элемента с заданным ключом. " +
                "Параметры key - String(имя человека). " +
                "Параметры element - передавать 5 значений: nickname,  surname, name, info, status. Пример : insert {\"nickname\":\"Red\"}" +
                "{\"nickname\":\"Red\", \"surname\":\"Blue\",\"Skill\":{\"name\":\"Убийство\", \"info\":\"Смертельное повреждение объекту\", \"status\":\"Мертв\"}}");
        System.out.println("show - вывод элементов в нашей коллекции");
        System.out.println("save - сохранить измененную коллекцию в исходный файл");
        System.out.println("info - информация о коллекции, ее тип, дата создания и кол-во элементов");
        System.out.println("remove {String key} - удалить элемент коллекции по его ключу. Пример : remove {\"nickname\":\"Red\"}");
        System.out.println("add_if_min - добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции. Пример: add_if_min {\"nickname\":\"Red\", \"surname\":\"Blue\",\"Skill\":{\"name\":\"Убийство\", \"info\":\"Смертельное повреждение объекту\", \"status\":\"Мертв\"}}");
        System.out.println("add_if_max - добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции. Пример: add_if_max {\"nickname\":\"Red\", \"surname\":\"Blue\",\"Skill\":{\"name\":\"Убийство\", \"info\":\"Смертельное повреждение объекту\", \"status\":\"Мертв\"}}");
    }
    /**
     *<p>Сохраняет коллекцию в файл</p>
     */
    public static void save(){
        FileOper.XmlSaveFile(HumansTree);
    }
    /**
     *<p>Выводит информацию о коллекции</p>
     */
    public static void info(){
        System.out.println("Тип коллекции - TreeMap");
        System.out.println("Дата инициализации - "+date.toString() );
        System.out.println("Количество элементов - "+HumansTree.size());
        System.out.println("Значение объектов коллекции определяется алфавитом");
    }
    /**
     * <p>Удаляет элемент из коллекции по его ключу</p>
     * @param key String
     * @throws NullPointerException
     */
    public static void remove(String key){
        try {
            if (HumansTree.containsKey(key)) {
                HumansTree.remove(key);
                System.out.println("Элемент успешно удален");
            } else {
                System.out.println("Такого элемента нет");
            }
        }
        catch (NullPointerException|StringIndexOutOfBoundsException e){
            System.out.println("Неверно введены данные");
        }
    }
    /**
     * <p>Добавляет элемент в коллекцию</p>
     * @param human Human
     * @throws NullPointerException
     */
    public static void insert(Human human){
        try {
            if (!HumansTree.containsKey(human.getNickname())) {
                HumansTree.put(human.getNickname(), human);
                System.out.println("Элемент успешно добавлен");
            } else {
                System.out.println("Такой ключ уже существует");
            }
        }
        catch (NullPointerException|StringIndexOutOfBoundsException|IllegalArgumentException e){
            System.out.println("Неверный ввод");
        }
    }
    /**
     * <p>Добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции, или же,добавляет новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции</p>
     * @param human Human
     * @param comand String
     * @throws NullPointerException,IllegalArgumentException
     */
    public static void add_if(Human human,String comand){
        try {
            if ("add_if_max".equals(comand)) {
                if (human.compareTo(Collections.max(HumansTree.values())) == 1) {
                    HumansTree.put(human.getNickname(), human);
                    System.out.println("Элемент успешно добавлен");
                }
                else System.out.println("Элемент не добавлен");
            }
            if ("add_if_min".equals(comand)) {
                if (human.compareTo(Collections.min(HumansTree.values())) == -1) {
                    HumansTree.put(human.getNickname(), human);
                    System.out.println("Элемент успешно добавлен");
                }
                else System.out.println("Элемент не добавлен");
            }
        }
        catch(NullPointerException|IllegalArgumentException e){
            System.out.println("Неверный ввод");
        }
    }
}
