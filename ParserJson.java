import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class ParserJson {
    private String Input;
    private int howmany=0;
    private String FirstValue;
    private String SecondValue;
    private String ThirdValue;
    private String FourthValue;
    private String FifthValue;
    private String SixthValue;
    public ParserJson(String Input){
        this.Input=Input;
    }
    public void parser(){
        int stop=0;
        int a=0;
        try{
            if (!Input.contains("{")&& !Input.contains("}")) {
                FirstValue=Input;
            }
            else if (Input.contains("{")) {
                for (int i = 0; i < Input.length(); i++) {
                    if (Input.charAt(i) == '{') {
                        FirstValue = Input.substring(0, i).trim();
                        a=i;
                        break;
                    }
                }
                this.Input=Input.substring(a,getInput().length());
                if ("remove".equals(FirstValue)&&!this.Input.contains("}")){
                    while (!this.Input.contains("}")) {
                        Scanner Input1 = new Scanner(System.in);
                        this.Input=getInput()+Input1.nextLine();
                        stop+=1;
                        if (stop==400) break;
                    }
                    this.Input=getInput().substring(1,this.Input.length());
                } else if("add_if_max".equals(FirstValue)||"add_if_min".equals(FirstValue)){
                    while(!((this.Input.indexOf("{")<this.Input.lastIndexOf("}"))&&((this.Input.indexOf("{",this.Input.indexOf("{")+1))<(this.Input.substring(this.Input.indexOf("{")+1,this.Input.lastIndexOf("}")).lastIndexOf("}"))))){
                        Scanner Input1=new Scanner(System.in);
                        this.Input=getInput()+Input1.nextLine();
                        stop+=1;
                        if (stop==400) break;
                    }
                    this.Input=getInput().substring(1,this.Input.length());
                }
                else if("insert".equals(FirstValue)){
                    while(!((this.Input.indexOf("{")<this.Input.indexOf("}"))
                            &&(this.Input.indexOf("{",this.Input.indexOf("}")+1)<this.Input.lastIndexOf("}"))
                            &&(this.Input.lastIndexOf("}")!=this.Input.indexOf("}",this.Input.indexOf("{",this.Input.indexOf("}"))+1))
                            &&(this.Input.indexOf("}")!=this.Input.lastIndexOf("}"))
                            &&(this.Input.indexOf("{",this.Input.indexOf("{",this.Input.indexOf("}"))+1)<this.Input.indexOf("}",this.Input.indexOf("{",this.Input.indexOf("}"))+1)))){
                        Scanner Input1=new Scanner(System.in);
                        this.Input=getInput()+Input1.nextLine();
                        stop+=1;
                        if (stop==400) {
                            System.out.println("Вы надоели программе");
                            break;
                        }
                    }
                    this.Input=getInput().substring(1,this.Input.length());
                }
                else {
                    this.Input=Input.substring(1,getInput().length());
                }
            }}
        catch (NoSuchElementException e){
            FileOper.XmlSaveFile(TreeCol.get());
            System.exit(1);
        }
        catch (NullPointerException|StringIndexOutOfBoundsException e){
            System.out.println("Неверные данные2");
            refresh();
        }
    }
    public String getInput(){
        return this.Input;
    }

    /**
     * <p>Из строки берет значения определенных json полей и присваивает переменным. Возвращает строку, которая осталась после преобразований</p>
     * @param parametr1 String
     * @return String
     * @throws NullPointerException
     * @throws StringIndexOutOfBoundsException
     */
    public String StringKey(String parametr1)
            throws NullPointerException, StringIndexOutOfBoundsException
    {
        if (parametr1.charAt(parametr1.length() - 1) != '}') {
            throw new NullPointerException();
        }
        String Compare = null;int a = parametr1.length() + 1;
        for (int l = 0; l < parametr1.length(); l++) {
            if (parametr1.charAt(l) == '}')
            {
                Compare = parametr1.substring(0, l);
                a = l + 1;
                break;
            }
        }
        parametr1 = parametr1.substring(a, parametr1.length());
        //if(!(Compare.contains("nickname")||Compare.contains("surname")||Compare.contains("info")||Compare.contains("status")||Compare.contains("name"))) throw new NullPointerException();
        String[] Compare1 = Compare.split(":");
        for (int i = 0; i < Compare1.length; i++) {
            Compare1[i] = Compare1[i].trim();
        }
        if (Compare1.length == 2)
        {
            if (("\"nickname\"".equals(Compare1[0])) && (Compare1[1] != null) && (Compare1[1].charAt(0) == '"') && (Compare1[1].charAt(Compare1[1].length() - 1) == '"'))
            {
                this.howmany += 1;
                if (this.SecondValue == null) {
                    this.SecondValue = Compare1[1].replaceAll("\"", "");
                } else if ((Compare1[1].replaceAll("\"", "").equals(this.SecondValue)) && (this.howmany < 3)) {
                    this.SecondValue = Compare1[1].replaceAll("\"", "");
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (("\"surname\"".equals(Compare1[0])) && (Compare1[1] != null) && (Compare1[1].charAt(0) == '"') && (Compare1[1].charAt(Compare1[1].length() - 1) == '"')) {
                if (this.ThirdValue == null) {
                    this.ThirdValue = Compare1[1].replaceAll("\"", "");
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (("\"name\"".equals(Compare1[0])) && (Compare1[1] != null) && (Compare1[1].charAt(0) == '"') && (Compare1[1].charAt(Compare1[1].length() - 1) == '"')) {
                if (this.FourthValue == null) {
                    this.FourthValue = Compare1[1].replaceAll("\"", "");
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (("\"info\"".equals(Compare1[0])) && (Compare1[1] != null) && (Compare1[1].charAt(0) == '"') && (Compare1[1].charAt(Compare1[1].length() - 1) == '"')) {
                if (this.FifthValue == null) {
                    this.FifthValue = Compare1[1].replaceAll("\"", "");
                } else {
                    throw new IllegalArgumentException();
                }
            }
            if (("\"status\"".equals(Compare1[0])) && (Compare1[1] != null) && (Compare1[1].charAt(0) == '"') && (Compare1[1].charAt(Compare1[1].length() - 1) == '"')) {
                if (this.SixthValue == null) {
                    this.SixthValue = Compare1[1].replaceAll("\"", "");
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } else throw new IllegalArgumentException();
        return parametr1;
    }
    /**
     * <p>Разделяет строку по запятым</p>
     * @param SK String
     * @throws NullPointerException
     */
    public void SKtogether(String SK) throws NullPointerException{
        if (!"".equals(SK)) {
            if (SK.contains(",")) {
                String[] compare1 = SK.split(",");
                int i=0;
                if (SK.charAt(0)==',') i++;
                while(i<compare1.length){
                    StringKey(compare1[i]+"}");
                    i++;
                }
                /*for (int c=i; c<compare1.length;c++){
                    StringKey(compare1[c]+"}");
                }*/
            } else {
                throw new NullPointerException();
            }
        }
    }

    /**
     * <p>Обновляет значение переменных</p>
     */
    public void refresh(){
        FirstValue = null;
        SecondValue = null;
        ThirdValue = null;
        FourthValue = null;
        FifthValue = null;
        SixthValue = null;
        howmany=0;
    }

    /**
     * <p>Разбивает строку на составляющие полей json(внутренние объекты json и сами поля объекта json.</p>
     * @param parametr2 String
     * @throws NullPointerException
     * @throws IllegalArgumentException
     */
    public void element(String parametr2)
            throws NullPointerException, IllegalArgumentException
    {
        parametr2 = parametr2.trim();
        if ((parametr2.charAt(0) != '{') && (parametr2.charAt(parametr2.length() - 1) != '}')) {
            throw new NullPointerException();
        }
        if ((!parametr2.contains("\"Skill\"")) || (!parametr2.contains("\"nickname\"")) || (!parametr2.contains("\"surname\"")) || (!parametr2.contains("\"name\"")) || (!parametr2.contains("\"info\"")) || (!parametr2.contains("\"status\""))) {
            throw new NullPointerException();
        }
        int a1 = parametr2.indexOf("\"Skill\"");
        String first = parametr2.substring(1, a1).trim();
        if (!"".equals(first)&&first.charAt(0)==',') throw new IllegalArgumentException();
        SKtogether(first);
        int a2 = parametr2.indexOf("{", a1) + 1;
        String search = parametr2.substring(a1, a2 - 1).trim();
        if ((!search.contains("\"Skill\"")) || (!search.contains(":")) || (search.indexOf("\"Skill\"") >= search.indexOf(":"))) {
            throw new IllegalArgumentException();
        }
        int a3 = parametr2.indexOf("}", a2) + 1;
        String third = parametr2.substring(a2, a3-1).trim();
        if (!"".equals(third)&&(third.charAt(third.length()-1)==','||third.charAt(0)==',')) throw new IllegalArgumentException();
        SKtogether(third);
        int a4 = parametr2.indexOf("}", a3);
        String second = parametr2.substring(a3, a4).trim();
        if (!"".equals(second)&&second.charAt(second.length()-1)==',') throw new IllegalArgumentException();
        SKtogether(second);
    }
    /**
     * <p>Метод, который при определенной значении команды-вызывает определенную команду.</p>
     * @throws NullPointerException
     */
    public void work()throws NullPointerException{
        try {
            if ("show".equals(FirstValue)) {
                TreeCol.show();
            } else if ("info".equals(FirstValue)) {
                TreeCol.info();
            } else if ("remove".equals(FirstValue)) {
                StringKey(this.Input);
                TreeCol.remove(SecondValue);
            } else if ("save".equals(FirstValue)){
                TreeCol.save();
            }
            else if ("help".equals(FirstValue)){
                TreeCol.help();
            }
            else if ("insert".equals(FirstValue)){
                element(StringKey(this.Input));
                if (howmany==2) TreeCol.insert(new Human(SecondValue, ThirdValue, new Skill(FourthValue, FifthValue, SixthValue)));
                else System.out.println("Неверные данные");
            }
            else if ("exit".equals(FirstValue)){
                FileOper.XmlSaveFile(TreeCol.get());
                System.exit(0);
            }
            else if ("add_if_max".equals(FirstValue)||"add_if_min".equals(FirstValue)){
                element("{"+Input);
                TreeCol.add_if(new Human(SecondValue, ThirdValue, new Skill(FourthValue, FifthValue, SixthValue)),FirstValue);
            }
            else System.out.println("Неверные входные данные, откройте справку командой help");
        }
        catch(NullPointerException|StringIndexOutOfBoundsException|IllegalArgumentException e){
            System.out.println("Неверно введены данные");
        }
        finally {
            refresh();
        }
    }
}
