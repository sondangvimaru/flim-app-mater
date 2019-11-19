package com.cnt57cl.linhhuong;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.roger.catloadinglibrary.CatLoadingView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
public class MainActivity extends AppCompatActivity {

    ArrayList<phim> dsphim;
    ListView list_phim;
    CatLoadingView huong_cat;
    AutoCompleteTextView searchview;
    RelativeLayout layout_main;
    adapter a;
    adapter_search adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public  void init()
    {

       list_phim= findViewById(R.id.list_phim);
       layout_main= findViewById(R.id.layout_main);
       layout_main.requestFocus();
            dsphim= new ArrayList<>();

        getdata(dsphim,"https://huongkute1998.000webhostapp.com/",list_phim);

        list_phim.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                phim p= (phim) list_phim.getItemAtPosition(position);
                Intent intent= new Intent(MainActivity.this,detail_phim_activity.class);
                intent.putExtra("data_click",p);
                startActivity(intent);


            }
        });
        a = new adapter(MainActivity.this,dsphim);
        list_phim.setAdapter(a);
        init_actionbar();
    }

    public void init_actionbar()
    {

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        View view= LayoutInflater.from(MainActivity.this).inflate(R.layout.customactionbar,null);

        searchview= view.findViewById(R.id.search_view);
        getSupportActionBar().setCustomView(view);
        Toolbar parent= (Toolbar) view.getParent();
        parent.setContentInsetsAbsolute(0,0);
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.width = ViewGroup.LayoutParams.MATCH_PARENT;
        view.setLayoutParams(lp);
        init_seachview();
    }

    private void init_seachview() {


            adapter = new adapter_search(MainActivity.this,R.layout.adapter_search_view,R.id.search_view,dsphim);

        searchview.setAdapter(adapter);

        searchview.addTextChangedListener(new TextWatcher() {
                                              @Override
                                              public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                                              }

                                              @Override
                                              public void onTextChanged(CharSequence s, int start, int before, int count) {


                                              }

                                              @Override
                                              public void afterTextChanged(Editable s) {
                                                  if(TextUtils.isEmpty(searchview.getText().toString().trim()))
                                                  {
                                                      dsphim.clear();
                                                      getdata(dsphim,"https://huongkute1998.000webhostapp.com/",list_phim);

                                                  }
                                                  else
                                                  {

                                                      ArrayList<phim> tmplist= new ArrayList<>();

                                                      for(int i=0;i<dsphim.size();i++)
                                                      {
                                                          if(searchview.getText().toString().indexOf(dsphim.get(i).getName())>0)
                                                          {
                                                              tmplist.add(dsphim.get(i));
                                                              a= new adapter(MainActivity.this,tmplist);


                                                          }
                                                      }

                                                      list_phim.setAdapter(a);

                                                  }
                                              }
                                          }
        );
    }

    public  void getdata(final ArrayList<phim>dsphim, String url, final ListView tv_phim)

    {
        huong_cat= new CatLoadingView();

        huong_cat.show(getSupportFragmentManager(),"huong_cat");
        final Response.Listener<JSONObject> reListener= new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray= response.getJSONArray("data");

                    for(int i=0;i<jsonArray.length();i++)
                    {

                        JSONObject jsonObject= jsonArray.getJSONObject(i);

                        phim p = new phim();
                                p.setId(jsonObject.getInt("id"));
                                p.setName(jsonObject.getString("name"));
                                p.setBanner(jsonObject.getString("baner"));
                                p.setDaodien(jsonObject.getString("daodien"));
                                p.setNgayphathanh(jsonObject.getString("ngayphathanh"));
                                p.setLinkphim(jsonObject.getString("linkphim"));
                                p.setThoiluong(jsonObject.getInt("thoiluong"));
                                p.setMota(jsonObject.getString("mota"));

                                dsphim.add(p);

                    }
                    adapter.notifyDataSetChanged();
                    searchview.setAdapter(adapter);
                    try {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                a.notifyDataSetChanged();
                                list_phim.deferNotifyDataSetChanged();


                            }
                        });
                    }catch (Exception e)
                    {

                    }



                } catch (JSONException e) {
                }

            }
        };

        JsonObjectRequest objectRequest= new JsonObjectRequest(Request.Method.GET, url, (String) null, reListener, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        })
        {
            @Override
            protected void onFinish() {
                super.onFinish();

                huong_cat.dismiss();
            }
        };


        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(objectRequest);


    }





}
