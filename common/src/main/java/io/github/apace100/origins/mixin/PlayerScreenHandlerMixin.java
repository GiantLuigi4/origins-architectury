package io.github.apace100.origins.mixin;

import io.github.apace100.origins.api.component.OriginComponent;
import io.github.apace100.origins.power.factories.RestrictArmorPower;
import io.github.apace100.origins.registry.ModComponentsArchitectury;
import io.github.apace100.origins.registry.ModPowers;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.slot.Slot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/screen/PlayerScreenHandler$1")
public abstract class PlayerScreenHandlerMixin extends Slot {

	public PlayerScreenHandlerMixin(Inventory inventory, int index, int x, int y) {
		super(inventory, index, x, y);
	}

	@Inject(method = "Lnet/minecraft/screen/PlayerScreenHandler$1;canInsert(Lnet/minecraft/item/ItemStack;)Z", at = @At("HEAD"), cancellable = true)
	private void preventArmorInsertion(ItemStack stack, CallbackInfoReturnable<Boolean> info) {
		PlayerEntity player = ((PlayerInventory) inventory).player;
		OriginComponent component = ModComponentsArchitectury.getOriginComponent(player);
		EquipmentSlot slot = MobEntity.getPreferredEquipmentSlot(stack);
		if (RestrictArmorPower.isForbidden(player, slot, stack))
			info.setReturnValue(false);
		else if (OriginComponent.hasPower(player, ModPowers.ELYTRA_FLIGHT.get()) && stack.getItem() == Items.ELYTRA)
			info.setReturnValue(false);
	}
}
