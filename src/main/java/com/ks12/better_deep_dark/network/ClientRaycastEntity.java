package com.ks12.better_deep_dark.network;

import com.mojang.datafixers.types.Func;
import io.netty.buffer.Unpooled;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;

import java.util.concurrent.CompletableFuture;

public class ClientRaycastEntity {

    //this runs on the server
    public static CompletableFuture<Integer> sendRaycastRequest(ServerPlayerEntity user) {
        CompletableFuture<Integer> hitResultFuture = new CompletableFuture<>();

        ServerPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.RAYCAST_ENTITY_RESPONSE_ID, (server, player, handler, buf, responseSender) -> {
            int entityId = buf.readInt();
            hitResultFuture.complete(entityId);
            ServerPlayNetworking.unregisterGlobalReceiver(ModNetworkingConstants.RAYCAST_ENTITY_RESPONSE_ID);

        });

        ServerPlayNetworking.send(user, ModNetworkingConstants.RAYCAST_ENTITY_REQUEST_ID, PacketByteBufs.empty());


        return hitResultFuture;
    }

    //this runs on the client
    public static void registerListener() {
        ClientPlayNetworking.registerGlobalReceiver(ModNetworkingConstants.RAYCAST_ENTITY_REQUEST_ID, (minecraftClient, clientPlayNetworkHandler, packetByteBuf, packetSender) -> {
            HitResult hitResult = MinecraftClient.getInstance().crosshairTarget;
            PacketByteBuf buf = PacketByteBufs.create();

            if (hitResult.getType().equals(HitResult.Type.ENTITY)) {
                Entity hitEntity = ((EntityHitResult) hitResult).getEntity();
                buf.writeInt(hitEntity.getId());
            }
            else buf.writeInt(-1);
            packetSender.sendPacket(ModNetworkingConstants.RAYCAST_ENTITY_RESPONSE_ID, buf);
        });
    }
}
