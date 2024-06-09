package dev.tonimatas.packetfixer.mixins;

import dev.tonimatas.packetfixer.util.Config;
import dev.tonimatas.packetfixer.util.Hooks;
import dev.tonimatas.packetfixer.util.MixinCheck;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinConfigPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        Config.runProperties();
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @SuppressWarnings("UnreachableCode")
    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean krypton = Hooks.isModLoaded("krypton") || Hooks.isModLoaded("pluto");

        if (MixinCheck.with(mixinClassName, "Varint21FrameDecoderMixin") || MixinCheck.with(mixinClassName, "Varint21LengthFieldPrependerMixin")) {
            return !krypton;
        }

        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

    }

    @Override
    public List<String> getMixins() {
        return null;
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

    }
}
