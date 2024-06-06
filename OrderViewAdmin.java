
/**
Old code created by Carlos. May need to redo. 
Not currently in use. 


import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderViewAdmin extends JFrame {

    public OrderViewAdmin() {
        setTitle("View Orders");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTable orderTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(orderTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadOrderData(orderTable);

        add(panel);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            OrderViewAdmin orderViewAdmin = new OrderViewAdmin();
            orderViewAdmin.setVisible(true);
        });
    }
}

*/
