package com.infamous.dungeons_libraries.network;

import com.infamous.dungeons_libraries.integration.curios.client.message.CuriosArtifactStartMessage;
import com.infamous.dungeons_libraries.integration.curios.client.message.CuriosArtifactStopMessage;
import com.infamous.dungeons_libraries.network.gearconfig.*;
import com.infamous.dungeons_libraries.network.materials.ArmorMaterialSyncPacket;
import com.infamous.dungeons_libraries.network.materials.WeaponMaterialSyncPacket;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

public class NetworkHandler {
    public static PayloadRegistrar INSTANCE;

    public NetworkHandler() {
    }

    public static void init(PayloadRegistrar registrar) {
        INSTANCE = registrar;
        INSTANCE.commonBidirectional(UpdateSoulsMessage.TYPE, UpdateSoulsMessage.STREAM_CODEC, UpdateSoulsMessage.UpdateSoulsHandler::handle);
        INSTANCE.commonBidirectional(ArmorGearConfigSyncPacket.TYPE, ArmorGearConfigSyncPacket.STREAM_CODEC, ArmorGearConfigSyncPacket::onPacketReceived);
        INSTANCE.commonBidirectional(MeleeGearConfigSyncPacket.TYPE, MeleeGearConfigSyncPacket.STREAM_CODEC, MeleeGearConfigSyncPacket::onPacketReceived);
        INSTANCE.commonBidirectional(BowGearConfigSyncPacket.TYPE, BowGearConfigSyncPacket.STREAM_CODEC, BowGearConfigSyncPacket::onPacketReceived);
        INSTANCE.commonBidirectional(CrossbowGearConfigSyncPacket.TYPE, CrossbowGearConfigSyncPacket.STREAM_CODEC, CrossbowGearConfigSyncPacket::onPacketReceived);
        INSTANCE.commonBidirectional(ArmorMaterialSyncPacket.TYPE, CrossbowGearConfigSyncPacket.STREAM_CODEC, CrossbowGearConfigSyncPacket::onPacketReceived);
        INSTANCE.commonBidirectional(WeaponMaterialSyncPacket.TYPE, WeaponMaterialSyncPacket.STREAM_CODEC, WeaponMaterialSyncPacket::onPacketReceived);
        INSTANCE.commonBidirectional(CuriosArtifactStartMessage.TYPE, CuriosArtifactStartMessage.STREAM_CODEC, CuriosArtifactStartMessage.CuriosArtifactHandler::handle);
        INSTANCE.commonBidirectional(CuriosArtifactStopMessage.TYPE, CuriosArtifactStopMessage.STREAM_CODEC, CuriosArtifactStopMessage::handle);
        INSTANCE.commonBidirectional(EliteMobMessage.TYPE, EliteMobMessage.STREAM_CODEC, EliteMobMessage::handle);
        INSTANCE.commonBidirectional(BreakItemMessage.TYPE, BreakItemMessage.STREAM_CODEC, BreakItemMessage.BreakItemHandler::handle);
        INSTANCE.commonBidirectional(SwitchHandMessage.TYPE, SwitchHandMessage.STREAM_CODEC, SwitchHandMessage.SwitchHandHandler::handle);
        INSTANCE.commonBidirectional(ArtifactGearConfigSyncPacket.TYPE, ArtifactGearConfigSyncPacket.STREAM_CODEC, ArtifactGearConfigSyncPacket::onPacketReceived);
    }
}
