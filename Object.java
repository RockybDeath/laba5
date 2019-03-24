public class Object {
    private String name;
    private String status;
    private int width;
    private int height;
    public String getName(){
        return name==null ? "..." : name;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String _status){
        status=_status;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }
    public Object(String _name, String _status){
        status=_status;
        name=_name;
        System.out.println("Создан - "+name);
    }
    public Object(String _name,String _status, int _width, int _height){
        name=_name;
        width=_width;
        height=_height;
        status=_status;
        System.out.println("Создан - "+name);
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + name.length();
        result = prime*result + status.length();
        result = prime*result + width;
        result = prime*result + height;
        return result;
    }
    @Override
    public boolean equals(java.lang.Object obj) {
        if (obj == null || obj.getClass() != getClass()) { return false; }
        Object guest=(Object) obj;
        if (obj.hashCode()==this.hashCode())
            return true;
        return guest.height == this.height && guest.name==this.name && guest.status==this.status && guest.width==this.width;
    }
    @Override
    public String toString() {
        return "Object [name=" + name
                + ", status=" + status
                + ", width=" + width
                + ", height=" + height + "]";
    }
}
