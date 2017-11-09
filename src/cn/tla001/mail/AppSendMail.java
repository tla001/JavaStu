package cn.tla001.mail;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.junit.Test;

import com.sun.mail.util.MailSSLSocketFactory;

public class AppSendMail {
	private static final String sendName = "1414419047@qq.com";
	private static final String userName = "1414419047";
	private static final String token = "uaoioxrakbxmicej";
	private static final String recName = "tla001@sina.cn";

	@Test
	public void sendTest() throws MessagingException, GeneralSecurityException {
		// 0. 邮件参数
		/*
		 * 发送 smtp.qq.com 465 |||接收pop.qq.com 995
		 */
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp"); // 指定协议
		prop.put("mail.smtp.host", "smtp.qq.com"); // 主机 stmp.qq.com
		prop.put("mail.smtp.port", "465"); // 端口
		prop.put("mail.smtp.auth", "true"); // 用户密码认证
		prop.put("mail.debug", "true"); // 调试模式
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.socketFactory", sf);
		// 1.创建一个邮件的会话
		Session session = Session.getDefaultInstance(prop);
		// 2. 创建邮件体对象 (整封邮件对象)
		MimeMessage message = new MimeMessage(session);
		// 3. 设置邮件体参数:
		// 3.1 标题
		message.setSubject("我的第一封邮件	");
		// 3.2 邮件发送时间
		message.setSentDate(new Date());
		// 3.3 发件人
		message.setSender(new InternetAddress(sendName));
		// 3.4 接收人
		message.setRecipient(RecipientType.TO, new InternetAddress(recName));
		// 3.5内容
		// message.setText("你好，已经发送成功！  正文...."); // 简单纯文本邮件
		// 邮件中含有超链接
		// message.setText("<a href='#'>百度</a>");
		message.setContent("<a href='#'>百度</a>", "text/html;charset=UTF-8");

		message.saveChanges(); // 保存邮件(可选)

		// 4. 发送
		Transport trans = session.getTransport();
		trans.connect(userName, token);
		// 发送邮件
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();
	}
}
