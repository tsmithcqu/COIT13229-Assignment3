import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DeliveryScheduleView extends JFrame {

    public DeliveryScheduleView() {
        setTitle("Delivery Schedule View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTable scheduleTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(scheduleTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        loadScheduleData(scheduleTable);

        add(panel);
    }

    private void loadScheduleData(JTable scheduleTable) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM delivery_schedules");

            String[] columnNames = {"Postcode", "Delivery Day", "Delivery Cost"};
            Object[][] data = new Object[50][3];
            int rowCount = 0;

            while (resultSet.next()) {
                data[rowCount][0] = resultSet.getString("postcode");
                data[rowCount][1] = resultSet.getString("delivery_day");
                data[rowCount][2] = resultSet.getDouble("delivery_cost");
                rowCount++;
            }

            scheduleTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeliveryScheduleView deliveryScheduleView = new DeliveryScheduleView();
            deliveryScheduleView.setVisible(true);
        });
    }
}
