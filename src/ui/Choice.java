package ui;

public enum Choice {
    Employee,Customer;

    private Choice(){}

    public String value(){
        return name();
    }

    public static Choice fromValue(String v){
        return valueOf(v);
    }
}
