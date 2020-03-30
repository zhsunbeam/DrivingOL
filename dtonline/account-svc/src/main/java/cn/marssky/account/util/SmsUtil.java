package cn.marssky.account.util;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Random;

@Component
public class SmsUtil {
    //注册模板的参数
    private static String signupTemplateCode="SMS_186610273";
    //忘记密码模板的参数
    private static String forgotPasswordTemplateCode="SMS_186610487";
    //阿里云accessKeyId
    private static String accessKeyId="LTAI4FsnkmY1VYZKTt5gozky";
    //阿里云accessSecret
    private static String accessSecret="wmC0FY8sv07J1971lriD1p0ISKPjvK";
    //日期格式对象
    SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyyMMdd");

    //产生随机数
    private int randomNumber(){
        Random random=new Random();
        return random.nextInt(899999)+100000;
    }

    //发送信息
    public String sendSms(String state,String phone){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId , accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        Object bizId;
        if("0".equals(state)){
            state=signupTemplateCode;
        }else if ("1".equals(state)){
            state=forgotPasswordTemplateCode;
        }
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", "blblyoga验证码");
        request.putQueryParameter("TemplateCode", state);
        request.putQueryParameter("TemplateParam", "{\"code\":\""+randomNumber()+"\"}");
        try {
            CommonResponse response = client.getCommonResponse(request);
            String data = response.getData();
            String sData = data.replaceAll("'\'", "");
            Gson gson = new Gson();
            Map map = gson.fromJson(sData, Map.class);
            bizId = map.get("BizId");
        } catch (Exception e) {
            e.printStackTrace();
            return "goUnder";
        }
        return bizId.toString();
    }

    //查询验证码
    public String querySendDetails(String phone,String bizId){
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessSecret);
        IAcsClient client = new DefaultAcsClient(profile);
        CommonRequest request = new CommonRequest();
        String message="";
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("QuerySendDetails");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumber", phone);
        request.putQueryParameter("SendDate", simpleDateFormat.format(new Date()));
        request.putQueryParameter("PageSize", "1");
        request.putQueryParameter("CurrentPage", "1");
        request.putQueryParameter("BizId", bizId);
        try {
            CommonResponse response = client.getCommonResponse(request);
            message=response.getData();
            int index=message.indexOf("：")+1;
            message=message.substring(index,index+6);
        } catch (Exception e) {
            e.printStackTrace();
            return "goUnder";
        }
        return message;
    }

}
