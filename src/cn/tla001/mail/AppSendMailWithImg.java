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
		// 0. �ʼ�����
		/*
		 * ���� smtp.qq.com 465 |||����pop.qq.com 995
		 */
		Properties prop = new Properties();
		prop.put("mail.transport.protocol", "smtp"); // ָ��Э��
		prop.put("mail.smtp.host", "smtp.qq.com"); // ���� stmp.qq.com
		prop.put("mail.smtp.port", "465"); // �˿�
		prop.put("mail.smtp.auth", "true"); // �û�������֤
		prop.put("mail.debug", "true"); // ����ģʽ
		MailSSLSocketFactory sf = new MailSSLSocketFactory();
		sf.setTrustAllHosts(true);
		prop.put("mail.smtp.ssl.enable", "true");
		prop.put("mail.smtp.ssl.socketFactory", sf);
		// 1.����һ���ʼ��ĻỰ
		Session session = Session.getDefaultInstance(prop);
		// 2. �����ʼ������ (�����ʼ�����)
		MimeMessage message = new MimeMessage(session);
		// 3. �����ʼ������:
		// 3.1 ����
		message.setSubject("��ͼƬ�ʼ�");
		// 3.2 �ʼ�����ʱ��
		message.setSentDate(new Date());
		// 3.3 ������
		message.setSender(new InternetAddress(sendName));
		// 3.4 ������
		message.setRecipient(RecipientType.TO, new InternetAddress(recName));
		/*************** �����ʼ�����: �๦���û��ʼ� (related) *******************/
		// 4.1 ����һ���๦���ʼ���
		MimeMultipart related = new MimeMultipart("related");
		// 4.2 �����๦���ʼ������� = ����ı� + �Ҳ�ͼƬ��Դ
		MimeBodyPart content = new MimeBodyPart();
		MimeBodyPart resource = new MimeBodyPart();

		// ���þ�������: a.��Դ(ͼƬ)
		// String filePath = AppSendMailWithImg.class.getResource(
		// "/resources/lufi.jpg").getPath();
		String filePath = "./resources/lufi.jpg";
		// System.out.println(filePath);
		DataSource ds = new FileDataSource(new File(filePath));
		DataHandler handler = new DataHandler(ds);
		resource.setDataHandler(handler);
		resource.setContentID("lufi.jpg"); // ������Դ���ƣ����������

		// ���þ�������: b.�ı�
		content.setContent("<img src='cid:lufi.jpg'/>  ·�ɣ�",
				"text/html;charset=UTF-8");

		related.addBodyPart(content);
		related.addBodyPart(resource);

		/******* 4.3 �ѹ����ĸ����ʼ��죬��ӵ��ʼ��� ********/
		message.setContent(related);

		// 5. ����
		Transport trans = session.getTransport();
		trans.connect(userName, token);
		// �����ʼ�
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();
	}
}
