package com.geekhub.javadevelopers;

        import android.app.ProgressDialog;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.LinearLayoutManager;
        import android.support.v7.widget.RecyclerView;
        import android.widget.Toast;


        import com.android.volley.Request;
        import com.android.volley.RequestQueue;
        import com.android.volley.Response;
        import com.android.volley.VolleyError;
        import com.android.volley.toolbox.StringRequest;
        import com.android.volley.toolbox.Volley;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.util.ArrayList;
        import java.util.List;

/**
 * Created by mo.yosiwealth on 8/23/2017.
 */

public class MainActivity extends AppCompatActivity {

    private static final String URL_DATA = "https://api.github.com/search/users?q=location:lagos+language:java";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<eachProfileView> profiles;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_list);


        recyclerView = (RecyclerView) findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        profiles = new ArrayList<>();
        loadRecyclerViewData();
    }


    private void loadRecyclerViewData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading Java Devs From Lagos ...");
        progressDialog.show();


        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                URL_DATA,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        progressDialog.dismiss();

                        try {
                            JSONObject jsonObject = new JSONObject(s);
                            JSONArray array = jsonObject.getJSONArray("items");   //add array name

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                eachProfileView item = new eachProfileView(
                                        o.getString("avatar_url"),
                                        o.getString("login"),
                                        o.getString("repos_url"),
                                        o.getString("html_url")
                                );
                                profiles.add(item);
                            }

                            adapter = new ProfileAdapter(profiles, getApplicationContext());
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "Connect to the internet", Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
