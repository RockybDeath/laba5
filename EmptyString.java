public class EmptyString extends RuntimeException {
    private String EmptyString;
    public EmptyString(String message){
        EmptyString=message;
    }
    public void ErrorString(){
        System.err.println(EmptyString);
    }
}
