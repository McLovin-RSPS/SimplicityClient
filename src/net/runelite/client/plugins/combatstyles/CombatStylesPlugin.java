package net.runelite.client.plugins.combatstyles;

import com.google.inject.Provides;
import net.runelite.api.Client;
import net.runelite.api.GameState;
import net.runelite.api.VarPlayer;
import net.runelite.api.events.VarbitChanged;
import net.runelite.api.events.WidgetLoaded;
import net.runelite.client.callback.ClientThread;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

import javax.inject.Inject;

@PluginDescriptor(name = "Combat Styles", description = "Show your current attack style as an overlay", tags = {"combat", "defence", "magic", "overlay", "ranged", "strength", "warn", "pure"})
public class CombatStylesPlugin extends Plugin {
    private int attackStyleVarbit = -1;

    private int equippedWeaponTypeVarbit = -1;

    private int castingModeVarbit = -1;

    private CombatStyle attackStyle;

    @Inject
    private Client client;

    @Inject
    private ClientThread clientThread;

    @Inject
    private CombatStylesConfig config;

    @Inject
    private OverlayManager overlayManager;

    @Inject
    private CombatStylesOverlay overlay;

    @Provides
    CombatStylesConfig provideConfig(ConfigManager configManager) {
        return (CombatStylesConfig)configManager.getConfig(CombatStylesConfig.class);
    }

    protected void startUp() throws Exception {
        this.overlayManager.add(this.overlay);
        if (this.client.getGameState() == GameState.LOGGED_IN)
            this.clientThread.invokeLater(this::start);
    }

    private void start() {
        this.attackStyleVarbit = this.client.getVar(VarPlayer.ATTACK_STYLE);
        this.equippedWeaponTypeVarbit = this.client.getSidebarIds()[0];
        this.castingModeVarbit = this.client.getVar(VarPlayer.CAST_MODE);
        updateAttackStyle(this.equippedWeaponTypeVarbit, this.attackStyleVarbit, this.castingModeVarbit);
    }

    protected void shutDown() {
        this.overlayManager.remove(this.overlay);
    }

    public CombatStyle getAttackStyle() {
        if (this.attackStyle == null)
            start();
        return this.attackStyle;
    }

    @Subscribe
    public void onWidgetLoaded(WidgetLoaded event) {
        if (this.equippedWeaponTypeVarbit == -1 || this.equippedWeaponTypeVarbit != this.client.getSidebarIds()[0]) {
            this.equippedWeaponTypeVarbit = this.client.getSidebarIds()[0];
            updateAttackStyle(this.equippedWeaponTypeVarbit, this.client.getVar(VarPlayer.ATTACK_STYLE), this.client
                    .getVar(VarPlayer.CAST_MODE));
        }
    }

    @Subscribe
    public void onVarpChanged(VarbitChanged event) {
        if (this.castingModeVarbit == -1 || this.castingModeVarbit != this.client.getVar(VarPlayer.CAST_MODE)) {
            this.castingModeVarbit = this.client.getVar(VarPlayer.CAST_MODE);
            updateAttackStyle(this.client.getSidebarIds()[0], this.client.getVar(VarPlayer.ATTACK_STYLE), this.castingModeVarbit);
            System.out.println("ATTACK_STYLE = " + this.client.getVar(VarPlayer.ATTACK_STYLE));
        }
        if (this.attackStyleVarbit == -1 || this.attackStyleVarbit != this.client.getVar(VarPlayer.ATTACK_STYLE)) {
            System.out.println("ATTACK_STYLE = " + this.client.getVar(VarPlayer.ATTACK_STYLE));
            this.attackStyleVarbit = this.client.getVar(VarPlayer.ATTACK_STYLE);
            updateAttackStyle(this.equippedWeaponTypeVarbit, this.attackStyleVarbit, this.client
                    .getVar(VarPlayer.CAST_MODE));
        }
    }

    private void updateAttackStyle(int equippedWeaponType, int attackStyleIndex, int castingMode) {
        CombatStyle[] attackStyles = WeaponType.getWeaponType(equippedWeaponType).getAttackStyles();
        if (attackStyleIndex < attackStyles.length) {
            this.attackStyle = attackStyles[attackStyleIndex];
            if (this.attackStyle == null) {
                this.attackStyle = CombatStyle.OTHER;
            } else if (this.attackStyle == CombatStyle.CASTING && castingMode == 1) {
                this.attackStyle = CombatStyle.DEFENSIVE_CASTING;
            } else if (this.attackStyle == CombatStyle.DEFENSIVE_CASTING && castingMode == 0) {
                this.attackStyle = CombatStyle.CASTING;
            }
        }
    }
}
