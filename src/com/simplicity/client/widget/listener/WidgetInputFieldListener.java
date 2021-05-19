package com.simplicity.client.widget.listener;

public interface WidgetInputFieldListener {
	
	void onUpdateInputField(int widgetId, String text);
	
	void onResetInputField(int widgetId);
	
	void onInputFocus(int widgetId);
	
}
