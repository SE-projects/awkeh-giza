package ui;

public enum Branch{
    Eastern, Western, Northern, Southern;

    private Branch(){}

    public String value(){
        return name();
    }

    public static Branch fromValue(String b){
        return valueOf(b);
    }
}
