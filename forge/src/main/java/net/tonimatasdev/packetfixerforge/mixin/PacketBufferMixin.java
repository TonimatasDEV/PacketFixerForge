package net.tonimatasdev.packetfixerforge.mixin;

import net.minecraft.network.PacketBuffer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.*;

@Mixin(PacketBuffer.class)
public class PacketBufferMixin {
    @ModifyVariable(method = "writeUtf(Ljava/lang/String;I)Lnet/minecraft/network/PacketBuffer;", at = @At(value = "HEAD"), ordinal = 0, argsOnly = true)
    private int writeUtfSize(int value) {
        return Integer.MAX_VALUE;
    }

    @ModifyVariable(method = "readUtf(I)Ljava/lang/String;", at = @At(value = "HEAD"), argsOnly = true)
    private int readUtfSize(int value) {
        return Integer.MAX_VALUE;
    }

    @ModifyConstant(method = "readNbt()Lnet/minecraft/nbt/CompoundNBT;", constant = @Constant(longValue = 2097152L))
    public long newSize(long value) {
        return Long.MAX_VALUE;
    }
}
