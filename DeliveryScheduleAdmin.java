

/**
old code created by carlos. May need to be redone. 


import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class DeliveryScheduleAdmin extends JFrame {

    private JTextField txtPostcode;
    private JTextField txtDeliveryDay;
    private JTextField txtDeliveryCost;
    private JTable scheduleTable;

    public DeliveryScheduleAdmin() {
        setTitle("Create Delivery Schedule");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        txtPostcode = new JTextField();
        txtDeliveryDay = new JTextField();
        txtDeliveryCost = new JTextField();

        inputPanel.add(new JLabel("Postcode:"));
        inputPanel.add(txtPostcode);
        inputPanel.add(new JLabel("Delivery Day:"));
        inputPanel.add(txtDeliveryDay);
        inputPanel.add(new JLabel("Delivery Cost:"));
        inputPanel.add(txtDeliveryCost);

        JButton btnAdd = new JButton("Add Schedule");
        JButton btnUpdate = new JButton("Update Schedule");
        JButton btnDelete = new JButton("Delete Schedule");

        btnAdd.addActionListener(e -> addSchedule());
        btnUpdate.addActionListener(e -> updateSchedule());
        btnDelete.addActionListener(e -> deleteSchedule());

        inputPanel.add(btnAdd);
        inputPanel.add(btnUpdate);
        inputPanel.add(btnDelete);

        panel.add(inputPanel, BorderLayout.NORTH);

        scheduleTable = new JTable();
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

    private void addSchedule() {
        String postcode = txtPostcode.getText();
        String deliveryDay = txtDeliveryDay.getText();
        double deliveryCost = Double.parseDouble(txtDeliveryCost.getText());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            PreparedStatement ps = connection.prepareStatement("INSERT INTO delivery_schedules (postcode, delivery_day, delivery_cost) VALUES (?, ?, ?)");
            ps.setString(1, postcode);
            ps.setString(2, deliveryDay);
            ps.setDouble(3, deliveryCost);
            ps.executeUpdate();
            ps.close();
            connection.close();

            loadScheduleData(scheduleTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateSchedule() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a schedule to update.");
            return;
        }

        String postcode = (String) scheduleTable.getValueAt(selectedRow, 0);
        String deliveryDay = txtDeliveryDay.getText();
        double deliveryCost = Double.parseDouble(txtDeliveryCost.getText());

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            PreparedStatement ps = connection.prepareStatement("UPDATE delivery_schedules SET delivery_day = ?, delivery_cost = ? WHERE postcode = ?");
            ps.setString(1, deliveryDay);
            ps.setDouble(2, deliveryCost);
            ps.setString(3, postcode);
            ps.executeUpdate();
            ps.close();
            connection.close();

            loadScheduleData(scheduleTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSchedule() {
        int selectedRow = scheduleTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Select a schedule to delete.");
            return;
        }

        String postcode = (String) scheduleTable.getValueAt(selectedRow, 0);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mhds", "root", "password");
            PreparedStatement ps = connection.prepareStatement("DELETE FROM delivery_schedules WHERE postcode = ?");
            ps.setString(1, postcode);
            ps.executeUpdate();
            ps.close();
            connection.close();

            loadScheduleData(scheduleTable);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DeliveryScheduleAdmin DeliveryScheduleAdmin = new DeliveryScheduleAdmin();
            DeliveryScheduleAdmin.setVisible(true);
        });
    }
}


*/
