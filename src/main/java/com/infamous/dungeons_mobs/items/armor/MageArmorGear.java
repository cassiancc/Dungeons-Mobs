package com.infamous.dungeons_mobs.items.armor;

import com.infamous.dungeons_libraries.items.gearconfig.ArmorGear;
import com.infamous.dungeons_mobs.client.renderer.armor.MageArmorGearRenderer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.extensions.common.IClientItemExtensions;
import org.jetbrains.annotations.NotNull;
import software.bernie.geckolib.renderer.GeoArmorRenderer;

import java.util.function.Consumer;

public class MageArmorGear extends ArmorGear {

    public MageArmorGear(ArmorItem.Type slotType, Properties properties, ResourceLocation armorSet, ResourceLocation modelLocation, ResourceLocation textureLocation, ResourceLocation animationFileLocation) {
        super(slotType, properties, armorSet, modelLocation, textureLocation, animationFileLocation);
    }

    @Override
    public void initializeClient(Consumer<IClientItemExtensions> consumer) {
        consumer.accept(new IClientItemExtensions() {
            private GeoArmorRenderer<?> renderer;

            @Override
            public @NotNull HumanoidModel<?> getHumanoidArmorModel(LivingEntity livingEntity, ItemStack itemStack, EquipmentSlot equipmentSlot, HumanoidModel<?> original) {
                if (this.renderer == null)
                    this.renderer = new MageArmorGearRenderer(livingEntity);

                // This prepares our GeoArmorRenderer for the current render frame.
                // These parameters may be null however, so we don't do anything further with them
                this.renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original);

                return this.renderer;
            }
        });
    }
}
