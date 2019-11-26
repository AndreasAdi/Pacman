/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Enrico
 */
public class Highscore implements Comparable{
    String nama;

    public Highscore(String nama, int score) {
        this.nama = nama;
        this.score = score;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    int score;

    @Override
    public int compareTo(Object o) {
        Highscore h = (Highscore)o;
        return (score-h.getScore())*-1;
    }
}
