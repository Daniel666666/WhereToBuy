package cdtu.wheretobuy.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.Address;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * 电子邮件工具类
 * @author Administrator
 *
 */
public class QQMailUtil {
	
	
	/**
	 * 发送电子邮件
	 * @param addr 收件人地址
	 * @param subject 主题
	 * @param text 内容
	 * @throws MessagingException
	 */
	public static void sendMail(String addr,String subject,String text) throws MessagingException{
		Properties props=new Properties();
		// 开启debug调试，以便在控制台查看
		props.setProperty("mail.debug", "true");
		// 设置邮件服务器主机名
		props.setProperty("mail.host", "smtp.qq.com");
		// 发送服务器需要身份验证
		props.setProperty("mail.smtp.auth", "true");
		// 发送邮件协议名称
		props.setProperty("mail.transport.protocol", "smtp");

		// 开启SSL加密，否则会失败
		MailSSLSocketFactory sf = null;
		try {
			sf = new MailSSLSocketFactory();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		sf.setTrustAllHosts(true);
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.ssl.socketFactory", sf);

		Session session=Session.getInstance(props);
		//构造信息体 
		MimeMessage message =new MimeMessage(session);
		 //发件地址 
		Address address = new InternetAddress("wheretobuy@qq.com");
		message.setFrom(address);
		//收件地址 
		Address toAddress = new InternetAddress(addr); 
		message.setRecipient(MimeMessage.RecipientType.TO, toAddress);
		//主题 
		message.setSubject(subject);
		//正文 
		message.setText(text);
		message.saveChanges();
		Transport transport = session.getTransport("smtp"); 
		transport.connect("smtp.qq.com", "wheretobuy@qq.com", "swhiktflsmmnbhcd"); //发送 
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
		
	}
	
}
