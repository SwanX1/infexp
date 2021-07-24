package com.nekomaster1000.infernalexp.config.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;

import com.nekomaster1000.infernalexp.config.ConfigHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.OptionsSubScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.List;

@OnlyIn(Dist.CLIENT)
public abstract class IESettingsScreen extends Screen {

	private final Screen parentScreen;
	protected OptionsList optionsRowList;

	protected IESettingsScreen(Screen parentScreen, Component titleIn) {
		super(titleIn);
		this.parentScreen = parentScreen;
	}

	public abstract void addSettings();

	@Override
	protected void init() {
		optionsRowList = new OptionsList(minecraft, width, height, 24, height - 32, 25);

		addSettings();

		children.add(optionsRowList);

		addButton(new Button((width - 200) / 2, height - 26, 200, 20, new TranslatableComponent("gui.done"), button -> onClose()));
	}

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrixStack);

		optionsRowList.render(matrixStack, mouseX, mouseY, partialTicks);

		List<FormattedCharSequence> list = OptionsSubScreen.tooltipAt(optionsRowList, mouseX, mouseY);
		if (list != null) {
			this.renderTooltip(matrixStack, list, mouseX, mouseY);
		}

		// The parameter names for this function are wrong. The three integers at the end should be x, y, color
		drawCenteredString(matrixStack, font, title, width / 2, 8, 0xFFFFFF);

		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}

	@Override
	public void onClose() {
		ConfigHelper.saveToClient();
		ConfigHelper.saveToCommon();
		Minecraft.getInstance().setScreen(parentScreen);
	}
}
