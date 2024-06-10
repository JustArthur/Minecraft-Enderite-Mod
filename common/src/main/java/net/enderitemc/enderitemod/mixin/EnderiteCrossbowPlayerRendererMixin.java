package net.enderitemc.enderitemod.mixin;

import net.enderitemc.enderitemod.tools.EnderiteTools;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.enderitemc.enderitemod.tools.EnderiteCrossbow;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.render.entity.model.BipedEntityModel.ArmPose;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

@Mixin(PlayerEntityRenderer.class)
public class EnderiteCrossbowPlayerRendererMixin {

    @Inject(at = @At("HEAD"), method = "getArmPose", cancellable = true)
    private static void getArmPose(AbstractClientPlayerEntity player, Hand hand,
            CallbackInfoReturnable<ArmPose> info) {
        ItemStack itemStack2 = player.getStackInHand(hand);
        if (!player.handSwinging && itemStack2.isOf(EnderiteTools.ENDERITE_CROSSBOW.get())
                && EnderiteCrossbow.isCharged(itemStack2)) {
            info.setReturnValue(ArmPose.CROSSBOW_HOLD);
        }
    }

}
