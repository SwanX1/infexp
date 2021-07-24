package com.nekomaster1000.infernalexp.data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import com.nekomaster1000.infernalexp.InfernalExpansion;

import net.minecraft.server.packs.resources.SimpleJsonResourceReloadListener;
import net.minecraft.world.item.Item;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraft.resources.ResourceLocation;

import net.minecraftforge.registries.ForgeRegistries;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class VolineEatTable extends SimpleJsonResourceReloadListener {

	private static final Gson GSON_INSTANCE = (new GsonBuilder()).create();
	private static final Map<Item, Map<Item, Integer>> VOLINE_EAT_TABLE = new HashMap<>();

	public VolineEatTable() {
        super(GSON_INSTANCE, "loot_tables/gameplay");
    }

	@Override
	protected void apply(Map<ResourceLocation, JsonElement> objectIn, ResourceManager resourceManagerIn, ProfilerFiller profilerIn) {
        ResourceLocation resourceLocation = new ResourceLocation(InfernalExpansion.MOD_ID, "loot_tables/gameplay/voline_eat_table.json");

        try {
            for (Resource iResource : resourceManagerIn.getResources(resourceLocation)) {
                try (Reader reader = new BufferedReader(new InputStreamReader(iResource.getInputStream(), StandardCharsets.UTF_8))) {
                    JsonObject jsonObject = GsonHelper.fromJson(GSON_INSTANCE, reader, JsonObject.class);

                    if (jsonObject != null) {
                        for (JsonElement entry : jsonObject.getAsJsonArray("entries")) {

							VOLINE_EAT_TABLE.put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry.getAsJsonObject().get("accepted_item").getAsString())),
                                new HashMap<Item, Integer>() {{
                                    for (JsonElement item : entry.getAsJsonObject().getAsJsonArray("returned_items")) {
                                        put(ForgeRegistries.ITEMS.getValue(new ResourceLocation(item.getAsJsonObject().get("item").getAsString())),
                                            item.getAsJsonObject().get("amount").getAsInt());
                                    }
                                }}
                            );
						}
					}

				} catch (RuntimeException | IOException exception) {
					InfernalExpansion.LOGGER.error("Couldn't read voline eat table list {} in data pack {}", resourceLocation, iResource.getSourceName(), exception);

				} finally {
					IOUtils.closeQuietly(iResource);
				}
			}
		} catch (IOException exception) {
			InfernalExpansion.LOGGER.error("Couldn't read voline eat table from {}", resourceLocation, exception);
		}

	}

	public Map<Item, Map<Item, Integer>> getVolineEatTable() {
		return VOLINE_EAT_TABLE;
	}

}
