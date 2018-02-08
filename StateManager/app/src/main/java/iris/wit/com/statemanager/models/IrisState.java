package iris.wit.com.statemanager.models;

/**
 * Created by Dean on 08/02/2018.
 */

public class IrisState {

    private String stateName;
    private int stateValue;
    private String stateColour;

    public IrisState(int stateValue, String stateColour){
        this.stateValue = stateValue;
        this.stateColour = stateColour;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public int getStateValue() {
        return stateValue;
    }

    public void setStateValue(int stateValue) {
        this.stateValue = stateValue;
    }

    public String getStateColour() {
        return stateColour;
    }

    public void setStateColour(String stateColour) {
        this.stateColour = stateColour;
    }
}
