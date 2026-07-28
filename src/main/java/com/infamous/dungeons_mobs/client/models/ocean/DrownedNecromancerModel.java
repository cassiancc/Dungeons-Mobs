package com.infamous.dungeons_mobs.client.models.ocean;// Made with Blockbench 3.8.4
// Exported for Minecraft version 1.15 - 1.16
// Paste this class into your mod and generate all required imports


import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.infamous.dungeons_mobs.DungeonsMobs;
import com.infamous.dungeons_mobs.client.particle.ModParticleTypes;
import com.infamous.dungeons_mobs.entities.water.DrownedNecromancerEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.animation.AnimationState;
import software.bernie.geckolib.loading.math.MathParser;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.model.data.EntityModelData;

@OnlyIn(Dist.CLIENT)
public class DrownedNecromancerModel extends GeoModel<DrownedNecromancerEntity> {

    @Override
    public ResourceLocation getAnimationResource(DrownedNecromancerEntity entity) {
        return GeneralUtil.mobsLoc( "animations/drowned_necromancer.animation.json");
    }

    @Override
    public ResourceLocation getModelResource(DrownedNecromancerEntity entity) {
        return GeneralUtil.mobsLoc( "geo/drowned_necromancer.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(DrownedNecromancerEntity entity) {
        return GeneralUtil.mobsLoc( "textures/entity/ocean/drowned_necromancer.png");
    }

    @Override
    public RenderType getRenderType(DrownedNecromancerEntity animatable, ResourceLocation textureLocation) {
        return RenderType.entityTranslucent(textureLocation);
    }

    @Override
    public void setCustomAnimations(DrownedNecromancerEntity entity, long uniqueID, AnimationState<DrownedNecromancerEntity> customPredicate) {
        super.setCustomAnimations(entity, uniqueID, customPredicate);

        var head = this.getAnimationProcessor().getBone("bipedHead");
        var cape = this.getAnimationProcessor().getBone("bipedCape");

        var particles = this.getAnimationProcessor().getBone("staffParticles");

        if (particles instanceof GeoBone && entity.isSpellcasting()) {
            GeoBone particleBone = ((GeoBone) particles);
            entity.level().addParticle(entity.isInWaterOrBubble() ? ParticleTypes.BUBBLE_COLUMN_UP : ModParticleTypes.NECROMANCY.get(), particleBone.getWorldPosition().x, particleBone.getWorldPosition().y, particleBone.getWorldPosition().z, 0, 0, 0);
        }

        cape.setHidden(entity.getItemBySlot(EquipmentSlot.CHEST).getItem() != entity.getArmorSet().getChest().get());

        EntityModelData extraData = (EntityModelData) customPredicate.getData(DataTickets.ENTITY_MODEL_DATA);

        if (extraData.headPitch() != 0 || extraData.netHeadYaw() != 0) {
            head.setRotX(head.getRotX() + (extraData.headPitch() * ((float) Math.PI / 180F)));
            head.setRotY(head.getRotY() + (extraData.netHeadYaw() * ((float) Math.PI / 180F)));
        }
        Vec3 velocity = entity.getDeltaMovement();
        float groundSpeed = Mth.sqrt((float) ((velocity.x * velocity.x) + (velocity.z * velocity.z)));
        MathParser.setVariable("query.ground_speed", () -> groundSpeed * 20);
    }
}
