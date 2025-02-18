package com.example.myapplication114514;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SecondFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondFragment extends Fragment {
    String apiUrl = "https://v2.jokeapi.dev/joke/Any";
    private TextView mTvSentence;
    private Handler mHandler;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SecondFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SecondFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondFragment newInstance(String param1, String param2) {
        SecondFragment fragment = new SecondFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
        mHandler = new MyHandler();


    }

    private void sendGetRequest() {
        new Thread(() -> {
            try {
                URL url = new URL(apiUrl);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                connection.connect();
                int responseCode = connection.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(
                            new InputStreamReader(connection.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    Log.d("success", "请求成功");
                    Message message = new Message();
                    message.obj = response.toString();
                    mHandler.sendMessage(message);
                } else {
                    Log.d("fail", "请求失败，响应码: " + responseCode);
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String result = (String) msg.obj;
            setText(decodeJson(result));
        }
    }

    private JsonData decodeJson(String data) {
        JsonData jsonData = new JsonData();
        jsonData.data = new ArrayList<>();
        JsonData.DetailData detailData = new JsonData.DetailData();
        try {
            JSONObject jsonObject = new JSONObject(data);
            if ("single".equals(jsonObject.getString("type"))) {
                detailData.joke = jsonObject.getString("joke");
            } else if ("twopart".equals(jsonObject.getString("type"))) {
                detailData.setup = jsonObject.getString("setup");
                detailData.delivery = jsonObject.getString("delivery");
            }
            jsonData.data.add(detailData);
            Log.d("json1", "decodeJson: 成功");
        } catch (Exception e) {
            Log.e("json", "decodeJson", e);
        }
        return jsonData;
    }

    private void setText(JsonData jsonData) {
        if (!jsonData.data.isEmpty()) {
            JsonData.DetailData detailData = jsonData.data.get(0);
            if (detailData.joke != null) {
                mTvSentence.setText(detailData.joke);
            } else if (detailData.setup != null && detailData.delivery != null) {
                mTvSentence.setText(detailData.setup + "\n" + detailData.delivery);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second, container, false);
        mTvSentence = view.findViewById(R.id.tv_item_sentence);
        sendGetRequest();
        return view;
    }
}
