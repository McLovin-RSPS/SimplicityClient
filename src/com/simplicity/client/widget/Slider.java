package com.simplicity.client.widget;

import com.simplicity.client.Client;
import com.simplicity.client.RSInterface;
import com.simplicity.client.Sprite;
import com.simplicity.client.widget.settings.Setting;
import com.simplicity.client.widget.settings.Settings;
import com.simplicity.util.MiscUtils;

public class Slider {

    /**
     * The inset (in pixels) to start/end the actual slider region on the slider sprite (makes edges of slider not part of dragging region)
     */
    private static final int SLIDER_INSET = 13;

    public final static int ZOOM = 1;
    public final static int BRIGHTNESS = 2;
    public final static int MUSIC = 3;
    public final static int SOUND = 4;
    private final double minValue, maxValue, length;
    private final Sprite[] images = new Sprite[2];
    private int position = 86;

    public double getValue() {
        return value;
    }

    private double value;

    private static RSInterface selectedSlider;
    private static int selectedDrawX = -1, selectedClickX = -1;

    public static RSInterface getSelectedSlider() {
        return selectedSlider;
    }

    public static void setSelectedSlider(RSInterface widget) {
        selectedSlider = widget;
    }

    public static int getSelectedDrawX() {
        return selectedDrawX;
    }

    public static void setSelectedDrawX(int drawX) {
        selectedDrawX = drawX;
    }
    public static int getSelectedClickX() {
        return selectedClickX;
    }

    public static void setSelectedClickX(int clickX) {
        selectedClickX = clickX;
    }

    public static void setSelected(RSInterface widget, int drawX, int clickX) {
        RSInterface selected = getSelectedSlider();

        if (widget == null && selected != null) {
            if (selected.contentType == ZOOM) {
                Settings.set(Setting.ZOOM, selected.slider.value, true);
            }
        }

        setSelectedSlider(widget);
        setSelectedDrawX(drawX);
        setSelectedClickX(clickX);
    }

    public Slider(Sprite icon, Sprite background, double minimumValue, double maximumValue) {
        this(icon, background, minimumValue, minimumValue, maximumValue);
    }
    
    public Slider(Sprite icon, Sprite background, double value, double minimumValue, double maximumValue) {
        this.images[0] = icon;
        this.images[1] = background;
        this.value = value;
        this.minValue = minimumValue;
        this.maxValue = maximumValue;
        this.length = this.images[1].myWidth - (SLIDER_INSET * 2);
    }

    public static void handleDragging() {
        RSInterface widget = selectedSlider;
        Slider slider = widget.slider;
        int dragX = Client.instance.mouseX - getSelectedClickX();
        int positionX = getSelectedClickX() - getSelectedDrawX();

        slider.position = MiscUtils.ensureRange(positionX + dragX - SLIDER_INSET, 0, (int) slider.length);

        double val = slider.minValue + (slider.position / slider.length) * (slider.maxValue - slider.minValue);
        if (val < slider.minValue) {
            val = slider.minValue;
        } else if (val > slider.maxValue) {
            val = slider.maxValue;
        }
        double oldVal = slider.value;
        slider.value = val;

        if (val != oldVal) {
            handleContent(widget);
        }
    }

    public void draw(int x, int y) {
        images[1].drawSprite(x, y);
        images[0].drawSprite(x + SLIDER_INSET - (images[0].myWidth / 2) + position, y - images[0].myHeight / 2 + images[1].myHeight / 2);
    }

    private static void handleContent(RSInterface widget) {
        int contentType = widget.contentType;
        Slider slider = widget.slider;
        switch (contentType) {
            case ZOOM:
                Client.cameraZoom = -(int) slider.value;
                break;
        }
    }

    public double getPercentage() {
        return ((position / length) * 100);
    }

    public void setValue(double value) {
        if (value < minValue) {
            value = minValue;
        } else if (value > maxValue) {
            value = maxValue;
        }

        this.value = value;
        double shift = 1 - ((value - minValue) / (maxValue - minValue));

        position = (int) (length * shift);
    }

}
