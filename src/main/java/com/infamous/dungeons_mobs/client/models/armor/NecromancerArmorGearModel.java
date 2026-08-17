package com.infamous.dungeons_mobs.client.models.armor;

import com.infamous.dungeons_libraries.client.renderer.gearconfig.ArmorGearModel;
import com.infamous.dungeons_libraries.items.gearconfig.ArmorGear;
import com.infamous.dungeons_mobs.entities.undead.NecromancerEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.cache.GeckoLibCache;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.loading.math.MathParser;

public class NecromancerArmorGearModel<T extends ArmorGear> extends ArmorGearModel<T> {

    public NecromancerArmorGearModel(LivingEntity wearer) {
        this.wearer = wearer;
    }

    LivingEntity wearer;

    public LivingEntity getWearer() {
        return wearer;
    }

    public void setWearer(LivingEntity wearer) {
        this.wearer = wearer;
    }

    @Override
    public void setCustomAnimations(T entity, long uniqueID, AnimationState<T> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var cloak = this.getAnimationProcessor().getBone("armorCloak");

        cloak.setHidden(this.getWearer() != null && this.getWearer() instanceof NecromancerEntity);
        Vec3 velocity = wearer.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MathParser.setVariable("query.ground_speed", () -> groundSpeed * 13);
    }
}
