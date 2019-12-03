package com.model.central;

public class LogisticsDepartment {
    boolean orderToDistribute;
    public boolean getDistributionOrder(){
        CentralStorageManager csm = new CentralStorageManager();
        orderToDistribute = csm.isOrderToDistribute();
        return orderToDistribute;
    }
}
