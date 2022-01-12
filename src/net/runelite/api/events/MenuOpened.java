package net.runelite.api.events;
/*
 * Copyright (c) 2018, Kamiel
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import net.runelite.api.MenuEntry;

import java.util.Iterator;

/**
 * An event where a menu has been opened.
 */
@Data
public class MenuOpened
{
	/**
	 * The menu entries in the newly opened menu.
	 * <p>
	 * The entries in this menu are reversed, the last entry in the
	 * array will appear first (at the top) in the opened menu.
	 */
	private MenuEntry[] menuEntries;

	/**
	 * This should be set to true if anything about the menu
	 * in menuEntries is changed, so the changes can be
	 * propagated through to the client.
	 */
	@Setter(AccessLevel.NONE)
	private boolean modified;
	/**
	 * Gets the entry that will be displayed first in the menu.
	 *
	 * @return the first entry
	 */
	public MenuEntry getFirstEntry()
	{
		if (menuEntries.length > 0)
		{
			return menuEntries[menuEntries.length - 1];
		}

		return null;
	}
	public void setModified()
	{
		this.modified = true;
	}
	public Iterator<MenuEntry> iterator()
	{
		return new Iterator<MenuEntry>()
		{
			int index = 0;

			@Override
			public boolean hasNext()
			{
				return index < menuEntries.length;
			}

			@Override
			public MenuEntry next()
			{
				return menuEntries[index++];
			}
		};
	}
}
