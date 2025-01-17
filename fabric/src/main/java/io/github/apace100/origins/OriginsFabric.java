package io.github.apace100.origins;

import io.github.apace100.origins.util.ElytraPowerFallFlying;
import net.adriantodt.fallflyinglib.FallFlyingLib;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;

import static io.github.apace100.origins.Origins.*;

public class OriginsFabric implements ModInitializer {
	@Override
	public void onInitialize() {
		FabricLoader.getInstance().getModContainer(MODID).ifPresent(modContainer -> {
			VERSION = modContainer.getMetadata().getVersion().getFriendlyString();
			if(VERSION.contains("+")) {
				VERSION = VERSION.split("\\+")[0];
			}
			if(VERSION.contains("-")) {
				VERSION = VERSION.split("-")[0];
			}
		});
		register();
		FallFlyingLib.registerAccessor(ElytraPowerFallFlying::new);
	}
}
