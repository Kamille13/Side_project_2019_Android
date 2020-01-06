package com.example.sideproject2019;

import com.example.sideproject2019.model.Position;

public class PosSingleton {
    private static PosSingleton instance;
    private Position position;

    private PosSingleton() {
    }

    public static PosSingleton getInstance() {
        if (instance == null) {
            instance = new PosSingleton();
        }
        return instance;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
