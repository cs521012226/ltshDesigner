package org.ltsh.framework.util.email;

import java.util.Date;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件发送,带附件
 */
public class SimpleMailSender {
	
	/**
	 * 发送邮件
	 * 
	 * @param mailInfo 待发送的邮件信息
	 */
	public SendEmailResult sendHtmlMail(MailSenderInfo mailInfo) {
		SendEmailResult sendEmailResult = new SendEmailResult();
		// 判断是否需要身份认证
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		// 如果需要身份认证，则创建一个密码验证器
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		// 根据邮件会话属性和密码验证器构造一个发送邮件的session
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		try {
			// 根据session创建一个邮件消息
			Message mailMessage = new MimeMessage(sendMailSession);
			
			// 创建邮件发送者地址
			Address from = new InternetAddress(mailInfo.getFromAddress());
			// 设置邮件消息的发送者
			mailMessage.setFrom(from);
			// 创建邮件的接收者地址，并设置到邮件消息中
			Address to = new InternetAddress(mailInfo.getToAddress());
			// Message.RecipientType.TO属性表示接收者的类型为TO
			mailMessage.setRecipient(Message.RecipientType.TO, to);
			// 设置邮件消息的主题
			mailMessage.setSubject(mailInfo.getSubject());
			// 设置邮件消息发送的时间
			mailMessage.setSentDate(new Date());
			// MiniMultipart类是一个容器类，包含MimeBodyPart类型的对象
			Multipart mainPart = new MimeMultipart();

			String sendContext = mailInfo.getContent();//得到发送信息模板
			Map<String, String> params = mailInfo.getParams();
			if(params != null){//添加参数
				for (String param : params.keySet()) {
					sendContext = sendContext.replaceAll("\\{"+param+"\\}", params.get(param));
				}
			}
			mailInfo.setContent(sendContext);
 
            MimeBodyPart mbp = new MimeBodyPart();  
            mbp.setContent(sendContext, "text/html;charset=gb2312");  
            mainPart.addBodyPart(mbp);
            Vector<String> file = mailInfo.getFile();//得到邮件附件集合
            if(!file.isEmpty()){//有附件  
                Enumeration<String> efile=file.elements();  
                while(efile.hasMoreElements()){   
                    mbp=new MimeBodyPart();  
                    FileDataSource fds=new FileDataSource(efile.nextElement()); //得到数据源  
                    mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart  
                    mbp.setFileName(fds.getName());  //得到文件名同样至入BodyPart  
                    mainPart.addBodyPart(mbp);
                }    
                file.removeAllElements();      
            }
			// 将MiniMultipart对象设置为邮件内容
			mailMessage.setContent(mainPart);
			// 发送邮件
			Transport.send(mailMessage, mailMessage.getAllRecipients());
			sendEmailResult.setStatus(2);
			sendEmailResult.setMsg("发送成功!");
		} catch (MessagingException ex) {
			ex.printStackTrace();
			sendEmailResult.setStatus(8);
			sendEmailResult.setMsg(ex.getMessage());
		}
		if(sendEmailResult.getStatus() == null){
			sendEmailResult.setStatus(0);
			sendEmailResult.setMsg("信息没有发送成功!");
		}
		return sendEmailResult;
	}
}