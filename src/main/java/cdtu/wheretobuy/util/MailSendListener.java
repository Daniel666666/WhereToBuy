package cdtu.wheretobuy.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MailSendListener {
    @Value("${SERVER_URL}")
    private String SERVER_URL;

    @JmsListener(destination = "sendEmail")
    public void sendEmail(Map map){
        try {
            if(map.get("obj").equals("seller")){
                QQMailUtil.sendMail(map.get("addr").toString(), "去哪儿买APP", "你已注册成功，请点击 "+SERVER_URL+"/seller/activate?Id=" + map.get("sellerId") + "&activeCode="+map.get("activeCode") + " 进行激活");
            }else if(map.get("obj").equals("user")){
                QQMailUtil.sendMail(map.get("addr").toString(), "去哪儿买APP", "你已注册成功，请点击 "+SERVER_URL+"/user/activate?Id=" + map.get("userId") + "&activeCode="+map.get("activeCode") + " 进行激活");
            }
            System.out.println("已发送激活邮件："+map.get("addr").toString());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
