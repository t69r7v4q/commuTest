package ztInterface;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * 测试接口的调用，根据参数(msg_type)的不同，返回不同的结果
 */
public class PUBLIC {
    public static void interfaceCall(List<NameValuePair> param,ComData comData) throws IOException {
        JSONObject result = new JSONObject();
        //用来存放post请求的参数，前面一个键，后面一个值
        param.add(new BasicNameValuePair("data_digest",comData.getData_digest()));
        param.add(new BasicNameValuePair("msg_type",comData.getMsg_type()));
        param.add(new BasicNameValuePair("company_id",comData.getCompany_id()));
        System.out.println("设置的参数："+param.toString());
        //测试接口
        String url="https://intelligent-fat.zt-express.com/branchweb/sortservice";
        //建立HttpPost对象
        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();
        //设置URL参数   输入数据编码成KEY1=VALUE1&KEY2=VALUE2
        StringEntity stringEntity = new UrlEncodedFormEntity(param, "UTF-8");
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/x-www-form-urlencoded; charset=UTF-8");
        httpPost.setEntity(stringEntity);
        //执行Post请求  JSON.parseObject(EntityUtils ?
        HttpResponse response = client.execute(httpPost);
        result = JSON.parseObject(EntityUtils.toString(response.getEntity(), "UTF-8"));
        System.out.println("状态码："+response.getStatusLine().getStatusCode()+"，返回结果："+result);
    }
}
