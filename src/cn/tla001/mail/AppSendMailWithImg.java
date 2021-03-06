package cn.tla001.mail;

import java.io.File;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

import org.junit.Test;

import com.sun.mail.util.MailSSLSocketFactory;

public class AppSendMailWithImg {
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
		message.setSubject("带图片邮件");
		// 3.2 邮件发送时间
		message.setSentDate(new Date());
		// 3.3 发件人
		message.setSender(new InternetAddress(sendName));
		// 3.4 接收人
		message.setRecipient(RecipientType.TO, new InternetAddress(recName));
		/*************** 设置邮件内容: 多功能用户邮件 (related) *******************/
		// 4.1 构建一个多功能邮件块
		MimeMultipart related = new MimeMultipart("related");
		// 4.2 构建多功能邮件块内容 = 左侧文本 + 右侧图片资源
		MimeBodyPart content = new MimeBodyPart();
		MimeBodyPart resource = new MimeBodyPart();

		// 设置具体内容: a.资源(图片)
		// String filePath = AppSendMailWithImg.class.getResource(
		// "/resources/lufi.jpg").getPath();
		String filePath = "./resources/lufi.jpg";
		// System.out.println(filePath);
		DataSource ds = new FileDataSource(new File(filePath));
		DataHandler handler = new DataHandler(ds);
		resource.setDataHandler(handler);
		resource.setContentID("lufi.jpg"); // 设置资源名称，给外键引用

		// 设置具体内容: b.文本
		content.setContent("<img src='cid:lufi.jpg'/>  路飞！",
				"text/html;charset=UTF-8");

		related.addBodyPart(content);
		related.addBodyPart(resource);

		/******* 4.3 把构建的复杂邮件快，添加到邮件中 ********/
		message.setContent(related);

		// 5. 发送
		Transport trans = session.getTransport();
		trans.connect(userName, token);
		// 发送邮件
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();
	}
}
