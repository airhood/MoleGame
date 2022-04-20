import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MoleGame extends JFrame {

    private int Width = 1280;
    private int Height = 720;

    private Image bufferImage;
    private Graphics screenGraphic;

    private Clip clip;

    private Image background = new ImageIcon("src/image/background.png").getImage();
    private Image mole = new ImageIcon("src/image/mole.png").getImage();
    private Image hammer = new ImageIcon("src/image/mole.png").getImage();

    private int score;

    ArrayList<String> Alpha = new ArrayList<String>(Arrays.asList("A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"));

    private String key;

    private String key_set = "A";

    public MoleGame() {
        setTitle("두더지 게임");
        setSize(Width, Height);
        setUndecorated(true);
        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case KeyEvent.VK_ESCAPE: // 종료
                        System.exit(0);
                    case KeyEvent.VK_F5: // 설정
                        key = "F5";
                        break;
                    // 두더지 잡기 키
                    case KeyEvent.VK_Q:
                        key = "Q";
                        break;
                    case KeyEvent.VK_W:
                        key = "w";
                        break;
                    case KeyEvent.VK_E:
                        key = "E";
                        break;
                    case KeyEvent.VK_R:
                        key = "R";
                        break;
                    case KeyEvent.VK_T:
                        key = "T";
                        break;
                    case KeyEvent.VK_Y:
                        key = "Y";
                        break;
                    case KeyEvent.VK_U:
                        key = "U";
                        break;
                    case KeyEvent.VK_I:
                        key = "I";
                        break;
                    case KeyEvent.VK_O:
                        key = "O";
                        break;
                    case KeyEvent.VK_P:
                        key = "P";
                        break;
                    case KeyEvent.VK_A:
                        key = "A";
                        break;
                    case KeyEvent.VK_S:
                        key = "S";
                        break;
                    case KeyEvent.VK_D:
                        key = "D";
                        break;
                    case KeyEvent.VK_F:
                        key = "F";
                        break;
                    case KeyEvent.VK_G:
                        key = "G";
                        break;
                    case KeyEvent.VK_H:
                        key = "H";
                        break;
                    case KeyEvent.VK_J:
                        key = "J";
                        break;
                    case KeyEvent.VK_K:
                        key = "K";
                        break;
                    case KeyEvent.VK_L:
                        key = "L";
                        break;
                    case KeyEvent.VK_Z:
                        key = "Z";
                        break;
                    case KeyEvent.VK_X:
                        key = "X";
                        break;
                    case KeyEvent.VK_C:
                        key = "C";
                        break;
                    case KeyEvent.VK_V:
                        key = "V";
                        break;
                    case KeyEvent.VK_B:
                        key = "B";
                        break;
                    case KeyEvent.VK_N:
                        key = "N";
                        break;
                    case KeyEvent.VK_M:
                        key = "M";
                        break;

                }
                if (key == null) {

                } else {
                    check();
                    set();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                key = "";
            }
        });
        resetGame();
        playSound("src/audio/backgroundMusic.wav", true);

    }

    public void check() {
        if (key.equals(key_set)) {
            score = score + 1;
        } else {

        }
    }

    public void set() {
        int key_tempNum = (int)(Math.floor((Math.random()*26)));
        key_set = Alpha.get(key_tempNum).toString();
    }

    public void playSound(String path, boolean isLoop) {
        try {
            clip = AudioSystem.getClip();
            File audioFile = new File(path);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            clip.open(audioStream);
            clip.start();
            if (isLoop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void paint(Graphics g) {
        bufferImage = createImage(Width, Height);
        screenGraphic = bufferImage.getGraphics();
        screenDraw(screenGraphic);
        g.drawImage(bufferImage, 0, 0, null);
    }

    public void screenDraw(Graphics g) {
        g.drawImage(background, 0, 0, null);
        g.drawImage(mole, 0, 0, null);
        g.setFont(new Font("Arial", Font.PLAIN, 25));
        g.setColor(Color.BLACK);
        g.drawString(Integer.toString(score), 1130, 120);
        g.setFont(new Font("Arial", Font.BOLD, 70));
        g.drawString(key_set, 200, 400);
        this.repaint();
    }

    public void resetGame() {
        set();
        score = 0;
    }

    public static void main(String[] args) {
        new MoleGame();
    }
}
