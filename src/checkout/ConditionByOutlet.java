package checkout;

public class ConditionByOutlet implements Condition {
    Outlet outlet;
    public ConditionByOutlet(Outlet outlet) {
        this.outlet = outlet;
    }

    @Override
    public boolean checkCondition(Check check) {
        if(check.getCostByOutlet(outlet) != 0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public int getCostForCondition(Check check) {
        return check.getCostByOutlet(outlet);
    }
}
