import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException, UnknownHostException {
//        Car car = new Car();
//        car.name = "hhhh";
//        car.No = 1265;
////        try {
////            FileOutputStream fileOutputStream=new FileOutputStream("./car.ser");
////            ObjectOutputStream out=new ObjectOutputStream(fileOutputStream);
////            out.writeObject(car);
////            out.close();
////            fileOutputStream.close();
////        } catch (IOException e) {
////            e.printStackTrace();
////        }
//        try {
//            FileInputStream fileInputStream = new FileInputStream("./car.ser");
//            ObjectInputStream in = new ObjectInputStream(fileInputStream);
//            Car car1=(Car)in.readObject();
//            fileInputStream.close();
//            in.close();
//            System.out.println(car1.No+" "+car1.name);
//        } catch (ClassNotFoundException | IOException e) {
//            e.printStackTrace();
//        }
//        String a = "123", b = "456", c;
//        c = a.concat(b);
//        int port = 7000;
//        // 在端口上创建一个服务器套接字
//        ServerSocket serverSocket = new ServerSocket(port);
//        // 监听来自客户端的连接
//        Socket socket = serverSocket.accept();
//        DataInputStream dis = new DataInputStream(
//                new BufferedInputStream(socket.getInputStream()));
//        DataOutputStream dos = new DataOutputStream(
//                new BufferedOutputStream(socket.getOutputStream()));
//        do {
//            double length = dis.readDouble();
//            System.out.println("服务器端收到的边长数据为：" + length);
//            double result = length * length;
//            dos.writeDouble(result);
//            dos.flush();
//
//        } while (dis.readInt() != 0);
//
//        socket.close();
//        serverSocket.close();
//    }
                int port = 7000;
                String host = "localhost";
                // 创建一个套接字并将其连接到指定端口号
                Socket socket = new Socket(host, port);
                DataInputStream dis = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                DataOutputStream dos = new DataOutputStream(
                        new BufferedOutputStream(socket.getOutputStream()));
                Scanner sc = new Scanner(System.in);
                boolean flag = false;
                while (!flag) {

                    System.out.println("请输入正方形的边长:");
                    double length = sc.nextDouble();

                    dos.writeDouble(length);
                    dos.flush();

                    double area = dis.readDouble();

                    System.out.println("服务器返回的计算面积为:" + area);

                    while (true) {

                        System.out.println("继续计算？(Y/N)");

                        String str = sc.next();

                        if (str.equalsIgnoreCase("N")) {
                            dos.writeInt(0);
                            dos.flush();
                            flag = true;
                            break;
                        } else if (str.equalsIgnoreCase("Y")) {
                            dos.writeInt(1);
                            dos.flush();
                            break;
                        }
                    }
                }

                socket.close();
            }

       // System.out.println(c);


}
