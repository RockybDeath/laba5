import java.util.ArrayList;

public class EnvironmentalPhenomenon implements PhenomenonFeature {
    private String name;
    private ArrayList<Action> actions=new ArrayList<>();
    public boolean addAction (Action action){
        if (actions.add(action)){
            System.out.println("Окружающему явлению - "+this.name+" успешно присвоено действие "+action.getName()+" "+action.getInfo());
            return true;
        }
        else {
            System.out.println("При добавлении произошла ошибка");
            return false;
        }
    }
    public void setName(String _name){
        try{
            name=_name;
            if (name.equals("")) throw new EmptyString("Введена пустая строка");
            }
        catch (EmptyString f){
            name="Неизвестное";
            f.ErrorString();
        }
    }
    public String getName(){
        return name;
    }
    public ArrayList<Action> getActions() {
        return actions;
    }
    public EnvironmentalPhenomenon(String _name){
        try {
            if (("".equals(_name))) throw new EmptyString("Введена неккоректная строка");
            else {
                name = _name;
                System.out.println("Добавлено окружающее явление - " + name);
            }
        }
        catch (EmptyString f){
            name="Неизвестное";
            f.ErrorString();
        }
    }
    public EnvironmentalPhenomenon (String _name, ArrayList<Action> _actions){
        name=_name;
        actions=_actions;
        System.out.println("Добавлено окружающее явление - "+_name);
    }
    public void setActions(ArrayList<Action> actions){
        this.actions=actions;
    }
    /*@Override
    public void doAction(Human human){
        for (int i=0; i<getActions().size();i++){
        human.changeMood(getActions().get(i).getIndemood());}
        System.out.println("Текущее настроение "+human.GetName()+" - "+human.getMood());
    }*/
    @Override
    public boolean equals(java.lang.Object obj) {
        if (obj == null || obj.getClass() != getClass()) { return false; }
        EnvironmentalPhenomenon guest=(EnvironmentalPhenomenon) obj;
        if (obj.hashCode()==this.hashCode())
            return true;
        return guest.name == this.name && guest.actions==this.actions;
    }
    @Override
    public String toString() {
        return "Object [name=" + name
                + ", actions=" + actions
                +  "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + name.length();
        result = prime*result + actions.size();
        return result;
    }
}
