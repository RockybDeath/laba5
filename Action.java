public class Action {
    private String name;
    private String info;
    private int indemood;
    public Action(String _name,String _info,int _indemood){
        indemood=_indemood;
        name=_name;
        info=_info;
    }
    public int getIndemood(){
        return indemood;
    }
    public void setIndemood(int _indemood){
        indemood=_indemood;
    }
    public String getName(){
        return name;
    }
    public String getInfo(){
        if (info==null)
            return "...";
        else return info;
    }
    @Override
    public boolean equals(java.lang.Object obj) {
        if (obj == null || obj.getClass() != getClass()) { return false; }
        Action guest=(Action) obj;
        if (obj.hashCode()==this.hashCode())
            return true;
        return guest.indemood == this.indemood && guest.name==this.name && guest.info==this.info;
    }
    @Override
    public String toString() {
        return "Object [name=" + name
                + ", info=" + info
                + ", indemood=" + indemood
                + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + name.length();
        result = prime*result + indemood;
        result = prime*result + info.length();
        return result;
    }
}
