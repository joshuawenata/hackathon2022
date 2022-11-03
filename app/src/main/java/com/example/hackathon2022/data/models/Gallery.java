package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class Gallery {

    private String GalleryKey;
    private String filepath;

    public Gallery(String GalleryKey, String filepath) {
        this.GalleryKey = GalleryKey;
        this.filepath = filepath;
    }

    public String getGalleryKey() {
        return GalleryKey;
    }

    public void setGalleryKey(String galleryKey) {
        GalleryKey = galleryKey;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("GalleryKey", GalleryKey);
        data.put("filepath", filepath);
        return data;
    }
}
