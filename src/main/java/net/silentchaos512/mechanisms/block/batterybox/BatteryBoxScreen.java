package net.silentchaos512.mechanisms.block.batterybox;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.silentchaos512.mechanisms.SilentMechanisms;

public class BatteryBoxScreen extends ContainerScreen<BatteryBoxContainer> {
    public static final ResourceLocation TEXTURE = SilentMechanisms.getId("textures/gui/battery_box.png");

    public BatteryBoxScreen(BatteryBoxContainer containerIn, PlayerInventory playerInventoryIn, ITextComponent titleIn) {
        super(containerIn, playerInventoryIn, titleIn);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks) {
        this.renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        this.renderHoveredToolTip(mouseX, mouseY);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        if (minecraft == null) return;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(TEXTURE);
        int xPos = (this.width - this.xSize) / 2;
        int yPos = (this.height - this.ySize) / 2;
        blit(xPos, yPos, 0, 0, this.xSize, this.ySize);

        // Debug text
        int y = 5;
        for (String line : container.tileEntity.getDebugText()) {
            font.drawString(line, 5, y, 0xFFFFFF);
            y += 10;
        }
    }
}
