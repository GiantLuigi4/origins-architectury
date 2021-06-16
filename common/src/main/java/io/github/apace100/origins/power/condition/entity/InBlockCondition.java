package io.github.apace100.origins.power.condition.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.apace100.origins.power.factory.condition.ConditionFactory;
import io.github.apace100.origins.util.OriginsCodecs;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.LivingEntity;

import java.util.Optional;
import java.util.function.Predicate;

public class InBlockCondition implements Predicate<LivingEntity> {

	public static final Codec<InBlockCondition> CODEC = RecordCodecBuilder.create(instance -> instance.group(
			OriginsCodecs.BLOCK_CONDITION.optionalFieldOf("block_condition").forGetter(x -> x.blockCondition)
	).apply(instance, InBlockCondition::new));

	private final Optional<ConditionFactory.Instance<CachedBlockPosition>> blockCondition;

	public InBlockCondition(Optional<ConditionFactory.Instance<CachedBlockPosition>> blockCondition) {this.blockCondition = blockCondition;}

	@Override
	public boolean test(LivingEntity entity) {
		return entity.isOnGround() && blockCondition.map(x -> x.test(new CachedBlockPosition(entity.world, entity.getBlockPos().down(), true))).orElse(true);
	}
}