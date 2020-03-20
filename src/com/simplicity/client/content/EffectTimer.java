package com.simplicity.client.content;

import com.simplicity.util.SecondsTimer;

public class EffectTimer {
	
	public enum Type {
	    EXPERIENCE,
	    BARRAGE,
	    VENGEANCE,
	    
	    DOUBLE_DONATION,
	    OVERLOAD, 
	    SPECIAL_RESTORE_POTION,
	    SPECIAL_RESTORE_FLASK,
	    PRAYER_RENEWAL,
	    
	    TELE_BLOCK;
	}
	
	private Type type;
    private final int sprite;
    private final SecondsTimer secondsTimer;
    private boolean isItem;

    public EffectTimer(int seconds, int sprite, boolean isItem, Type type) {
        this.secondsTimer = new SecondsTimer(seconds);
        this.sprite = sprite;
        this.isItem = isItem;
        this.type = type;
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
    
    public Type getType() {
    	return type;
    }
}
