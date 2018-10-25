package nl.Icaprojecten.ci.Spotitube.DTO;

public class Track {
    private int idTracks;
    private String title;
    private String performer;
    private String album;
    private int playcount;
    private String publicationDate;
    private String offlineplay;
    private int lenght;

    public Track(int idTracks, String title, String performer, String album, int playcount, String publicationDate, String offlineplay, int lenght) {
        this.idTracks = idTracks;
        this.title = title;
        this.performer = performer;
        this.album = album;
        this.playcount = playcount;
        this.publicationDate = publicationDate;
        this.offlineplay = offlineplay;
        this.lenght = lenght;
    }

    public Track() {
    }

    public int getIdTracks() {
        return idTracks;
    }

    public void setIdTracks(int idTracks) {
        this.idTracks = idTracks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public int getPlaycount() {
        return playcount;
    }

    public void setPlaycount(int playcount) {
        this.playcount = playcount;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getOfflineplay() {
        return offlineplay;
    }

    public void setOfflineplay(String offlineplay) {
        this.offlineplay = offlineplay;
    }

    public int getLenght() {
        return lenght;
    }

    public void setLenght(int lenght) {
        this.lenght = lenght;
    }
}
