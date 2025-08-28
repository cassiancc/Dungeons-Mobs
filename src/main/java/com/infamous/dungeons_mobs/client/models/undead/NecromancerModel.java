package com.infamous.dungeons_mobs.client.models.undead;

import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.particle.ModParticleTypes;
import com.infamous.dungeons_mobs.entities.undead.NecromancerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.molang.MolangParser;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class NecromancerModel extends GeoModel<NecromancerEntity> {

    @Override
    public ResourceLocation getAnimationResource(NecromancerEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "animations/necromancer.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(NecromancerEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "geo/necromancer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(NecromancerEntity entity) {
        return new ResourceLocation(DungeonsMobs.MODID, "textures/entity/skeleton/necromancer.png");
    }

    @Override
    public RenderType getRenderType(NecromancerEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(NecromancerEntity entity, long uniqueID, AnimationState<NecromancerEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var head = this.getAnimationProcessor().getBone("bipedHead");
        var cape = this.getAnimationProcessor().getBone("bipedCape");

        var particles = this.getAnimationProcessor().getBone("staffParticles");

        if (entity.tickCount % 1 == 0 && particles instanceof GeoBone && entity.isSpellcasting()) {
            GeoBone particleBone = ((GeoBone) particles);
            entity.level().addParticle(ModParticleTypes.NECROMANCY.get(), particleBone.getWorldPosition().x, particleBone.getWorldPosition().y, particleBone.getWorldPosition().z, 0, 0, 0);
        }

        cape.setHidden(entity.getItemBySlot(EquipmentSlot.CHEST).getItem() != entity.getArmorSet().getChest().get());

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
        Vec3 velocity = entity.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MolangParser.INSTANCE.setValue("query.ground_speed", () -> groundSpeed * 20);
    }
}
