package nl.Icaprojecten.ci.Spotitube.DTO.Collections;

import nl.Icaprojecten.ci.Spotitube.DTO.Playlist;

import java.util.ArrayList;

public class Playlists {
    private int lenght;
    private ArrayList<Playlist> playlists;

    public Playlists() {
    }

    public Playlists(int lenght, ArrayList<Playlist> playlists) {
        this.lenght = lenght;
        this.playlists = playlists;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
}
