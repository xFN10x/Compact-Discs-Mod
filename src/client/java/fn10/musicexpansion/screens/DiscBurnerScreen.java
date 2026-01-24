package fn10.musicexpansion.screens;

import fn10.musicexpansion.MusicExpanded;
import fn10.musicexpansion.menu.DiscBurnerMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class DiscBurnerScreen extends AbstractContainerScreen<DiscBurnerMenu> {

    private final Identifier BG = Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
            "textures/gui/container/disc_burner.png");
    private final Identifier PROGRESS_SPRITE = Identifier.fromNamespaceAndPath(MusicExpanded.MOD_ID,
            "container/disc_burner/progress");
    private final int bgWidth = 176;
    private final int bgHeight = 161;
    private final DiscBurnerMenu menu;

    public DiscBurnerScreen(DiscBurnerMenu menu, Inventory inventory, Component component) {
        super(menu, inventory, component);
        this.menu = menu;
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float f, int i, int j) {
        int bgX = (width - bgWidth) / 2;
        int bgY = (height - bgHeight) / 2;

        guiGraphics.blit(RenderPipelines.GUI_TEXTURED, BG, bgX, bgY, 0, 0, bgWidth, bgHeight, 256, 256);
    }

    public void renderProgressSprite(GuiGraphics guiGraphics) {
        int burnTime = 700 - menu.data.get(0);
        int maxTime = 700;
        float progress = (float) burnTime / (float) maxTime;
        int bgX = (width - bgWidth) / 2;
        int bgY = (height - bgHeight) / 2;
        guiGraphics.blitSprite(RenderPipelines.GUI_TEXTURED, PROGRESS_SPRITE, 20, 41, 0, 0, bgX + 78, bgY + 69, 20,
                Math.round(41 * progress));
    }

    @Override
    public void render(GuiGraphics guiGraphics, int i, int j, float f) {
        super.render(guiGraphics, i, j, f);
        renderTooltip(guiGraphics, i, j);
        renderProgressSprite(guiGraphics);
    }

}
