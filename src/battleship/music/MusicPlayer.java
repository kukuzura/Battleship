package battleship.music;

public class MusicPlayer implements Runnable{

    @Override
    public void run() {
        Sound music=new Sound("hanz_zimmer_-_hes_a_pirate_(zf.fm).wav");
        music.play();
    }
}
