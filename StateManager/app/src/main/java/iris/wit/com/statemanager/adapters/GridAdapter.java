package iris.wit.com.statemanager.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import iris.wit.com.statemanager.models.IrisState;
import iris.wit.com.statemanager.models.IrisStateManager;
import iris.wit.com.statemanager.utils.IrisStateManagerUtils;

/**
 * Created by Dean on 08/02/2018.
 */

public class GridAdapter extends BaseAdapter {

    private Context context;
    private IrisStateManager[] states;

    public GridAdapter(Context context){
        this.context = context;
        this.states = IrisStateManagerUtils.getIrisStateManagers();
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }
}
