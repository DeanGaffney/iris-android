package iris.wit.com.statemanager.utils;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import iris.wit.com.statemanager.models.IrisState;
import iris.wit.com.statemanager.models.IrisStateManager;

/**
 * Created by Dean on 08/02/2018.
 */

public class IrisStateManagerUtils {

    /**
     * Gets a list of IrisStateManagers to use for a grid view
     * @return a list of iris state managers
     */
    public static List<IrisStateManager> getIrisStateManagers(){
        return Arrays.asList(
            createIrisStateManager("State1"),
            createIrisStateManager("State2"),
            createIrisStateManager("State3"),
            createIrisStateManager("State4"),
            createIrisStateManager("State5"),
            createIrisStateManager("State6"));
    }

    private static IrisStateManager createIrisStateManager(String stateName){
        return new IrisStateManager(stateName, Arrays.asList(
                                                            new IrisState(1, "#ff0000"),
                                                            new IrisState(2, "#3399ff"),
                                                            new IrisState(3, "#33cc33")
                                                        ));
    }

}
