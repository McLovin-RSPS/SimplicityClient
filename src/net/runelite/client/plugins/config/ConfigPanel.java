/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
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
package net.runelite.client.plugins.config;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import com.google.common.primitives.Ints;

import lombok.extern.slf4j.Slf4j;
import net.runelite.client.config.ChatColorConfig;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigDescriptor;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigItemDescriptor;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.config.Keybind;
import net.runelite.client.config.Range;
import net.runelite.client.config.RuneLiteConfig;
import net.runelite.client.config.Units;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.plugins.PluginInstantiationException;
import net.runelite.client.plugins.PluginManager;
import net.runelite.client.ui.ColorScheme;
import net.runelite.client.ui.DynamicGridLayout;
import net.runelite.client.ui.PluginPanel;
import net.runelite.client.ui.components.ComboBoxListRenderer;
import net.runelite.client.ui.components.IconButton;
import net.runelite.client.ui.components.IconTextField;
import net.runelite.client.util.SwingUtil;

@Slf4j
public class ConfigPanel extends PluginPanel
{
	private static final int SPINNER_FIELD_WIDTH = 6;
	private static final int SCROLLBAR_WIDTH = 17;
	private static final int OFFSET = 6;
	private static final ImageIcon BACK_ICON;
	private static final ImageIcon BACK_ICON_HOVER;
	private static final ImageIcon SEARCH;

	private static final String RUNELITE_GROUP_NAME = RuneLiteConfig.class.getAnnotation(ConfigGroup.class).value();
	private static final String PINNED_PLUGINS_CONFIG_KEY = "pinnedPlugins";
	private static final String RUNELITE_PLUGIN = "RuneLite";
	private static final String CHAT_COLOR_PLUGIN = "Chat Color";
	private static final Splitter COMMA_SPLITTER = Splitter.on(',');

	private final PluginManager pluginManager;
	private final ConfigManager configManager;
	private final ScheduledExecutorService executorService;
	private final RuneLiteConfig runeLiteConfig;
	private final ChatColorConfig chatColorConfig;
	private final IconTextField searchBar = new IconTextField();
	private final List<PluginListItem> pluginList = new ArrayList<>();

	private final JPanel topPanel;
	private final JPanel mainPanel;
	private final JScrollPane scrollPane;

	private boolean showingPluginList = true;
	private int scrollBarPosition = 0;

	static
	{
		try
		{
			synchronized (ImageIO.class)
			{
				BufferedImage backIcon = ImageIO.read(ConfigPanel.class.getResourceAsStream("config_back_icon.png"));
				BACK_ICON = new ImageIcon(backIcon);
				BACK_ICON_HOVER = new ImageIcon(SwingUtil.grayscaleOffset(backIcon, -100));
				SEARCH = new ImageIcon(ImageIO.read(IconTextField.class.getResourceAsStream("search.png")));
			}
		}
		catch (IOException e)
		{
			throw new RuntimeException(e);
		}
	}

	ConfigPanel(PluginManager pluginManager, ConfigManager configManager, ScheduledExecutorService executorService,
		RuneLiteConfig runeLiteConfig, ChatColorConfig chatColorConfig)
	{
		super(false);
		this.pluginManager = pluginManager;
		this.configManager = configManager;
		this.executorService = executorService;
		this.runeLiteConfig = runeLiteConfig;
		this.chatColorConfig = chatColorConfig;

		searchBar.setIcon(SEARCH);
		searchBar.setPreferredSize(new Dimension(PluginPanel.PANEL_WIDTH - 20, 30));
		searchBar.setBackground(ColorScheme.DARKER_GRAY_COLOR);
		searchBar.setHoverBackgroundColor(ColorScheme.DARK_GRAY_HOVER_COLOR);
		searchBar.getDocument().addDocumentListener(new DocumentListener()
		{
			@Override
			public void insertUpdate(DocumentEvent e)
			{
				onSearchBarChanged();
			}

			@Override
			public void removeUpdate(DocumentEvent e)
			{
				onSearchBarChanged();
			}

			@Override
			public void changedUpdate(DocumentEvent e)
			{
				onSearchBarChanged();
			}
		});

		setLayout(new BorderLayout());
		setBackground(ColorScheme.DARK_GRAY_COLOR);

		topPanel = new JPanel();
		topPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		topPanel.setLayout(new BorderLayout(0, OFFSET));
		add(topPanel, BorderLayout.NORTH);

		mainPanel = new FixedWidthPanel();
		mainPanel.setBorder(new EmptyBorder(8, 10, 10, 10));
		mainPanel.setLayout(new DynamicGridLayout(0, 1, 0, 5));
		mainPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

		JPanel northPanel = new FixedWidthPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.add(mainPanel, BorderLayout.NORTH);

		scrollPane = new JScrollPane(northPanel);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);

		initializePluginList();
		refreshPluginList();
	}

	private void initializePluginList()
	{
		final List<String> pinnedPlugins = getPinnedPluginNames();

		// populate pluginList with all non-hidden plugins
		pluginManager.getPlugins().stream()
			.filter(plugin -> !plugin.getClass().getAnnotation(PluginDescriptor.class).hidden())
			.forEach(plugin ->
			{
				final PluginDescriptor descriptor = plugin.getClass().getAnnotation(PluginDescriptor.class);
				final Config config = pluginManager.getPluginConfigProxy(plugin);
				final ConfigDescriptor configDescriptor = config == null ? null : configManager.getConfigDescriptor(config);

				final PluginListItem listItem = new PluginListItem(this, plugin, descriptor, config, configDescriptor);
				listItem.setPinned(pinnedPlugins.contains(listItem.getName()));
				pluginList.add(listItem);
			});

		// add special entries for core client configurations
		final PluginListItem runeLite = new PluginListItem(this, runeLiteConfig,
			configManager.getConfigDescriptor(runeLiteConfig),
			RUNELITE_PLUGIN, "RuneLite client settings", "client");
		runeLite.setPinned(pinnedPlugins.contains(RUNELITE_PLUGIN));
		pluginList.add(runeLite);

		/*final PluginListItem chatColor = new PluginListItem(this, chatColorConfig,
			configManager.getConfigDescriptor(chatColorConfig),
			CHAT_COLOR_PLUGIN, "Recolor chat text", "colour", "messages");
		chatColor.setPinned(pinnedPlugins.contains(CHAT_COLOR_PLUGIN));
		pluginList.add(chatColor);*/

		pluginList.sort(Comparator.comparing(PluginListItem::getName));
	}

	void refreshPluginList()
	{
		// update enabled / disabled status of all items
		pluginList.forEach(listItem ->
		{
			final Plugin plugin = listItem.getPlugin();
			if (plugin != null)
			{
				listItem.setPluginEnabled(pluginManager.isPluginEnabled(plugin));
			}
		});

		if (showingPluginList)
		{
			openConfigList();
		}
	}

	void openConfigList()
	{
		if (showingPluginList)
		{
			scrollBarPosition = scrollPane.getVerticalScrollBar().getValue();
		}

		showingPluginList = true;

		topPanel.removeAll();
		mainPanel.removeAll();

		JLabel title = new JLabel("Configuration", SwingConstants.LEFT);
		title.setForeground(Color.WHITE);

		topPanel.add(title, BorderLayout.NORTH);
		topPanel.add(searchBar, BorderLayout.CENTER);

		onSearchBarChanged();
		searchBar.requestFocusInWindow();
		validate();
		scrollPane.getVerticalScrollBar().setValue(scrollBarPosition);
	}

	private void onSearchBarChanged()
	{
		final String text = searchBar.getText();

		pluginList.forEach(mainPanel::remove);

		showMatchingPlugins(true, text);
		showMatchingPlugins(false, text);

		revalidate();
	}

	private void showMatchingPlugins(boolean pinned, String text)
	{
		if (text.isEmpty())
		{
			pluginList.stream().filter(item -> pinned == item.isPinned()).forEach(mainPanel::add);
			return;
		}

		final String[] searchTerms = text.toLowerCase().split(" ");
		pluginList.forEach(listItem ->
		{
			if (pinned == listItem.isPinned() && listItem.matchesSearchTerms(searchTerms))
			{
				mainPanel.add(listItem);
			}
		});
	}

	void openGroupConfigPanel(PluginListItem listItem, Config config, ConfigDescriptor cd)
	{
		showingPluginList = false;

		scrollBarPosition = scrollPane.getVerticalScrollBar().getValue();
		topPanel.removeAll();
		mainPanel.removeAll();

		final IconButton topPanelBackButton = new IconButton(BACK_ICON, BACK_ICON_HOVER);
		topPanelBackButton.setPreferredSize(new Dimension(22, 0));
		topPanelBackButton.setBorder(new EmptyBorder(0, 0, 0, 5));
		topPanelBackButton.addActionListener(e -> openConfigList());
		topPanelBackButton.setToolTipText("Back");
		topPanel.add(topPanelBackButton, BorderLayout.WEST);

		topPanel.add(listItem.createToggleButton(), BorderLayout.EAST);

		String name = listItem.getName();
		JLabel title = new JLabel(name);
		title.setForeground(Color.WHITE);
		title.setToolTipText("<html>" + name + ":<br>" + listItem.getDescription() + "</html>");
		topPanel.add(title);

		for (ConfigItemDescriptor cid : cd.getItems())
		{
			if (cid == null)
			{
				continue;
			}

			Boolean unhide = cid.getItem().hidden();
			Boolean hide = !cid.getItem().hide().isEmpty();

			if (unhide || hide)
			{
				boolean show = false;

				List<String> itemHide = Splitter
						.onPattern("\\|\\|")
						.trimResults()
						.omitEmptyStrings()
						.splitToList(String.format("%s || %s", cid.getItem().unhide(), cid.getItem().hide()));

				for (ConfigItemDescriptor cid2 : cd.getItems())
				{
					if (itemHide.contains(cid2.getItem().keyName()))
					{
						if (cid2.getType() == boolean.class)
						{
							show = Boolean.parseBoolean(configManager.getConfiguration(cd.getGroup().value(), cid2.getItem().keyName()));
						}
						else if (cid2.getType().isEnum())
						{
							@SuppressWarnings("unchecked") Class<? extends Enum> type = (Class<? extends Enum>) cid2.getType();
							try
							{
								@SuppressWarnings("unchecked") Enum selectedItem = Enum.valueOf(type, configManager.getConfiguration(cd.getGroup().value(), cid2.getItem().keyName()));
								if (!cid.getItem().unhideValue().equals(""))
								{
									List<String> unhideValue = Splitter
											.onPattern("\\|\\|")
											.trimResults()
											.omitEmptyStrings()
											.splitToList(cid.getItem().unhideValue());

									show = unhideValue.contains(selectedItem.toString());
								}
								else if (!cid.getItem().hideValue().equals(""))
								{
									List<String> hideValue = Splitter
											.onPattern("\\|\\|")
											.trimResults()
											.omitEmptyStrings()
											.splitToList(cid.getItem().hideValue());

									show = !hideValue.contains(selectedItem.toString());
								}
							}
							catch (IllegalArgumentException ex)
							{
								log.info("So bad, so sad: {}", ex.toString());
							}
						}
					}

					if (show)
					{
						break;
					}
				}

				if ((unhide && !show) || (hide && show))
				{
					continue;
				}
			}

			JPanel item = new JPanel();
			item.setLayout(new BorderLayout());
			item.setMinimumSize(new Dimension(PANEL_WIDTH, 0));
			name = cid.getItem().name();
			JLabel configEntryName = new JLabel(name);
			configEntryName.setForeground(Color.WHITE);
			configEntryName.setToolTipText("<html>" + name + ":<br>" + cid.getItem().description() + "</html>");
			item.add(configEntryName, BorderLayout.CENTER);

			if (cid.getType() == boolean.class)
			{
				JCheckBox checkbox = new JCheckBox();
				checkbox.setBackground(ColorScheme.LIGHT_GRAY_COLOR);
				checkbox.setSelected(Boolean.parseBoolean(configManager.getConfiguration(cd.getGroup().value(), cid.getItem().keyName())));
				checkbox.addActionListener(ae -> changeConfiguration(listItem, config, checkbox, cd, cid));

				item.add(checkbox, BorderLayout.EAST);
			}

			if (cid.getType() == int.class)
			{
				int value = Integer.parseInt(configManager.getConfiguration(cd.getGroup().value(), cid.getItem().keyName()));
				
				Range range = cid.getRange();
				int min = 0, max = Integer.MAX_VALUE;
				if (range != null)
				{
					min = range.min();
					max = range.max();
				}
				
				// Config may previously have been out of range
				value = Ints.constrainToRange(value, min, max);
				

				SpinnerModel model = new SpinnerNumberModel(value, min, max, 1);
				JSpinner spinner = new JSpinner(model);
				Component editor = spinner.getEditor();
				JFormattedTextField spinnerTextField = ((JSpinner.DefaultEditor) editor).getTextField();
				spinnerTextField.setColumns(SPINNER_FIELD_WIDTH);
				spinner.addChangeListener(ce -> changeConfiguration(listItem, config, spinner, cd, cid));
				
				Units units = cid.getUnits();
				if (units != null)
				{
					spinnerTextField.setFormatterFactory(new UnitFormatterFactory(units));
				}

				item.add(spinner, BorderLayout.EAST);
			}

			if (cid.getType() == String.class)
			{
				JTextArea textField = new JTextArea();
				textField.setLineWrap(true);
				textField.setWrapStyleWord(true);
				textField.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
				textField.setText(configManager.getConfiguration(cd.getGroup().value(), cid.getItem().keyName()));

				textField.addFocusListener(new FocusAdapter()
				{
					@Override
					public void focusLost(FocusEvent e)
					{
						changeConfiguration(listItem, config, textField, cd, cid);
					}
				});

				item.add(textField, BorderLayout.SOUTH);
			}

			if (cid.getType() == Color.class)
			{
				String existing = configManager.getConfiguration(cd.getGroup().value(), cid.getItem().keyName());

				Color existingColor;
				JButton colorPicker;

				if (existing == null)
				{
					existingColor = Color.BLACK;
					colorPicker = new JButton("Pick a color");
				}
				else
				{
					existingColor = Color.decode(existing);
					colorPicker = new JButton("#" + Integer.toHexString(existingColor.getRGB()).substring(2).toUpperCase());
				}

				colorPicker.setFocusable(false);
				colorPicker.setBackground(existingColor);
				colorPicker.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent e)
					{
						final JFrame parent = new JFrame();
						JColorChooser jColorChooser = new JColorChooser(existingColor);
						jColorChooser.getSelectionModel().addChangeListener(e1 ->
						{
							colorPicker.setBackground(jColorChooser.getColor());
							colorPicker.setText("#" + Integer.toHexString(jColorChooser.getColor().getRGB()).substring(2).toUpperCase());
						});
						parent.addWindowListener(new WindowAdapter()
						{
							@Override
							public void windowClosing(WindowEvent e)
							{
								changeConfiguration(listItem, config, jColorChooser, cd, cid);
							}
						});
						parent.add(jColorChooser);
						parent.pack();
						parent.setVisible(true);
					}
				});
				item.add(colorPicker, BorderLayout.EAST);
			}

			if (cid.getType() == Dimension.class)
			{
				JPanel dimensionPanel = new JPanel();
				dimensionPanel.setLayout(new BorderLayout());

				String str = configManager.getConfiguration(cd.getGroup().value(), cid.getItem().keyName());
				String[] splitStr = str.split("x");
				int width = Integer.parseInt(splitStr[0]);
				int height = Integer.parseInt(splitStr[1]);

				SpinnerModel widthModel = new SpinnerNumberModel(width, 0, Integer.MAX_VALUE, 1);
				JSpinner widthSpinner = new JSpinner(widthModel);
				Component widthEditor = widthSpinner.getEditor();
				JFormattedTextField widthSpinnerTextField = ((JSpinner.DefaultEditor) widthEditor).getTextField();
				widthSpinnerTextField.setColumns(4);

				SpinnerModel heightModel = new SpinnerNumberModel(height, 0, Integer.MAX_VALUE, 1);
				JSpinner heightSpinner = new JSpinner(heightModel);
				Component heightEditor = heightSpinner.getEditor();
				JFormattedTextField heightSpinnerTextField = ((JSpinner.DefaultEditor) heightEditor).getTextField();
				heightSpinnerTextField.setColumns(4);

				ChangeListener listener = e ->
					configManager.setConfiguration(cd.getGroup().value(), cid.getItem().keyName(), widthSpinner.getValue() + "x" + heightSpinner.getValue());

				widthSpinner.addChangeListener(listener);
				heightSpinner.addChangeListener(listener);

				dimensionPanel.add(widthSpinner, BorderLayout.WEST);
				dimensionPanel.add(new JLabel(" x "), BorderLayout.CENTER);
				dimensionPanel.add(heightSpinner, BorderLayout.EAST);

				item.add(dimensionPanel, BorderLayout.EAST);
			}

			if (cid.getType().isEnum())
			{
				Class<? extends Enum> type = (Class<? extends Enum>) cid.getType();
				JComboBox box = new JComboBox(type.getEnumConstants());
				box.setPreferredSize(new Dimension(box.getPreferredSize().width, 25));
				box.setRenderer(new ComboBoxListRenderer());
				box.setForeground(Color.WHITE);
				box.setFocusable(false);
				box.setPrototypeDisplayValue("XXXXXXXX"); //sorry but this is the way to keep the size of the combobox in check.
				try
				{
					Enum selectedItem = Enum.valueOf(type, configManager.getConfiguration(cd.getGroup().value(), cid.getItem().keyName()));
					box.setSelectedItem(selectedItem);
					box.setToolTipText(selectedItem.toString());
				}
				catch (IllegalArgumentException ex)
				{
					log.debug("invalid seleced item", ex);
				}
				box.addItemListener(e ->
				{
					if (e.getStateChange() == ItemEvent.SELECTED)
					{
						changeConfiguration(listItem, config, box, cd, cid);
						box.setToolTipText(box.getSelectedItem().toString());
					}
				});
				item.add(box, BorderLayout.EAST);
			}

			if (cid.getType() == Keybind.class)
			{
				Keybind startingValue = configManager.getConfiguration(cd.getGroup().value(), cid.getItem().keyName(), Keybind.class);

				HotkeyButton button = new HotkeyButton(startingValue);

				button.addFocusListener(new FocusAdapter()
				{
					@Override
					public void focusLost(FocusEvent e)
					{
						changeConfiguration(listItem, config, button, cd, cid);
					}
				});

				item.add(button, BorderLayout.EAST);
			}

			mainPanel.add(item);
		}

		JButton resetButton = new JButton("Reset");
		resetButton.addActionListener((e) ->
		{
			configManager.setDefaultConfiguration(config, true);

			// Reload configuration panel
			openGroupConfigPanel(listItem, config, cd);
		});
		mainPanel.add(resetButton);

		JButton backButton = new JButton("Back");
		backButton.addActionListener(e -> openConfigList());
		mainPanel.add(backButton);

		revalidate();
		scrollPane.getVerticalScrollBar().setValue(0);
	}

	private void changeConfiguration(PluginListItem listItem, Config config, JComponent component, ConfigDescriptor cd, ConfigItemDescriptor cid)
	{
		final ConfigItem configItem = cid.getItem();

		if (!Strings.isNullOrEmpty(configItem.warning()))
		{
			final int result = JOptionPane.showOptionDialog(component, configItem.warning(),
				"Are you sure?", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,
				null, new String[]{"Yes", "No"}, "No");

			if (result != JOptionPane.YES_OPTION)
			{
				openGroupConfigPanel(listItem, config, cd);
				return;
			}
		}

		if (component instanceof JCheckBox)
		{
			JCheckBox checkbox = (JCheckBox) component;
			configManager.setConfiguration(cd.getGroup().value(), cid.getItem().keyName(), "" + checkbox.isSelected());
		}
		else if (component instanceof JSpinner)
		{
			JSpinner spinner = (JSpinner) component;
			configManager.setConfiguration(cd.getGroup().value(), cid.getItem().keyName(), "" + spinner.getValue());
		}
		else if (component instanceof JTextArea)
		{
			JTextArea textField = (JTextArea) component;
			configManager.setConfiguration(cd.getGroup().value(), cid.getItem().keyName(), textField.getText());
		}
		else if (component instanceof JColorChooser)
		{
			JColorChooser jColorChooser = (JColorChooser) component;
			configManager.setConfiguration(cd.getGroup().value(), cid.getItem().keyName(), String.valueOf(jColorChooser.getColor().getRGB()));
		}
		else if (component instanceof JComboBox)
		{
			JComboBox jComboBox = (JComboBox) component;
			configManager.setConfiguration(cd.getGroup().value(), cid.getItem().keyName(), ((Enum) jComboBox.getSelectedItem()).name());
		}
		else if (component instanceof HotkeyButton)
		{
			HotkeyButton hotkeyButton = (HotkeyButton) component;
			configManager.setConfiguration(cd.getGroup().value(), cid.getItem().keyName(), hotkeyButton.getValue());
		}
	}

	void startPlugin(Plugin plugin, PluginListItem listItem)
	{
		executorService.submit(() ->
		{
			pluginManager.setPluginEnabled(plugin, true);

			try
			{
				pluginManager.startPlugin(plugin);
			}
			catch (PluginInstantiationException ex)
			{
				log.warn("Error when starting plugin {}", plugin.getClass().getSimpleName(), ex);
			}

			listItem.setPluginEnabled(true);
		});
	}

	void stopPlugin(Plugin plugin, PluginListItem listItem)
	{
		executorService.submit(() ->
		{
			pluginManager.setPluginEnabled(plugin, false);

			try
			{
				pluginManager.stopPlugin(plugin);
			}
			catch (PluginInstantiationException ex)
			{
				log.warn("Error when stopping plugin {}", plugin.getClass().getSimpleName(), ex);
			}

			listItem.setPluginEnabled(false);
		});
	}

	private List<String> getPinnedPluginNames()
	{
		final String config = configManager.getConfiguration(RUNELITE_GROUP_NAME, PINNED_PLUGINS_CONFIG_KEY);

		if (config == null)
		{
			return Collections.emptyList();
		}

		return COMMA_SPLITTER.splitToList(config);
	}

	void savePinnedPlugins()
	{
		final String value = pluginList.stream()
			.filter(PluginListItem::isPinned)
			.map(PluginListItem::getName)
			.collect(Collectors.joining(","));

		configManager.setConfiguration(RUNELITE_GROUP_NAME, PINNED_PLUGINS_CONFIG_KEY, value);
	}

	@Override
	public void onActivate()
	{
		super.onActivate();

		if (searchBar.getParent() != null)
		{
			searchBar.requestFocusInWindow();
		}
	}

	@Override
	public Dimension getPreferredSize()
	{
		return new Dimension(PANEL_WIDTH + SCROLLBAR_WIDTH, super.getPreferredSize().height);
	}

	public class FixedWidthPanel extends JPanel
	{
		@Override
		public Dimension getPreferredSize()
		{
			return new Dimension(PANEL_WIDTH, super.getPreferredSize().height);
		}

	}
}