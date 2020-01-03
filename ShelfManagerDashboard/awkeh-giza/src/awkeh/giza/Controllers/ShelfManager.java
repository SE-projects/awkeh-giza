
package awkeh.giza.Controllers;

public class ShelfManager extends Shelf implements Isle  {
    
    Shelf shelf;
    boolean status;
    Products prod;
    Isle isle;
    @Override
    public void UpdateInfo() {
    }

    @Override
    public Shelf getShelvesinIsle() {
        return shelf;
    }
    
    public void setProductRequest(Products product){
        this.product=prod;
    
    }
    public boolean getApproval(){
      return status;
    }
    public Shelf updateShelf(){
        return shelf;
    }
    public Isle updateIsle(){
        return isle;
    }
    
}
