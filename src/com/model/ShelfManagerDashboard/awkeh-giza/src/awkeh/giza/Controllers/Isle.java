
package awkeh.giza.Controllers;

import java.util.ArrayList;

public interface Isle {
    
    String name="";
    ArrayList<Shelf> shelves= new ArrayList<Shelf>();
     void UpdateInfo();
     Shelf getShelvesinIsle();
}
