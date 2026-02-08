import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class MinecraftModFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MinecraftModFrame frame = new MinecraftModFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public MinecraftModFrame() {
        this.setDefaultCloseOperation(1);
        this.setBounds(100, 100, 651, 518);
        this.contentPane = new JPanel();
        this.contentPane.setBackground(new Color(187, 246, 255));
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager)null);


    }
}
