
package awkeh.giza.Controllers;

import java.util.ArrayList;

public class Shelf {
    
    int number=0;
    String shelfno,direction;
    Products product;
    ArrayList<Products> product1st=new ArrayList<Products>();
    Shelf(){}
    
    Shelf(Products product){
        this.product=product;
    }
     Shelf(String shelfno, String direction){
        this.shelfno=shelfno;
        this.direction=direction;
    }
    
    public  ArrayList<Products> addProductToShelf(Products product){
        product1st.add(product);
        return product1st;
    }
     public  ArrayList<Products> removeProductfromShelf(Products product){
        int index=product1st.indexOf(product);
        product1st.remove(index);
        return product1st;
    
    }
    public String updataProductShelfInfo(){
        return shelfno + "/" +direction ;
    }
   public String getShelfno() {
        return shelfno;
    }

    public void setShelfno(String shelfno) {
        this.shelfno = shelfno;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }
}
    
    

