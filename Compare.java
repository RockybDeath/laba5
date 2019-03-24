import java.util.Comparator;

public class Compare implements Comparator<Human> {
    @Override
    public int compare(Human a, Human b){
        if (a.hashCode()-b.hashCode()<0) return -1;
        else if (a.hashCode()-b.hashCode()==0) return 0;
        return 1;
    }
}
