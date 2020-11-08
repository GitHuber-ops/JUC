package xyz.blog.JUC;

/**
 * @Project: JUC
 * @Author: Unknown
 * @Create: 2020--09--06--4:29 PM
 */
public class EightLocks {
    public synchronized void sendEmail() {
        System.out.println("发邮件");
    }

    public synchronized void sendSMS() {
        System.out.println("发短信");
    }

    public void hello() {
        System.out.println("hello");
    }
}
