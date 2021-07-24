package com.nekomaster1000.infernalexp.config.gui.screens;

import com.mojang.blaze3d.vertex.PoseStack;

import com.nekomaster1000.infernalexp.InfernalExpansion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.TranslatableComponent;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ConfigScreen extends Screen {

	private static final int BUTTON_WIDTH = 150;
	private static final int BUTTON_HEIGHT = 20;

	public ConfigScreen() {
		super(new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.title"));
	}

	@Override
	protected void init() {
        addButton(new Button(width / 2 - 155, height / 6, BUTTON_WIDTH, BUTTON_HEIGHT, new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.button.mobInteractions"), button -> Minecraft.getInstance().setScreen(new MobInteractionsScreen(this))));
        addButton(new Button(width / 2 + 5, height / 6, BUTTON_WIDTH, BUTTON_HEIGHT, new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.button.mobSpawning"), button -> Minecraft.getInstance().setScreen(new MobSpawningScreen(this))));
        addButton(new Button(width / 2 - 155, height / 6 + 24, BUTTON_WIDTH, BUTTON_HEIGHT, new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.button.miscellaneous"), button -> Minecraft.getInstance().setScreen(new MiscellaneousScreen(this))));
        addButton(new Button(width / 2 + 5, height / 6 + 24, BUTTON_WIDTH, BUTTON_HEIGHT, new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.button.worldGeneration"), button -> Minecraft.getInstance().setScreen(new WorldGenerationScreen(this))));
        addButton(new Button(width / 2 - 155, height / 6 + 48, BUTTON_WIDTH, BUTTON_HEIGHT, new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.button.clientConfig"), button -> Minecraft.getInstance().setScreen(new ClientConfigScreen(this))));

        addButton(new Button((width - 200) / 2, height - 26, 200, BUTTON_HEIGHT, new TranslatableComponent("gui.done"), button -> onClose()));
    }

	@Override
	public void render(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrixStack);

		// The parameter names for this function are wrong. The three integers at the end should be x, y, color
		drawCenteredString(matrixStack, font, title, width / 2, 8, 0xFFFFFF);

		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}

}
