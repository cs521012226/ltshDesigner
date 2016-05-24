package org.ltsh.test.framework.util.email;

import org.ltsh.framework.util.email.MailSenderInfo;
import org.ltsh.framework.util.email.SimpleMailSender;


/**
 * @author FengJianBo
 * @since 2013-8-2
 */

public class MailTest {
	public static void main(String[] args){   
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.163.com");
     mailInfo.setMailServerPort("25");
     mailInfo.setValidate(true);
     mailInfo.setUserName("邮箱用户名");    
     mailInfo.setPassword("邮箱密码");//您的邮箱密码    
     mailInfo.setFromAddress("发件人,与邮箱用户名一样");    
     mailInfo.setToAddress("收件人");    
     mailInfo.setSubject("标题");
     mailInfo.setContent("邮件内容"); 
     mailInfo.addFile("d:\\test123.csv");//添加附件
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
     sms.sendHtmlMail(mailInfo);//发送邮件 
   }  
}
