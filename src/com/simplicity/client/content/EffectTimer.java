package com.simplicity.client.content;

import com.simplicity.util.SecondsTimer;

public class EffectTimer {

    private final int sprite;
    private final SecondsTimer secondsTimer;
    private boolean isItem;

    public EffectTimer(int seconds, int sprite, boolean isItem) {
        this.secondsTimer = new SecondsTimer(seconds);
        this.sprite = sprite;
        this.isItem = isItem;
    }

    public int getSprite() {
        return sprite;
    }

    public void setSeconds(int seconds) {
        this.secondsTimer.start(seconds);
    }

    public SecondsTimer getSecondsTimer() {
        return secondsTimer;
    }
    
    public boolean isItem() {
    	return isItem;
    }
}
