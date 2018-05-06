package com.box.framework.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送邮件的工具类
 * 
 */
public class MailUtils {
	public static void sendMail(String to,String code) throws Exception{
		/**
	     * 发送邮件的props文件
	     */
		Properties props = new Properties();
		// 初始化props，创建信件服务器
		//props.setProperty("mail.smtp", "localhost");
	    // props.setProperty("mail.host", "localhost");  
	     props.setProperty("mail.host", "smtp.sina.com");  
	      //发送服务器需要身份验证,要采用指定用户名密码的方式去认证  
	       props.setProperty("mail.smtp.auth", "true");  
	      //发送邮件协议名称  
	       props.setProperty("mail.transport.protocol", "smtp"); 
		// 1.Session对象.连接(与邮箱服务器连接)
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				//初始化邮箱和密码
				//return new PasswordAuthentication("root@sina.com", "123456");
				return new PasswordAuthentication("Chunyan192@sina.com", "202.114.38.83");
			}
			
			
		});
		
		// 2.构建邮件信息:创建mime类型邮件
		Message message = new MimeMessage(session);
		// 发件人:
		message.setFrom(new InternetAddress("Chunyan192@sina.com"));
		// 收件人:
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		// 设置标题
		message.setSubject("来自东纳包装盒的激活邮件");
		// 设置正文
		//message.setContent("<h1>来自东纳包装盒的官网激活邮件</h1><h3><a href='http://192.168.1.102:8080/SSH/user/user_active.action?activeuser.code="+code+"'>http://192.168.1.102:8080/SSH/user/user_active.action?activeuser.code="+code+"</a></h3>", "text/html;charset=UTF-8");
		message.setContent("<h1>来自东纳包装盒的官网激活邮件</h1><h3><a href='http://139.199.159.121:9090/Box-api/user/userActive?activeuser.code="+code+"'>http://139.199.159.121:9090/SSH/user/user_active.action?activeuser.code="+code+"</a></h3>", "text/html;charset=UTF-8");
		
		// 3.发送对象
		Transport.send(message);
	}
}
