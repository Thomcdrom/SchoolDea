package nl.Icaprojecten.ci.Spotitube.DTO;

import java.util.ArrayList;

public class Playlist implements IDTO {

    private int id;
    private String name;
    private boolean owner;
    private ArrayList<Track> tracks;

    public Playlist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }
}
