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
		message.setSubject("�ҵĵ�һ���ʼ�	");
		// 3.2 �ʼ�����ʱ��
		message.setSentDate(new Date());
		// 3.3 ������
		message.setSender(new InternetAddress(sendName));
		// 3.4 ������
		message.setRecipient(RecipientType.TO, new InternetAddress(recName));
		// 3.5����
		// message.setText("��ã��Ѿ����ͳɹ���  ����...."); // �򵥴��ı��ʼ�
		// �ʼ��к��г�����
		// message.setText("<a href='#'>�ٶ�</a>");
		message.setContent("<a href='#'>�ٶ�</a>", "text/html;charset=UTF-8");

		message.saveChanges(); // �����ʼ�(��ѡ)

		// 4. ����
		Transport trans = session.getTransport();
		trans.connect(userName, token);
		// �����ʼ�
		trans.sendMessage(message, message.getAllRecipients());
		trans.close();
	}
}
