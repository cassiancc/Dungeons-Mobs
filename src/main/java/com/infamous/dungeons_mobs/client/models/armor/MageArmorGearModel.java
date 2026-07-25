package com.infamous.dungeons_mobs.client.models.armor;

import com.infamous.dungeons_libraries.client.renderer.gearconfig.ArmorGearModel;
import com.infamous.dungeons_libraries.items.gearconfig.ArmorGear;
import com.infamous.dungeons_mobs.config.DungeonsMobsConfig;
import com.infamous.dungeons_mobs.entities.illagers.MageCloneEntity;
import com.infamous.dungeons_mobs.entities.illagers.MageEntity;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.core.molang.MolangParser;

public class MageArmorGearModel<T extends ArmorGear> extends ArmorGearModel<T> {

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

        cloak.setHidden(cloak != null && this.getWearer() != null && (this.getWearer() instanceof MageEntity || this.getWearer() instanceof MageCloneEntity));

        var rightArm = this.getAnimationProcessor().getBone("armorRightArm");
        var leftArm = this.getAnimationProcessor().getBone("armorLeftArm");
        if(!DungeonsMobsConfig.COMMON.ENABLE_3D_SLEEVES.get() && this.getWearer() != null && (this.getWearer() instanceof MageEntity || this.getWearer() instanceof MageCloneEntity)){
            rightArm.setHidden(true);
            leftArm.setHidden(true);
        }
        Vec3 velocity = wearer.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MolangParser.INSTANCE.setValue("query.ground_speed", () -> groundSpeed * 13);
    }
}
