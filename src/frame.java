import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;


public class frame extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    public static HashMap<Section, ArrayList<Component>> sectionMap = new HashMap<>();

    public static Section currentSection = Section.MOVIE_SEC;

    public PictureBox makeImage(String filename, int x, int y, double size) {
        try {
            PictureBox temp = new PictureBox(this, "src/images", filename);
            File imageFile = new File("src/images/" + filename);
            //System.out.println(imageFile.getAbsolutePath());
            //System.out.println(imageFile.exists());
            BufferedImage image = ImageIO.read(imageFile);
            temp.setBorder(new LineBorder(new Color(0, 0, 0)));
            SizeRatio myImageSR = new SizeRatio(image.getWidth(), image.getHeight(), size);
            temp.setBounds(x, y, myImageSR.getWidth(), myImageSR.getHeight());
            return temp;
        } catch (IOException e) {
            System.out.println("IOException!");
            e.printStackTrace();
            return new PictureBox(this, "images", filename);
        }
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

    public void hideSection(ArrayList<Component> section) {
        for (Component c : section) {
            c.hide();
        }
    }

    public void showSection(ArrayList<Component> section) {
        for (Component c : section) {
            c.show();
        }
    }

    public void changeSection(Section newSection) {
        hideSection(sectionMap.get(currentSection));
        showSection(sectionMap.get(newSection));
        currentSection = newSection;
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    frame frame = new frame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
    }

    public frame() throws IOException {
        this.setDefaultCloseOperation(3);
        this.setBounds(100, 100, 1200, 800);
        this.contentPane = new JPanel();
        this.contentPane.setBackground(new Color(14, 68, 94));
        //this.contentPane.setBorder(new TitledBorder((Border)null, "", 4, 2, (Font)null, (Color)null));
        this.setContentPane(this.contentPane);
        this.contentPane.setLayout((LayoutManager)null);
        this.setResizable(false);
        this.setTitle("About Me - Mark Kupa");
        File iconImageFile = new File("src/images/me with smile.jpg");
        BufferedImage image = ImageIO.read(iconImageFile);
        this.setIconImage(image);

        // NOTICE HOW THIS WASNT GENERATED AND I WROTE IT BY HAND? I HAVE to get a 300/10 on this assignment now.

        //PictureBox gta = makeImage("GAMBLEGAMBLEGAMBLE.jpg", 160, 350, 351);
        //this.contentPane.add(gta);

        PictureBox myImage = makeImage("20260207_155934.jpg", 770, 50, 250);
        this.contentPane.add(myImage);

        JTextArea myName = makeText(" Mark Kupa", myImage.getX(), myImage.getY()+myImage.getHeight(), myImage.getWidth(), 35, 25);
        myName.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.contentPane.add(myName);

        int quickAboutMeWidth = 660;
        JTextArea quickAboutMe = makeText("My name is Mark Kupa, I'm in grade 11 (but you probably knew that).\n" +
                        "I've been programming for 8 years, my strongest languages are C, Python,\nand Java ordered from most to least in experience" +
                        " and knowledge,\nthough I know all 3 quite well. I have done multiple projects in these languages.\n\n" +
                        "Other languages I've used before include JS, NASM ASM, C++, HTML, and CSS.\n\n" +
                        "Some of my interests include low level systems, semiconductor architecture\n" +
                        "and fabrication, video making, graphics design, and anything to do with a\n" +
                        "computer basically.\n\n" +
                        "I have 2 cats, one of which named Charlie is in the photo to the right.\n\n" +
                        "I also like building computers and systems, I have a homelab with 3\n" +
                        "servers hosting various things, I also sometimes do small electronics\n" +
                        "projects for fun or for something useful, some tools are on the wall behind me.\n\n" +
                        "I also speak Russian, and was born in Belarus.",
                myImage.getX()-quickAboutMeWidth, myImage.getY(), quickAboutMeWidth, myImage.getHeight()+myName.getHeight(), 14);
        quickAboutMe.setBackground(new Color(169, 255, 249));
        quickAboutMe.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.contentPane.add(quickAboutMe);

        JTextArea bgLayer = makeBG(quickAboutMe.getX(), quickAboutMe.getY()+400, quickAboutMeWidth+myImage.getWidth(), quickAboutMe.getHeight(), new Color(255, 255, 255));

        int titlePad = 20;

        // MOVIE_SEC START

        ArrayList<Component> movieSection = new ArrayList<>();
        int moviePadX = 70;
        int moviePadY = 50;

        JTextArea movieTitleText = makeText("My Favorite Movies", bgLayer.getX()+titlePad, bgLayer.getY()+titlePad-10, 300, 60, 25);

        PictureBox interstellarImage = makeImage("Screenshot_20260207_185451.png", bgLayer.getX() + titlePad, bgLayer.getY() + moviePadY, 165);
        this.contentPane.add(interstellarImage);
        movieSection.add(interstellarImage);
        PictureBox inceptionImage = makeImage("Screenshot_20260207_185515.png", interstellarImage.getX() + interstellarImage.getWidth() + moviePadX, bgLayer.getY() + moviePadY, 165);
        this.contentPane.add(inceptionImage);
        movieSection.add(inceptionImage);
        PictureBox elCaminoImage = makeImage("Screenshot_20260207_185511.png", inceptionImage.getX() + inceptionImage.getWidth() + moviePadX, bgLayer.getY() + moviePadY, 165);
        this.contentPane.add(elCaminoImage);
        movieSection.add(elCaminoImage);
        PictureBox ironManImage = makeImage("Screenshot_20260207_190654.png", elCaminoImage.getX() + elCaminoImage.getWidth() + moviePadX, bgLayer.getY() + moviePadY, 165);
        this.contentPane.add(ironManImage);
        movieSection.add(ironManImage);

        this.contentPane.add(movieTitleText);
        movieSection.add(movieTitleText);

        sectionMap.put(Section.MOVIE_SEC, movieSection);
        hideSection(movieSection);

        // MOVIE_SEC END



        // CAT_SEC START
        ArrayList<Component> catSection = new ArrayList<>();

        JTextArea catTitleText = makeText("My Cats", bgLayer.getX()+titlePad, bgLayer.getY()+titlePad-10, 300, 60, 25);

        int catPadY = 50;
        int catPadX = 10;

        PictureBox charlieImage = makeImage("20251218_100422.jpg", bgLayer.getX()+catPadX, bgLayer.getY()+catPadY, 250);
        this.contentPane.add(charlieImage);
        catSection.add(charlieImage);

        PictureBox lizzyImage = makeImage("20251121_115732.jpg", bgLayer.getX()+bgLayer.getWidth(), bgLayer.getY()+catPadY, 250);
        lizzyImage.setLocation(bgLayer.getX()+bgLayer.getWidth()-lizzyImage.getWidth()-catPadX, bgLayer.getY()+catPadY);
        this.contentPane.add(lizzyImage);
        catSection.add(lizzyImage);

        JTextArea charlieAbout = makeText("This is Charlie\n" +
                        "He is 3 years old, born on\nDec 24th 2022.\n" +
                        "He likes to sleep with me.\n" +
                        "He is also big.\n(ALL muscles NO fat)",
                charlieImage.getX()+charlieImage.getWidth(), charlieImage.getY(), 200, 100, 12);
        charlieAbout.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.contentPane.add(charlieAbout);
        catSection.add(charlieAbout);

        JTextArea lizzyAbout = makeText("Lizzy on top, Charlie\n" +
                        "towards the bottom.\n" +
                        "Lizzy is almost 5 years\n" +
                        "old which is crazy to me.\n" +
                        "She has been from Niagara\n" +
                        "Falls, to Winnipeg to\n" +
                        "B.C., so all over Canada,\n" +
                        "when she went on trips\n" +
                        "with us. She is more\n" +
                        "quiet and likes to be\n" +
                        "around people but by\n" +
                        "herself. She is also\n" +
                        "the mother of Charlie.",
                lizzyImage.getX()-180, lizzyImage.getY(), 180, 200, 12);
        lizzyAbout.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.contentPane.add(lizzyAbout);
        catSection.add(lizzyAbout);


        this.contentPane.add(catTitleText);
        catSection.add(catTitleText);
        sectionMap.put(Section.CAT_SEC, catSection);
        hideSection(catSection);

        // CAT_SEC END

        // PROJECT_SEC START

        ArrayList<Component> projectSection = new ArrayList<>();

        JTextArea projectTitleText = makeText("2 Of My Most Recent Projects", bgLayer.getX()+titlePad, bgLayer.getY()+titlePad-10, 600, 60, 25);

        JTextArea minecraftBankProjectText = makeText("This is a mod I made for a Minecraft server\n" +
                        "I was going to make for me and my friends.\n" +
                        "Unfortunately the server didn't happen, but\n" +
                        "I still made the mod for it. This is a very\n" +
                        "interesting project as it scales across 3\n" +
                        "languages. Java for the Minecraft mod\n" +
                        "integration itself, Python for the\n" +
                        "API and backend, and JS for the site\n" +
                        "(Plus obviously CSS and HTML)\n" +
                        "Click button to read more.",
                bgLayer.getX()+titlePad, bgLayer.getY() + titlePad + 70, 350, 175, 14);
        minecraftBankProjectText.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.contentPane.add(minecraftBankProjectText);
        projectSection.add(minecraftBankProjectText);

        JTextArea minecraftBankProjectTitle = makeText("Minecraft Economy + Bank Mod", minecraftBankProjectText.getX(), minecraftBankProjectText.getY()-30, 350, 35, 20);
        this.contentPane.add(minecraftBankProjectTitle);
        projectSection.add(minecraftBankProjectTitle);

        JButton minecraftModButton = new JButton("More Info");
        minecraftModButton.setBorder(new LineBorder(new Color(0, 0, 0)));
        minecraftModButton.setBounds(minecraftBankProjectText.getX(), minecraftBankProjectText.getY()+minecraftBankProjectText.getHeight()+10, 100, 30);
        minecraftModButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                MinecraftModFrame minecraftMod = new MinecraftModFrame();
                frame.this.setDefaultCloseOperation(1);
                minecraftMod.setVisible(true);
            }
        });
        this.contentPane.add(minecraftModButton);
        projectSection.add(minecraftModButton);


        JTextArea quickOBFProjectText = makeText("This is a CLI app I made for myself in C.\n" +
                        "Basically it just obfuscates files with a\n" +
                        "password. While it isn't cryptographically\n" +
                        "secure, it isn't made to be that. This is\n" +
                        "one of my most polished projects yet and\n" +
                        "if you are interested you can check it out\n" +
                        "on my Github at:\n" +
                        "https://github.com/GuyWithGreenScreen/QuickOBF",
                bgLayer.getX()+bgLayer.getWidth()-350-titlePad, bgLayer.getY() + titlePad + 70, 350, 175, 14);
        quickOBFProjectText.setBorder(new LineBorder(new Color(0, 0, 0)));
        this.contentPane.add(quickOBFProjectText);
        projectSection.add(quickOBFProjectText);

        JTextArea quickOBFProjectTitle = makeText("QuickOBF Obfuscator", quickOBFProjectText.getX(), quickOBFProjectText.getY()-30, 350, 35, 20);
        this.contentPane.add(quickOBFProjectTitle);
        projectSection.add(quickOBFProjectTitle);


        this.contentPane.add(projectTitleText);
        projectSection.add(projectTitleText);
        sectionMap.put(Section.PROJECT_SEC, projectSection);
        hideSection(projectSection);

        // PROJECT_SEC END


        int buttonPadX = 40;
        int buttonWidth = 200;
        int buttonHeight = 40;

        JButton movieSecButton = new JButton("Movies");
        movieSecButton.setBorder(new LineBorder(new Color(0, 0, 0)));
        movieSecButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeSection(Section.MOVIE_SEC);
            }
        });
        movieSecButton.setBounds(bgLayer.getX(), bgLayer.getY()-60, buttonWidth, buttonHeight);
        this.contentPane.add(movieSecButton);

        JButton catSecButton = new JButton("My Cats");
        catSecButton.setBorder(new LineBorder(new Color(0, 0, 0)));
        catSecButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeSection(Section.CAT_SEC);
            }
        });
        catSecButton.setBounds(movieSecButton.getX() + buttonWidth + buttonPadX, bgLayer.getY()-60, buttonWidth, buttonHeight);
        this.contentPane.add(catSecButton);

        JButton projectSecButton = new JButton("My Projects");
        projectSecButton.setBorder(new LineBorder(new Color(0, 0, 0)));
        projectSecButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                changeSection(Section.PROJECT_SEC);
            }
        });
        projectSecButton.setBounds(catSecButton.getX() + buttonWidth + buttonPadX, bgLayer.getY()-60, buttonWidth, buttonHeight);
        this.contentPane.add(projectSecButton);

        changeSection(Section.PROJECT_SEC);

        this.contentPane.add(bgLayer);

    }
}