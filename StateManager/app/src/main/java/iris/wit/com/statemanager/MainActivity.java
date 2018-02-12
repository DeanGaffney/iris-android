package iris.wit.com.statemanager;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.List;

import iris.wit.com.statemanager.models.IrisStateManager;
import iris.wit.com.statemanager.utils.IrisStateManagerUtils;

public class MainActivity extends AppCompatActivity {

    private List<IrisStateManager> stateManagers;
    private LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        stateManagers = IrisStateManagerUtils.getIrisStateManagers();
        mainLayout = findViewById(R.id.mainlayout);
        setOnClickListeners(mainLayout);

    }

    private void setOnClickListeners(LinearLayout mainLayout) {
        for(int i = 0; i < mainLayout.getChildCount(); i++){
            LinearLayout childLayout = (LinearLayout)mainLayout.getChildAt(i);
            for(int j = 0; j < childLayout.getChildCount(); j++){
                final Button button = (Button)childLayout.getChildAt(j);
                final int stateManagerIndex = i;
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        IrisStateManager manager = stateManagers.get(stateManagerIndex);
                        manager.moveToNextState();
                        button.setBackgroundColor(Color.parseColor(manager.getCurrentState().getStateColour()));
                    }
                });
            }
        }
    }
}
