import ztInterface.ComData;
import ztInterface.MD5;
import ztInterface.PUBLIC;
import ztInterface.module.*;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Test {
    List<NameValuePair> param=new ArrayList<NameValuePair>();

    @org.junit.Test
    public void PIPELINE_STATUS() throws Exception {
        //设置请求参数
        PIPELINE_STATUS PIPELINESTATUSData = new PIPELINE_STATUS("57162-001", new Date().getTime(), "start", "Sorting");
        ComData comData = new ComData(MD5.md52(PIPELINESTATUSData.toString()+"3c82d5aadfe63ffe"),"WCS_PIPELINE_STATUS","shttzn");
        param.add(new BasicNameValuePair("data", PIPELINESTATUSData.toString()));
        PUBLIC.interfaceCall(param,comData);
    }

    @org.junit.Test
    public void SORTING_SETTING() throws Exception{
        //设置请求参数
        ComData comData = new ComData(MD5.md52("57162-001"+"3c82d5aadfe63ffe"),"WCS_SORTING_SETTING","shttzn");
        param.add(new BasicNameValuePair("data","57162-001"));
        PUBLIC.interfaceCall(param,comData);
    }

    @org.junit.Test
    public void GET_BILL_RULE() throws Exception{
        //设置请求参数
        ComData comData = new ComData(MD5.md52("3c82d5aadfe63ffe"),"GET_BILL_RULE","shttzn");
        param.add(new BasicNameValuePair("data",null));
        PUBLIC.interfaceCall(param,comData);
    }

    @org.junit.Test
    public void WCS_SORTING_INFO() throws Exception{
        SORTING_INFO _sorting_info = new SORTING_INFO("78299833560209", "57162-001", 1, new Date().getTime(), 0, null, null, null, "1", "1", 1, null, 1);
        //设置请求参数
        ComData comData = new ComData( MD5.md52(_sorting_info.toString()+"3c82d5aadfe63ffe"),"WCS_SORTING_INFO","shttzn");
        param.add(new BasicNameValuePair("data", _sorting_info.toString()));
        PUBLIC.interfaceCall(param,comData);
    }

    @org.junit.Test
    public void WCS_SORTING_RESULT() throws Exception{
        String result="{\"message\":\"\",\"result\":{\"billCode\":\"78636203846899\",\"errorCode\":\"\",\"isComp\":false,\"pipeline\":\"\",\"scaleSn\":\"\",\"sortPortCode\":[\"040\",\"041\",\"041-A\"],\"sortSource\":\"0\",\"trayCode\":\"127\"},\"status\":true,\"statusCode\":\"SUCCESS\"}";
        JSONObject parse = JSONObject.parseObject(result);
        JSONObject result1 = parse.getJSONObject("result");
        JSONArray sortPortCode = result1.getJSONArray("sortPortCode");
        System.out.println("JSONObject："+parse);
        System.out.println("result："+result1);
        System.out.println("sortPortCode数组："+sortPortCode);
        Random random = new Random();

        for (int i = 0; i <= 20; i++) {
            int num = random.nextInt(sortPortCode.size());
            String o = (String) sortPortCode.get(num);
            System.out.println("选择的sortPortCode："+o);
        }
//        WCS_SORTING_RESULT wcs_sorting_result = new WCS_SORTING_RESULT("1", "78299833560209", "57162-001", new Date().getTime(), 1, o, "1", null, null, null, null, "1", null, 1, 1);
//        ComData comData = new ComData( MD5.md52(wcs_sorting_result.toString()+"3c82d5aadfe63ffe"),"WCS_SORTING_RESULT","shttzn");
//        param.add(new BasicNameValuePair("data",wcs_sorting_result.toString()));
//        PUBLIC.interfaceCall(param,comData);
    }

    @org.junit.Test
    public void WCS_COMPLEMENT_INFO()throws Exception{
        COMPLEMENT_INFO _complement_info = new COMPLEMENT_INFO("78299833560209", "57162-001","1","1");
        ComData comData = new ComData( MD5.md52(_complement_info.toString()+"3c82d5aadfe63ffe"),"WCS_SORTING_RESULT","shttzn");
        param.add(new BasicNameValuePair("data", _complement_info.toString()));
        PUBLIC.interfaceCall(param,comData);
    }
}
