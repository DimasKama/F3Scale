package io.github.dimaskama.f3scale.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import io.github.dimaskama.f3scale.client.F3Scale;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.hud.DebugHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(DebugHud.class)
abstract class DebugHudMixin {

    @Inject(method = "drawText", at = @At("HEAD"))
    private void beforeTextRendering(DrawContext context, List<String> text, boolean left, CallbackInfo ci) {
        float scale = F3Scale.CONFIG.scale;;
        context.getMatrices().push();
        context.getMatrices().scale(scale, scale, 0.0F);
    }

    @ModifyExpressionValue(method = "drawText", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/DrawContext;getScaledWindowWidth()I"))
    private int getReadWidth(int original) {
        return (int) (original / F3Scale.CONFIG.scale);
    }

    @Inject(method = "drawText", at = @At("TAIL"))
    private void afterTextRendering(DrawContext context, List<String> text, boolean left, CallbackInfo ci) {
        context.getMatrices().pop();
    }
}
