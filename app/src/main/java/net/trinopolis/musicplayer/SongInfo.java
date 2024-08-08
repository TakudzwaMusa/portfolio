package net.trinopolis.musicplayer;

import java.io.File;

public class SongInfo {
    private String songName;
    private String artistName;
    private File file;

    public SongInfo(String songName, String artistName, File file) {
        this.songName = songName;
        this.artistName = artistName;
        this.file = file;
    }

    public String getSongName() {
        return songName;
    }

    public String getArtistName() {
        return artistName;
    }

    public File getFile() {
        return file;
    }
}

