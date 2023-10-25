import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        Enterprise enterprise = new Enterprise();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                EnterpriseGUI gui = new EnterpriseGUI(enterprise);
                gui.setVisible(true);
            }
        });
    }
}
