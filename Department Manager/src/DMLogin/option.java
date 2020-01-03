package DMLogin;

public enum option {
    Western ,Eastern ,Northern ,Southern;

    private option(){}

    public String value (){

        return name();
    }

    public static option fromvalue(String v){

        return valueOf(v);
    }

}
