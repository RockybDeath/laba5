public class WrongSkill extends Exception{
    private String WrongString;
    static public int c;
    public WrongSkill(String badstring){
        WrongString=badstring;
        c=c+1;
        System.out.println("Exception WrongSkill");
    }
    public void MyMessageForMyException(){
        System.err.println("This is exception message for skill "+WrongString);
    }
    public int getc(){
        return c;
    }
}
