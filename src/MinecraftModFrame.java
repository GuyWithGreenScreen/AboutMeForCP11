import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MinecraftModFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public PictureBox makeImage(String filename, int x, int y, double size) {
        PictureBox temp = new PictureBox(this, "images", filename);
        //System.out.println(imageFile.getAbsolutePath());
        //System.out.println(imageFile.exists());
        ImageCache.Sprite image = ImageCache.getInstance().getImage("images", filename);
        temp.setBorder(new LineBorder(new Color(0, 0, 0)));
        SizeRatio myImageSR = new SizeRatio(image.getWidth(), image.getHeight(), size);
        temp.setBounds(x, y, myImageSR.getWidth(), myImageSR.getHeight());
        return temp;
    }


    public JTextArea makeText(String text, int x, int y, int width, int height, int size) {
        JTextArea temp = new JTextArea();
        temp.setEditable(false);
        temp.setLineWrap(true);
        temp.setFont(new Font("Monospaced", 1, size));
        temp.setText(text);
        temp.setBounds(x, y, width, height);
        return temp;
    }

    public JTextArea makeBG(int x, int y, int width, int height, Color color) {
        JTextArea temp = new JTextArea();
        temp.setEditable(false);
        temp.setLineWrap(true);
        temp.setFont(new Font("Monospaced", 1, 1));
        temp.setText("");
        temp.setBounds(x, y, width, height);
        temp.setBackground(color);
        return temp;
    }

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

    public MinecraftModFrame() throws IOException {
        this.setDefaultCloseOperation(1);
        this.setBounds(100, 100, 1300, 700);
        this.contentPane = new JPanel();
        this.contentPane.setBackground(new Color(187, 246, 255));
        this.contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager)null);
        this.setResizable(false);
        this.setTitle("Minecraft Economy Mod");
        this.setIconImage(ImageCache.getInstance().getImage("images", "Screenshot_20260207_213017.png").getFrame(1d));

        JTextArea aboutProject = makeText(
                   "The core idea for this project was to allow players to be able\n" +
                        "to contribute to and use an economy. Allowing them to make\n" +
                           "their own \"crypto coins\", as well as trade in an open\n" +
                           "market for other resources. Unfortunately the server didn't\n" +
                           "end up happening due to hardware issues. (CPU was dead and\n" +
                           "it took a while to return it, then I didn't have the funds\n" +
                           "to continue anyways.)\n" +
                           "However I still wrote the mod/program. I won't go explaining\n" +
                           "how each an every detail works but outline the communication\n" +
                           "flow between the 3 parts (the mod, the api, and the site), \n" +
                           "since that seems more interesting.\n" +
                           "This project consists of 3 things as mentioned:\n" +
                           "The Minecraft integration itself (Via a Neoforge Mod in Java)\n" +
                           "The API Backend (Written with python and uses FastAPI for\ncommunications)\n" +
                           "The Site (Served by the python backend, but needs JS to\n" +
                           "communicate with the API)\n\n" +
                           "Everything talks with the API, which will respond back.\n" +
                           "The API is the central authority that saves all states\n" +
                           "and does all the logic, the rest is just cosmetic in a\n" +
                           "way.\n" +
                           "Honestly I am now realising how long it will take to\n" +
                           "explain it, I will just give some features I added and\n" +
                           "some screenshots of the site and mod.\n\n" +
                           "SOME OTHER FEATURES:\n" +
                           "- Account+Login system, with password hashing using " +
                           "argon2id.\n" +
                           "- Central \"Banking\" website where players do all " +
                           "transactions\n" +
                           "- Dynamic site pages for when new coins and resources are\n" +
                           "added.",
                45, 45, 500, 650, 14);
        this.contentPane.add(aboutProject);

        JTextArea title = makeText("Minecraft Economy Mod", aboutProject.getX(), aboutProject.getY()-40, 400, 50, 30);
        this.contentPane.add(title);


        PictureBox vsCodeImage = makeImage("Screenshot_20260207_212819.png", aboutProject.getX()+aboutProject.getWidth(), aboutProject.getY(), 130);
        this.contentPane.add(vsCodeImage);

        PictureBox websiteMainImage = makeImage("Screenshot_20260207_213216.png", vsCodeImage.getX()+vsCodeImage.getWidth(), vsCodeImage.getY(), 600);
        this.contentPane.add(websiteMainImage);

        PictureBox inMinecraftImage = makeImage("Screenshot_20260207_213017.png", websiteMainImage.getX(), websiteMainImage.getY()+websiteMainImage.getHeight(), 600);
        this.contentPane.add(inMinecraftImage);

        JTextArea jokeDisclaimer = makeText("Also I should note that the names of the coins " +
                "were my friend's doings\nand in general this is a joke bank, so its " +
                "name and branding is also goofy\n\nIt was all made to be funny. Don't overthink it :)", inMinecraftImage.getX(), inMinecraftImage.getY()+inMinecraftImage.getHeight(), 600, 100, 13);
        this.contentPane.add(jokeDisclaimer);

    }
}
