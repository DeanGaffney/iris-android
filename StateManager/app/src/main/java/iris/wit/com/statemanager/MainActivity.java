package iris.wit.com.statemanager;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import iris.wit.com.statemanager.models.IrisState;
import iris.wit.com.statemanager.models.IrisStateManager;
import iris.wit.com.statemanager.utils.IrisStateManagerUtils;


public class MainActivity extends AppCompatActivity {

    private List<IrisStateManager> stateManagers;
    private LinearLayout mainLayout;
    private RequestQueue queue;
    private String agentAddress;
    private static final String JWT = "eyJhbGciOiJIUzI1NiJ9.eyJwcmluY2lwYWwiOiJINHNJQUFBQUFBQUFBSlZTdjBcL2JRQlIrRG9rb1FpcFFpVW9NZENuZGtDUEJtS21ndEJLeVFrV2FoVXF0THZiREhKenZ6TjBaa2dWbGdpRURxQzFTSlZaR1wvaE5ZK2dkVVpXQmw3dHAzaHVEQWdyakpmdmY1K1wvVjhmZ01WbzJFeDFvd0w0NmNpaTduMFRhcTVqQTJHbWVhMjYyY0dkWVEyUjN6TWdTMmF3TzN4U3VBRlVPS1JoVmZCRnR0bFZjRmtYRjF0YjJGb2F4ME5DMHJIZDR3Ym1pVzRwXC9TMmY4OGRLbzBQQkFwcTc2d0VvK3N3eGNKUVpkSTJsS3gzVXE0eFdvZkpZaGFvY051TnBrTzZRV2s1RTJZWU9vcVN0UVZHQVl5enpHNHFVdVZvTEV6Y21zMHNGOVVtMmxvQUwxSm1ETGw3bEtScG5YVjM3MnhLU3JBRCsxRHVwQjRkNnU2ZGdcL3FPeDE5V1FsQnFycVNaYThsRVJYeURPM0hpNzgxK1wvMzEwMm11VkFLaVQrYWVcL0tlWXpTOUM3K1BydlRWNjBGMXA0UFdTOWdOVTZLYm1aS3BnXC9hM1RLZjM1OStuRnljXC9obGhKUWQ0c1B6OXpIM1wvcTY1N3JKS1VxYVpWVU03SXRxOXNuc204cVdueVFkYjZQcE5ucVFDNlkrU0ZxTjdpWUtZNHBhMUVvTytMWXl0clFiMWI2MW1mYzI5VlZpVWNFbWlMXC9QTWJsbCtvR2hWXC9ldmp5Nk8zZjRsZ0JTcTdUR1JJbFU4V29FYVd0RkVmbkpcL01qdis4NnVjQkJqXC96ZjFtZmFkb1FBd0FBIiwic3ViIjoiYWRtaW4iLCJyb2xlcyI6WyJST0xFX1VTRVIiXSwiZXhwIjoxNTIwNDI2NDM4LCJpYXQiOjE1MjA0MjI4Mzh9.D-Ky6RgRQZrbfd4I5W642cV2ymJXwlWSIUG-rVWBo3o";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        stateManagers = IrisStateManagerUtils.getIrisStateManagers();
        mainLayout = findViewById(R.id.mainlayout);
        queue = Volley.newRequestQueue(this);
        setAgentUrl();
        setDefaultButtonStates(mainLayout);
        setOnClickListeners(mainLayout);
    }

    private void setDefaultButtonStates(LinearLayout mainLayout) {
        for (int i = 0; i < mainLayout.getChildCount(); i++) {
            LinearLayout childLayout = (LinearLayout) mainLayout.getChildAt(i);
            for (int j = 0; j < childLayout.getChildCount(); j++) {
                final Button button = (Button) childLayout.getChildAt(j);
                button.setText("Hold to set State!");
            }
        }
    }

    private void setOnClickListeners(LinearLayout mainLayout) {
        for(int i = 0; i < mainLayout.getChildCount(); i++){
            LinearLayout childLayout = (LinearLayout)mainLayout.getChildAt(i);
            for(int j = 0; j < childLayout.getChildCount(); j++){
                final Button button = (Button)childLayout.getChildAt(j);
                final int stateManagerIndex = i * childLayout.getChildCount() + j;

                button.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        IrisStateManager manager = stateManagers.get(stateManagerIndex);
                        IrisState state = manager.moveToNextState();
                        button.setBackgroundColor(Color.parseColor(state.getStateColour()));
                        button.setText(manager.getStateName() + "\n" + "Current State: " + state.getStateValue());
                        return true;
                    }
                });

                button.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){
                        sendJson(view);
                    }
                });
            }
        }
    }

    private void sendJson(final View view){
        try{
            JSONObject outgoingJson = IrisStateManagerUtils.toJson(stateManagers);
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.POST, agentAddress, outgoingJson,
                    new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(view.getContext(), "Successful Server Response", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(view.getContext(), "Error Received from Server", Toast.LENGTH_SHORT).show();
                    }
                }){
                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("X-Auth-Token", JWT);
                    return params;
                }
            };

            queue.add(jsObjRequest);
        }catch(JSONException e){
            Toast.makeText(view.getContext(), "JSON Exception", Toast.LENGTH_SHORT).show();
        }
    }

    private void setAgentUrl() {
        String agentAddress = "http://ec2-52-16-53-220.eu-west-1.compute.amazonaws.com:8080/iris/schema/getAgentUrl";

        try {
            JSONObject outgoingJson = new JSONObject().put("name", "android_agent");
            JsonObjectRequest jsObjRequest = new JsonObjectRequest
                    (Request.Method.POST, agentAddress, outgoingJson, new Response.Listener<JSONObject>() {

                        @Override
                        public void onResponse(JSONObject response) {
                            setAgentUrl(response);
                        }
                    }, new Response.ErrorListener() {

                        @Override
                        public void onErrorResponse(VolleyError error) {
                        }
                    }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String>  params = new HashMap<String, String>();
                        params.put("X-Auth-Token", JWT);
                        return params;
                    }
                };
            queue.add(jsObjRequest);
        } catch (JSONException e) {
        }
    }

    private void setAgentUrl(JSONObject response) {
        try{
            this.agentAddress = response.getString("url");
        }catch (JSONException e){}
    }
}
