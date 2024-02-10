package net.tonimatasdev.packetfixerfabric.mixin;

import net.minecraft.network.packet.s2c.login.LoginQueryRequestS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(value = LoginQueryRequestS2CPacket.class, priority = 9999)
public class LoginQueryRequestS2CPacketMixin {
    @ModifyConstant(method = "read", constant = @Constant(intValue = 1048576))
    private int newSize(int value) {
        return Integer.MAX_VALUE;
    }
}
