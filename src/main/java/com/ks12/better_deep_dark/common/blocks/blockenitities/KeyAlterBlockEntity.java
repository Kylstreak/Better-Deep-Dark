package com.ks12.better_deep_dark.common.blocks.blockenitities;

import com.ks12.better_deep_dark.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class KeyAlterBlockEntity extends BlockEntity {
    private ItemStack stack = ItemStack.EMPTY;

    public KeyAlterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.KEY_ALTER_BLOCK_ENTITY, pos, state);
    }

    public ItemStack getStack() {
        return stack;
    }

    public void setStack(ItemStack stack) {
        this.stack = stack;
        markDirty();
        if (world != null && !world.isClient) {
            ((ServerWorld) world).getChunkManager().markForUpdate(pos);

            world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        }
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        nbt.put("Item", stack.writeNbt(new NbtCompound()));
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("Item")) {
            stack = ItemStack.fromNbt(nbt.getCompound("Item"));
        }
        else {
            this.stack = ItemStack.EMPTY;
        }
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        NbtCompound nbt = new NbtCompound();
        writeNbt(nbt);
        return nbt;
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }
}


