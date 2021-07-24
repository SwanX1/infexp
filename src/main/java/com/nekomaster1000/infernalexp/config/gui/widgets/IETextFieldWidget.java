package com.nekomaster1000.infernalexp.config.gui.widgets;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Options;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.TooltipAccessor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.network.chat.Component;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;
import java.util.Optional;

@OnlyIn(Dist.CLIENT)
public class IETextFieldWidget extends EditBox implements TooltipAccessor {

	private final Options settings;
	private final TextFieldOption option;

	public IETextFieldWidget(Options settings, int x, int y, int width, Component title, TextFieldOption option) {
        super(Minecraft.getInstance().font, x + 2 + 100, y, width - 4 - 100, 20, title);
        this.option = option;
        this.settings = settings;

        setMaxLength(1892);
        setValue(option.get(settings));
    }

	@Override
	public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		super.renderButton(matrixStack, mouseX, mouseY, partialTicks);
		// The parameter names for this function are wrong. The three integers at the end should be x, y, color
		drawString(matrixStack, Minecraft.getInstance().font, this.getMessage(), this.x - 100, (this.y + (this.height - 8) / 2), -6250336);
	}

	@Override
	public void onValueChange(String newText) {
		super.onValueChange(newText);
		option.set(settings, newText);
	}

	@Override
	public Optional<List<FormattedCharSequence>> getTooltip() {
		return option.getTooltip();
	}
}
