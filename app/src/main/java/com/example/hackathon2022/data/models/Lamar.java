package com.example.hackathon2022.data.models;

import java.util.HashMap;
import java.util.Map;

public class Lamar {
    private String LamarKey;
    private String LamarPertanyaan;
    private String LamarFilePath;

    public Lamar(String LamarKey, String LamarPertanyaan, String LamarFilePath) {
        this.LamarKey = LamarKey;
        this.LamarPertanyaan = LamarPertanyaan;
        this.LamarFilePath = LamarFilePath;
    }

    public String getLamarKey() {
        return LamarKey;
    }


    public String getLamarPertanyaan() {
        return LamarPertanyaan;
    }

    public String getLamarFilePath() {
        return LamarFilePath;
    }


    public Map<String, Object> toMap(){
        Map<String, Object> data = new HashMap<>();
        data.put("pertanyaan", LamarPertanyaan);
        data.put("filepath", LamarFilePath);
        return data;
    }
}
