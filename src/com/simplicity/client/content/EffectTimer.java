package com.simplicity.client.content;

import com.simplicity.util.SecondsTimer;

public class EffectTimer {
	
	public enum Type {
		DOUBLE_DONATION(1057),
		EXPERIENCE(15389, true),
		BARRAGE(1307),
	    VENGEANCE(1306),
	    TELE_BLOCK(1333),
	    
	    OVERLOAD(15332, true),
	    OVERLOAD_FLASK(14301, true),
	    ANTIFIRE_POTION(2452, true),
	    SUPER_ANTIFIRE_POTION(15304, true),
	    ANTIPOISON_POTION(2446, true),
	    SUPER_ANTIPOISON_POTION(2448, true),
	    SPECIAL_RESTORE_POTION(15300, true),
	    SPECIAL_RESTORE_FLASK(14385, true),
	    PRAYER_RENEWAL(21630, true),
	    ANTI_VENOM(42905, true),
	    FAMILIAR_SPECIAL(327),
	    DREAM_POTION(11154, true),
	    
	    ;
		
	    private int sprite;
	    
	    private boolean isItem;
	    
	    Type(int sprite) {
	    	this(sprite, false);
	    }

	    Type(int sprite, boolean isItem) {
	        this.sprite = sprite;
	        this.isItem = isItem;
	    }
	    
	    public int getSprite() {
	        return sprite;
	    }
	    
	    public boolean isItem() {
	    	return isItem;
	    }
	}
	
	private Type type;
    private final SecondsTimer secondsTimer;

    public EffectTimer(int seconds, Type type) {
        this.secondsTimer = new SecondsTimer(seconds);
        this.type = type;
    }

    public void setSeconds(int seconds) {
        this.secondsTimer.start(seconds);
    }

    public SecondsTimer getSecondsTimer() {
        return secondsTimer;
    }
    
    public Type getType() {
    	return type;
    }
}
