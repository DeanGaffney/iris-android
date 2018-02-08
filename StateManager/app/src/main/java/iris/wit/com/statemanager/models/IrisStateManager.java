package iris.wit.com.statemanager.models;

import java.util.List;

/**
 * Created by Dean on 08/02/2018.
 */

public class IrisStateManager {

    private String stateName;
    private List<IrisState> states;
    private IrisState currentState;
    private int currentStateIndex;

    public IrisStateManager(String stateName, List<IrisState> states){
        this.stateName = stateName;
        this.states = states;
        this.currentStateIndex = 0;
        this.currentState = this.states.get(this.currentStateIndex);
        setStatesNames();
    }

    public List<IrisState> getStates() {
        return states;
    }

    public void setStates(List<IrisState> states) {
        this.states = states;
    }

    public IrisState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(IrisState currentState) {
        this.currentState = currentState;
    }

    public void setCurrentState(int index){
        this.currentState = getStates().get(index);
    }

    public int getCurrentStateIndex() {
        return currentStateIndex;
    }

    public void setCurrentStateIndex(int currentStateIndex) {
        this.currentStateIndex = currentStateIndex;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     *  Changes the currentState to the next available state in the states list
     *  and sets the currentStateIndex to the new index associated with this state
     */
    public void moveToNextState(){
        setCurrentStateIndex(getCurrentStateIndex() % getStates().size());
        setCurrentState(getStates().get(getCurrentStateIndex()));
    }

    /**
     * Sets the names for all states to be the same name
     */
    private void setStatesNames(){
        for(IrisState state : getStates()){
            state.setStateName(getStateName());
        }
    }
}
