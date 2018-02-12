package iris.wit.com.statemanager;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
        for()
    }
}
