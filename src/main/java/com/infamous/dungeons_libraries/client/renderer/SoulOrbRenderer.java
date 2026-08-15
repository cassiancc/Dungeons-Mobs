package com.infamous.dungeons_libraries.client.renderer;

import com.infamous.dungeons_libraries.entities.SoulOrbEntity;
import com.infamous.dungeons_libraries.utils.GeneralUtil;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.joml.Vector3f;

import static com.infamous.dungeons_libraries.DungeonsLibraries.MODID;


public class SoulOrbRenderer extends EntityRenderer<SoulOrbEntity> {
    private static final ResourceLocation SOUL_ORB_LOCATION = GeneralUtil.loc(MODID, "textures/entity/soul_orb.png");
    private static final RenderType RENDER_TYPE = RenderType.itemEntityTranslucentCull(SOUL_ORB_LOCATION);

    public SoulOrbRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.shadowRadius = 0.15F;
        this.shadowStrength = 0.75F;
    }

    protected int getBlockLightLevel(SoulOrbEntity pEntity, BlockPos pPos) {
        return Mth.clamp(super.getBlockLightLevel(pEntity, pPos) + 7, 0, 15);
    }

    public void render(SoulOrbEntity pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        pMatrixStack.pushPose();
        int i = pEntity.getIcon();
        float f = (float) (i % 4 * 16 + 0) / 84.0F;
        float f1 = (float) (i % 4 * 16 + 16) / 84.0F;
        float f2 = (float) (i / 4 * 18 + 0) / 84.0F;
        float f3 = (float) (i / 4 * 18 + 18) / 84.0F;
        float f4 = 1.0F;
        float f5 = 0.5F;
        float f6 = 0.25F;
        float f7 = 255.0F;
        float f8 = ((float) pEntity.tickCount + pPartialTicks) / 2.0F;
        int j = (int) ((Mth.sin(f8 + 0.0F) + 1.0F) * 0.5F * 155.0F);
        int k = 255;
        int l = (int) ((Mth.sin(f8 + 4.1887903F) + 1.0F) * 0.1F * 155.0F);
        pMatrixStack.translate(0.0D, 0.1F, 0.0D);
        pMatrixStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
        pMatrixStack.mulPose(Axis.YP.rotationDegrees(180.0F));
        float f9 = 0.3F;
        pMatrixStack.scale(0.3F, 0.3F, 0.3F);
        VertexConsumer ivertexbuilder = pBuffer.getBuffer(RENDER_TYPE);
        PoseStack.Pose matrixstack$entry = pMatrixStack.last();
        Matrix4f matrix4f = matrixstack$entry.pose();
        Matrix3f matrix3f = matrixstack$entry.normal();
        vertex(ivertexbuilder, matrix4f, matrix3f, -0.5F, -0.25F, j, l, 255, f, f3, pPackedLight);
        vertex(ivertexbuilder, matrix4f, matrix3f, 0.5F, -0.25F, j, l, 255, f1, f3, pPackedLight);
        vertex(ivertexbuilder, matrix4f, matrix3f, 0.5F, 0.75F, j, l, 255, f1, f2, pPackedLight);
        vertex(ivertexbuilder, matrix4f, matrix3f, -0.5F, 0.75F, j, l, 255, f, f2, pPackedLight);
        pMatrixStack.popPose();
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    private static void vertex(VertexConsumer pBuffer, Matrix4f pMatrix, Matrix3f pMatrixNormal, float pX, float pY, int pRed, int pGreen, int pBlue, float pTexU, float pTexV, int pPackedLight) {
        pBuffer.addVertex(pMatrix, pX, pY, 0.0F).setColor(pRed, pGreen, pBlue, 128).setUv(pTexU, pTexV).setOverlay(OverlayTexture.NO_OVERLAY).setLight(pPackedLight).setNormal(0.0F, 1.0F, 0.0F); //FIXME pMatrixNormal?
    }

    /**
     * Returns the location of an entity's texture.
     */
    public ResourceLocation getTextureLocation(SoulOrbEntity pEntity) {
        return SOUL_ORB_LOCATION;
    }
}
