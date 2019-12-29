package com.example.client.Robot.controller.controller;

import android.os.AsyncTask;

import com.example.client.Robot.controller.DTO.ListData;
import com.example.client.Robot.controller.model.NewsList;
import com.example.client.Robot.controller.utils.HttpUtil;
import com.example.client.Robot.controller.utils.Utility;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.List;

public class HttpData extends AsyncTask<String, Void, String> {


    private HttpGetDataListener listener;

    private String url;

    public HttpData(String url, HttpGetDataListener listener) {
        this.url = url;
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            String content = "";
            final ListData[] listData = new ListData[1];
//            String content = "";
//            mHttpClient = new DefaultHttpClient();
//            mHttpGet = new HttpGet(url);
//            mHttpResponse = mHttpClient.execute(mHttpGet);
//            mHttpEntity = mHttpResponse.getEntity();
//            in = mHttpEntity.getContent();
//            BufferedReader br = new BufferedReader(new InputStreamReader(in));
//            String line = null;
//            StringBuffer sb = new StringBuffer();
//            while ((line = br.readLine()) != null) {
//                sb.append(line);
//            }
            //发送请求
            String address = url;
            String jsonResult = HttpUtil.request(address);
            System.out.println(jsonResult);
//            JSONObject jb = new JSONObject(jsonResult);
//            jsonResult=jb.getString("text");
//           //图灵api
//            return jsonResult;

            //天启API
            NewsList newlist= Utility.parseJsonWithGson(jsonResult);
            final int code = newlist.code;
                    final String msg = newlist.msg;
                    if (code == 200) {
                        List<ListData> newsList = newlist.newsList;
                        ListData listData1 = newsList.get(0);
                        jsonResult= listData1.getContent();
                        return jsonResult;
                    }






//            HttpUtil.sendOkHttpRequest(address, new Callback() {
//
//                @Override
//                public void onFailure(Call call, IOException e) {
//                    //机器人连接请求失败
//                    System.out.println("机器人连接请求失败");
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//                    //返回成功
//                     String responseText = response.body().string();
//                     NewsList newlist = Utility.parseJsonWithGson(responseText);
//                    final int code = newlist.code;
//                    final String msg = newlist.msg;
//                    if (code == 200) {
//                        listData[0] = newlist.newsList.get(0);
//                    }
//                }
//            });

        } catch (Exception e) {
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {
        listener.getDataUrl(result);
        super.onPostExecute(result);
    }
}