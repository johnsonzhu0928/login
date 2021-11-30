//注册时，输2次密码
//注册用户名是11位手机号的纯数字
package cn.zhu.test;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class TestLogin {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/day1129", "root", "123");
        //String sql----;
        //Statement st=conn.createStatement();
        //Result set=st.executeQuery(sql);
        for (; ; ) {
            System.out.println("请输入数字(1.注册 2.登录 3.退出):");
            Scanner scanner = new Scanner(System.in);
            Integer num = scanner.nextInt();
            if (num == 1) {
                System.out.println("开始注册!");
                System.out.println("请输入你想注册的用户名:");
                String username = scanner.next();
                for (; ; ) {
                    if (username.length() < 11) {
                        System.out.println("用户名必须是11位纯数字，请重新输入!");
                        break;
                    }
                    System.out.println("请输入你想注册的密码:");
                    String password1 = scanner.next();
                    System.out.println("请重复输入一次刚才的密码:");
                    String password2 = scanner.next();
                    if (password1.equals(password2)) {
                        Statement st = conn.createStatement();
                        String sql = "insert into login values('" + username + "','" + password2 + "')";
                        int a = st.executeUpdate(sql);
                        System.out.println("注册成功!共" + a + "条语句收到影响");
                        break;
                    } else {
                        System.out.println("注册失败!两次密码输入不一致!");
                        break;
                    }
                }
            } else if (num == 2) {
                System.out.println("开始登录!");
                System.out.println("请输入用户名:");
                Scanner scanner2 = new Scanner(System.in);
                String username = scanner2.next();
                System.out.println("请输入密码:");
                String password = scanner2.next();
                Statement st = conn.createStatement();
                String sql = "SELECT * FROM login WHERE username='" + username + "' AND password='" + password + "'";
                ResultSet set = st.executeQuery(sql);
                if (set.next()) {
                    System.out.println("登录成功");
                } else {
                    System.out.println("登录失败!");
                }
            } else if (num == 3) {
                System.out.println("退出成功!");
                break;
            } else {
                System.out.println("输入数字有误，请重新输入!");
            }
            System.out.println("是否继续操作?(y/任意除y字符)");
            Scanner scanner1 = new Scanner(System.in);
            String answer = scanner1.nextLine();
            if (answer.equals("y") || answer.equals("Y")) {
                System.out.println("重新开始!");
            } else {
                System.out.println("程序结束!");
                break;
            }
        }

    }
}
