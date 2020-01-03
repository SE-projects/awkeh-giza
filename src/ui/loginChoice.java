package ui;

public enum loginChoice {
    customer, storageManager, purchaser, centralManager, generalManager, shelfManager;
    private loginChoice(){}
    public String value(){
        return name();
    }
    public static loginChoice fromValue(String a){
        return valueOf(a);
    }
}
