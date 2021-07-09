package com.simplicity.client.widget;

import com.simplicity.client.RSInterface;
import com.simplicity.client.widget.listener.WidgetButtonListener;
import com.simplicity.client.widget.listener.WidgetContainerListener;

public class NewAchievements extends CustomWidget implements WidgetButtonListener, WidgetContainerListener {

	public NewAchievements() {
		super(96000);
	}
	//
	@Override
	public void init() {
		addButtonListener(this);
		addContainerListener(this);
		int x = 14, y = 15;
		add(addClosableWindow(484, 304, false, "Master Achievements"), x, y);
		add(achievementList(96100, 1), 6+x, 63+y);
		achievementList(96205, 2);
		achievementList(96310, 3);
		achievementList(96415, 4);
		add(addSpriteRepeatY(1277, 264), 154+x, 35+y);
		String[] pages = {"PVP", "PVM", "Skilling", "Other"};
		int[] icons = {1859, 1860, 1861, 1862};
		for (int i = 0; i < 4; ++i) {
			add(addConfigButton(pages[i], 1849, 1850, 5, i, 1631), 6 + x + (i * 37), 35+y);
			add(addSprite(icons[i]), 6 + x + (i * 37) + 8, 40 + y);
		}
		
		add(addRectangle(307, 40, 0x897661, 0, false), 164+x, 40+y);
		add(addRectangle(305, 38, 0, 200, true), 165+x, 41+y);
		add(addRectangle(307, 82, 0x897661, 0, false), 164+x, 85+y);
		add(addRectangle(305, 80, 0, 200, true), 165+x, 86+y);
		
		add(addProgressBar(312, 26), 165+x, 174+y);
		
		add(addSpriteRepeatX(1276, 318), 160+x, 206+y);
		
		add(addHoverButton(1855, "Previous Achievement"), 172+x, 49+y);
		add(addHoverButton(1856, "Next Achievement"), 435+x, 49+y);
		
		add(addCenteredText("Achievement Name", 2, 0xffb000), 317+x, 52+y);
		
		for (int i = 0; i < 6; ++i) {
			add(addText("", 1, 0xffb000), 167+x, 88+y+(i*12));
		}
		
		add(addSprite(1857), 165+x, 217+y);
		add(addText("Reward Value: 69", 2, 0xffb000), 183+x, 216+y);
		add(addRectangle(197, 49, 0x897661, 0, false), 164+x, 235+y);
		add(addRectangle(195, 47, 0, 200, true), 165+x, 236+y);
		add(addItemContainer(4, 1, 14, 1, null, ""), 174+x, 244+y);
		RSInterface.fill(id - 1);

		add(addDynamicButton("Claim", 2, 0xffb000, 5, 0, 102, 49), 369 + x, 235 + y);
		add(addSprite(1794), 372+x, 243+y);
	}

	@Override
	public void onContainerUpdate(int id) {
		
	}

	@Override
	public boolean onClick(int id) {
		switch(id) {
		case 96012:
			RSInterface.interfaceCache[96000].children[1] = 96100;
			return true;
		case 96014:
			RSInterface.interfaceCache[96000].children[1] = 96205;
			return true;
		case 96016:
			RSInterface.interfaceCache[96000].children[1] = 96310;
			return true;
		case 96018:
			RSInterface.interfaceCache[96000].children[1] = 96415;
			return true;
		}
		return false;
	}

	@Override
	public String getName() {
		return "";
	}
	
	
	public static RSInterface achievementList(int id, int page) {
		RSInterface tab = RSInterface.addInterface(id++);
		tab.width = 132;
		tab.height = 235;
		tab.scrollMax = 1300;
		RSInterface.addSprite(96098, 1853);
		RSInterface.addSprite(96099, 1854);
		tab.totalChildren(200);
		for (int i = 0; i < 100; ++i) {
			RSInterface.addClickableText(++id, "", "Select", RSInterface.fonts, 0, 0xff981f, false, true, 132);
			tab.child(i, i % 2 == 0 ? 96098 : 96099, 0, i * 13);
			tab.child(i + 100, id, 2, 2 + (i * 13));
		}
		return tab;
	}
	
}
