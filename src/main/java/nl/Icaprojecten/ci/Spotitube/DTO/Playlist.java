package nl.Icaprojecten.ci.Spotitube.DTO;

import java.util.List;

public class Playlist {

    private int ID;
    private String name;
    private boolean owner;
    private List<Track> tracks;

    public Playlist(int ID, String name, boolean owner, List<Track> tracks) {
        this.ID = ID;
        this.name = name;
        this.owner = owner;
        this.tracks = tracks;
    }

    public Playlist() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOwner() {
        return owner;
    }

    public void setOwner(boolean owner) {
        this.owner = owner;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }
}