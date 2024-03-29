/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Andreas
 */

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Logger;
import javax.swing.Timer;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JOptionPane;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Player extends Rectangle {

    public boolean right, left, up, down, awal = true, buka = false;
    private int speed = 4;
    public Image img;
    public Timer t;
    public static boolean cekstoping;
    public static boolean cekconfuse;
    int Waktu = 0;
    int Waktu2 = 0;
    public static boolean cekmenang = false;
    public static AudioStream sound; 
    public Level level; 
    public static ArrayList <Highscore> highscore= new ArrayList<>();
    public static ArrayList <Highscore> highscoreeasy= new ArrayList<>();
    public static ArrayList <Highscore> highscoremedium= new ArrayList<>();
    public static ArrayList <Highscore> highscorehard= new ArrayList<>();
    public static String namaplayer;
    public static String diff = "easy";
    public Player(int x, int y) {
        setBounds(x, y, 32, 32);
    }

    public void tick() {
        level = Game.level;
        cekgerak();
        cek_makan();
        cek_stoping();
        cek_Confuse();
        cek_menang();
        cek_kalah();


    }
    
    void cek_menang(){
            if (level.makanan.size() == 0 && cekmenang == false) {
           // win reset permainan
            Game.player = new Player(0, 0);
            Game.level = new Level("/Map/map_win.png");
//            Game.game.start();
            //Game.game.stop();
            Musuh.spd=0;
            JOptionPane.showMessageDialog(null, "Menang");
        
//           Diffuclty d = new Diffuclty();
//           d.setVisible(true);
//           Game.frame.setVisible(false);
//           Game.game.stop();
           musicmenang();
           cekmenang = true;
           namaplayer= JOptionPane.showInputDialog("Nama Player");
                if (diff.equals("easy")) {
                    add_scoreeasy();
                }
                if (diff.equals("medium")) {
                    add_scoremedium();
                }
                if (diff.equals("hard")) {
                    add_scorehard();
                }
                save();
        }
    }
    
    void cek_kalah(){
            // Pengecekan Kalah
        for (int i = 0; i < level.musuh.size(); i++) {
            if (this.intersects(level.musuh.get(i))) {
                //Game.player = new Player(0, 0);
                //Game.level = new Level("/Map/map.png");
                AudioPlayer.player.stop(Game.sound);
                musickalah();
                namaplayer= JOptionPane.showInputDialog("Nama Player");
                if (diff.equals("easy")) {
                    add_scoreeasy();
                }
                if (diff.equals("medium")) {
                    add_scoremedium();
                }
                if (diff.equals("hard")) {
                    add_scorehard();
                }
                int retry = JOptionPane.showConfirmDialog(null, "Retry", "Game Over", JOptionPane.YES_NO_OPTION);
                
                if (retry == JOptionPane.YES_OPTION) {
                    Game.player = new Player(0, 0);
                    //Game.level = new Level("/Map/map.png");
                    Diffuclty d = new Diffuclty();
                    d.setVisible(true);
                    Game.frame.setVisible(false);
                    Game.load_song("xx.wav");
                    AudioPlayer.player.start(Game.sound);
                    Game.Score=0;
                } else if (retry == JOptionPane.NO_OPTION) {
                    System.exit(1);
                }
                save();
            }
        }
    }
    public void add_scoreeasy() {
        highscoreeasy.add(new Highscore(namaplayer, Game.Score));
        Collections.sort(highscoreeasy);
    }
    public void add_scoremedium() {
        highscoremedium.add(new Highscore(namaplayer, Game.Score));
        Collections.sort(highscoremedium);
    }
    public void add_scorehard() {
        highscorehard.add(new Highscore(namaplayer, Game.Score));
        Collections.sort(highscorehard);
    }
    
    void cekgerak(){
            if (left && canmove(x - speed, y)) {
            x -= speed;
        }
        if (right && canmove(x + speed, y)) {
            x += speed;
        }
        if (up && canmove(x, y - speed)) {
            y -= speed;
        }
        if (down && canmove(x, y + speed)) {
            y += speed;
        }
    }
    void cek_stoping(){
            //powerup slowing
        for (int i = 0; i < level.slowing.size(); i++) {

            t = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cekstoping == true) {

                        System.out.println(Waktu);
                        for (Musuh M : level.musuh) {
                            if (Waktu <= 0) {
                                M.spd = 0;
                                Musuh.pathimage = "Src\\Char\\diam.png";
                            } else if (Waktu == 5) {
                                Musuh.pathimage ="Src\\Char\\ghost_0_0.png";
                                M.spd = 2;
                                t.stop();
                                
                                cekstoping = false;
                            }
                        }
                        Waktu++;
                    }
                }

            });
            if (this.intersects(level.slowing.get(i))) {
                level.slowing.remove(i);
                //Game.Score +=10;
                musicpowerup();
                Waktu = 0;
                cekstoping = true;
                t.start();
                break;
            }
            
        }
    }
    
    
    
        void cek_Confuse(){
            //powerup slowing
        for (int i = 0; i < level.confuse.size(); i++) {

            t = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (cekconfuse == true) {

                        System.out.println(Waktu2);
                        for (Musuh M : level.musuh) {
                            if (Waktu2 <= 0) {
                              Musuh.pathimage = "Src\\Char\\bingung.png";
                               M.setState(0);
                            } else if (Waktu2 == 10) {
                              Musuh.pathimage ="Src\\Char\\ghost_0_0.png";
                               M.setState(1);
                                t.stop();

                                cekconfuse = false;
                            }
                        }
                        Waktu2++;
                    }
                }

            });
            if (this.intersects(level.confuse.get(i))) {
                level.confuse.remove(i);
                //Game.Score +=10;
                musicpowerup();
                Waktu2 = 0;
                cekconfuse = true;
                t.start();
                break;
            }
            
        }
    }
    
    
    void cek_makan(){
            // makan
        for (int i = 0; i < level.makanan.size(); i++) {
            if (this.intersects(level.makanan.get(i))) {
                level.makanan.remove(i);
                Game.Score += 10;
                musicmakan();
                break;
            }
        }
    }
    void sort(){
        Collections.sort(highscore);
    }
    void musickalah(){

        AudioInputStream in= null;
        try {
            File soundfile= new File("die.wav");
            in = AudioSystem.getAudioInputStream(soundfile);
            Clip c=AudioSystem.getClip();
            c.open(in);
            c.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
    
        void musicmenang(){
        AudioPlayer.player.stop(Game.sound);
        AudioInputStream in= null;
        try {
            File soundfile= new File("Win.wav");
            in = AudioSystem.getAudioInputStream(soundfile);
            Clip c=AudioSystem.getClip();
            c.open(in);
            c.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
    
    void musicmakan(){
            AudioInputStream in= null;
        try {
            File soundfile= new File("eating.wav");
            in = AudioSystem.getAudioInputStream(soundfile);
            Clip c=AudioSystem.getClip();
            c.open(in);
            c.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
    
    void musicpowerup(){
                AudioInputStream in= null;
        try {
            File soundfile= new File("powerup.wav");
            in = AudioSystem.getAudioInputStream(soundfile);
            Clip c=AudioSystem.getClip();
            c.open(in);
            c.start();
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
                Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
            
    
    public boolean canmove(int nextx, int nexy) {

        Rectangle bounds = new Rectangle(nextx, nexy, width, height);
        Level level = Game.level;

        for (int xx = 0; xx < level.tiles.length; xx++) {
            for (int yy = 0; yy < level.tiles[0].length; yy++) {
                if (level.tiles[xx][yy] != null) {
                    if (bounds.intersects(level.tiles[xx][yy])) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
    public void save() {
        try {
            // TODO add your handling code here:
            FileOutputStream fout = new FileOutputStream("save.pacman");
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            oos.writeObject(highscoreeasy);
            oos.writeObject(highscoremedium);
            oos.writeObject(highscorehard);
            oos.close();
            fout.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    public void load() {
        try {
            // TODO add your handling code here:
            FileInputStream fin = new FileInputStream("save.pacman");
            ObjectInputStream ois = new ObjectInputStream(fin);
            highscoreeasy = (ArrayList<Highscore>)ois.readObject();
            highscoremedium = (ArrayList<Highscore>)ois.readObject();
            highscorehard = (ArrayList<Highscore>)ois.readObject();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Player.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    public void render(Graphics g) {
        try {
            if (awal) {
                img = ImageIO.read(new File("Src\\Char\\pacmankanan.png"));
            } else {
                if (buka == false) {
                   img = ImageIO.read(new File("Src\\Char\\pacmannutup.png"));     
                   buka=true;
                } else {
                    if (left) {
                        img = ImageIO.read(new File("Src\\Char\\pacmankiri.png"));
                    }
                    if (right) {
                        img = ImageIO.read(new File("Src\\Char\\pacmankanan.png"));
                    }
                    if (up) {
                        img = ImageIO.read(new File("Src\\Char\\pacmanatas.png"));
                    }
                    if (down) {
                        img = ImageIO.read(new File("Src\\Char\\pacmanbawah.png"));
                    }
                    buka=false;
                }
                awal = false;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        //g.setColor(Color.yellow);
        //g.fillRect(x, y, width, height);

        g.drawImage(img, x, y, 32, 32, null);

    }
}
