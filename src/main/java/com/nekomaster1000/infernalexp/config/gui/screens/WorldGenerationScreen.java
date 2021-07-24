package com.nekomaster1000.infernalexp.config.gui.screens;

import com.nekomaster1000.infernalexp.InfernalExpansion;
import com.nekomaster1000.infernalexp.config.InfernalExpansionConfig;
import com.nekomaster1000.infernalexp.config.gui.widgets.TextFieldOption;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.BooleanOption;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class WorldGenerationScreen extends IESettingsScreen {

    public WorldGenerationScreen(Screen parentScreen) {
        super(parentScreen, new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.title.worldGeneration"));
    }

    @Override
    public void addSettings() {
        for (InfernalExpansionConfig.WorldGeneration worldGeneration : InfernalExpansionConfig.WorldGeneration.values()) {
            if (worldGeneration.get() instanceof Boolean) {
                optionsRowList.addBig(new BooleanOption(InfernalExpansion.MOD_ID + ".config.option." + worldGeneration.getTranslationName(),
                    new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.tooltip." + worldGeneration.getTranslationName()),
                    settings -> (Boolean) worldGeneration.get(), (settings, value) -> worldGeneration.set(value)));

            } else if (worldGeneration.get() instanceof String) {
                optionsRowList.addBig(new TextFieldOption(InfernalExpansion.MOD_ID + ".config.option." + worldGeneration.getTranslationName(),
                    new TranslatableComponent(InfernalExpansion.MOD_ID + ".config.tooltip." + worldGeneration.getTranslationName()),
                    settings -> (String) worldGeneration.get(), (settings, value) -> worldGeneration.set(value)));
            }
        }
    }
}
