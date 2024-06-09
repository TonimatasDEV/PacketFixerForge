package dev.tonimatas.packetfixer.mixins;

import dev.tonimatas.packetfixer.util.Config;
import net.minecraft.network.Varint21FrameDecoder;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = Varint21FrameDecoder.class, priority = 9999)
public abstract class Varint21FrameDecoderMixin {
    @ModifyConstant(method = "decode", constant = @Constant(intValue = 3))
    private int newSize(int value) {
        return Config.getVarInt21Size();
    }
}