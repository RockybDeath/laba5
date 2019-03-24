public class Skill {
    /*String One=Zero.replaceAll("\\{","");
        String Two=One.replaceAll("}","");
        String[] Tree=Two.split("\"");
        for(String s:Tree) {
            System.out.println(s);
        }*/
    /*if (a!=0) {
            while (a < Zero.length()) {
                if (Zero.charAt(a) == '{') {
                    while (Zero.charAt(a) != '}'&& a<Zero.length()) {
                            if (Zero.charAt(a)=='"'){
                                a++;
                                while(Zero.charAt(a) != '"'&& a<Zero.length()){
                                    Compare=Compare+Zero.charAt(a);
                                    a++;
                                }
                            if ("name".equals(Compare))
                            }
                    }
                }
            }
        }*/
    //System.out.println(Zero);
    private String name;
    private String info;
    private String status;
    public Skill(String _name,String _info, String _status){
        name=_name;
        info=_info;
        status=_status;
    }
    public String getStatus(){
        return status;
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
        Skill guest=(Skill) obj;
        if (obj.hashCode()==this.hashCode())
            return true;
        return guest.status == this.status && guest.name==this.name && guest.info==this.info;
    }
    @Override
    public String toString() {
        return "Object [name=" + name
                + ", info=" + info
                + ", status=" + status
                + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + name.length();
        result = prime*result + status.length();
        result = prime*result + info.length();
        return result;
    }
}
