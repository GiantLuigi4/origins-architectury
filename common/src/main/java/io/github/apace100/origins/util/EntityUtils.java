package io.github.apace100.origins.util;

import me.shedaniel.architectury.platform.Platform;
import net.minecraft.entity.Entity;
import virtuoel.pehkui.util.ScaleUtils;

public class EntityUtils {
	/**
	 * simple method, other mods can mixin to this to change the value
	 * if pehkui is present, then multiply by the scale pehkui applies to step height
	 * @param entity the entity in question
	 * @return the entity's step height
	 */
	public static double getStepHeight(Entity entity) {
		if (Platform.isModLoaded("pehkui")) return value(entity.stepHeight * ScaleUtils.getMotionScale(entity));
		return value(entity.stepHeight);
	}
	
	/**
	 * just here so that ModifyArgs works
	 * @param val the input value
	 * @return the input value
	 */
	public static double value(double val) {
		return val;
	}
}
