package com.nekomaster1000.infernalexp.client;

import net.minecraft.client.resources.model.Material;

public class ClientFireType {

	private final Material associatedSprite0;
	private final Material associatedSprite1;

	public ClientFireType(Material associatedSprite0, Material associatedSprite1) {
		this.associatedSprite0 = associatedSprite0;
		this.associatedSprite1 = associatedSprite1;
	}

	public Material getAssociatedSprite0() {
		return associatedSprite0;
	}

	public Material getAssociatedSprite1() {
		return associatedSprite1;
	}

}
