package com.infamous.dungeons_libraries.mixin;

import com.infamous.dungeons_libraries.capabilities.elite.EliteMob;
import com.infamous.dungeons_libraries.capabilities.elite.EliteMobHelper;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;
import software.bernie.geckolib.core.animatable.GeoAnimatable;
import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.renderer.GeoRenderer;

import static com.infamous.dungeons_libraries.entities.elite.EliteMobConfig.EMPTY_TEXTURE;

@Mixin(GeoEntityRenderer.class)
public abstract class GeoEntityRendererMixin<T extends LivingEntity & GeoAnimatable> extends EntityRenderer<T> implements GeoRenderer<T> {

    protected GeoEntityRendererMixin(EntityRendererProvider.Context p_i46179_1_) {
        super(p_i46179_1_);
    }

//    @Inject(method = "getTextureLocation(Lnet/minecraft/world/entity/Entity;)Lnet/minecraft/resources/ResourceLocation;",
//            at = @At(value = "RETURN"), locals = LocalCapture.CAPTURE_FAILHARD, cancellable = true, remap = false)
//    private void dungeonsLibraries_bindEliteTexture(Entity animatable, CallbackInfoReturnable<ResourceLocation> cir) {
//        EliteMob cap = EliteMobHelper.getEliteMobCapability(animatable);
//        if (cap.isElite() && !cap.getTexture().equals(EMPTY_TEXTURE)) {
//            cir.setReturnValue(cap.getTexture());
//        }
//    }
}
