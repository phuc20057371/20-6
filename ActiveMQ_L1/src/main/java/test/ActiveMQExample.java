package test;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQExample {
    public static void main(String[] args) {
        try {
            // Tạo ConnectionFactory
            ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","http://localhost:8161/");

            // Tạo Connection
            Connection connection = connectionFactory.createConnection();
            connection.start();

            // Tạo Session
            Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

            // Tạo MessageProducer
            MessageProducer producer = session.createProducer(session.createQueue("myqueue"));

            // Tạo TextMessage và gửi đi
            TextMessage message = session.createTextMessage("Hello, ActiveMQ!");
            producer.send(message);

            // Đóng kết nối
            producer.close();
            session.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}