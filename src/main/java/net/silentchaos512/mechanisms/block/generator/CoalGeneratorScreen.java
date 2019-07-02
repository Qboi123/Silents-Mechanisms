package net.silentchaos512.mechanisms.block.generator;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.silentchaos512.mechanisms.SilentMechanisms;

public class CoalGeneratorScreen extends ContainerScreen<CoalGeneratorContainer> {
    public static final ResourceLocation TEXTURE = SilentMechanisms.getId("textures/gui/coal_generator.png");

    public CoalGeneratorScreen(CoalGeneratorContainer container, PlayerInventory playerInventory, ITextComponent titleIn) {
        super(container, playerInventory, titleIn);
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

        // Fuel remaining
        if (container.tileEntity.isBurning()) {
            int height = getFlameIconHeight();
            blit(xPos + 81, yPos + 53 + 12 - height, 176, 12 - height, 14, height + 1);
        }

        // Debug text
        int y = 5;
        for (String line : container.tileEntity.getDebugText()) {
            font.drawString(line, 5, y, 0xFFFFFF);
            y += 10;
        }
    }

    private int getFlameIconHeight() {
        int total = container.tileEntity.getTotalBurnTime();
        if (total == 0) total = 200;
        return container.tileEntity.getBurnTime() * 13 / total;
    }
}