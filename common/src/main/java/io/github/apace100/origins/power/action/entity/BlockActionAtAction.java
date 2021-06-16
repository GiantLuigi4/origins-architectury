package io.github.apace100.origins.power.action.entity;

import io.github.apace100.origins.power.configuration.FieldConfiguration;
import io.github.apace100.origins.api.power.configuration.ConfiguredBlockAction;
import io.github.apace100.origins.api.power.factory.EntityAction;
import io.github.apace100.origins.util.OriginsCodecs;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.Direction;

public class BlockActionAtAction extends EntityAction<FieldConfiguration<ConfiguredBlockAction<?>>> {

	public BlockActionAtAction() {
		super(FieldConfiguration.codec(OriginsCodecs.BLOCK_ACTION, "block_action"));
	}

	@Override
	public void execute(FieldConfiguration<ConfiguredBlockAction<?>> configuration, Entity entity) {
		configuration.value().execute(entity.world, entity.getBlockPos(), Direction.UP);
	}
}