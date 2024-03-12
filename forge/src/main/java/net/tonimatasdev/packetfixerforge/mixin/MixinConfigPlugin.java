package net.tonimatasdev.packetfixerforge.mixin;

import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.logging.log4j.LogManager;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

import java.util.List;
import java.util.Set;

public class MixinConfigPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) {
        System.getProperties().setProperty("forge.disablePacketCompressionDebug", "true");
    }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        boolean krypton = FMLLoader.getLoadingModList().getModFileById("krypton") != null || FMLLoader.getLoadingModList().getModFileById("pluto") != null;
        boolean randomPatches = FMLLoader.getLoadingModList().getModFileById("randompatches") != null;
        
        
        if (mixinClassName.equalsIgnoreCase("net.tonimatasdev.packetfixerforge.mixin.NettyVarint21FrameDecoder") || mixinClassName.equalsIgnoreCase("net.tonimatasdev.packetfixerforge.mixin.NettyVarint21FrameEncoder")) {
            if (krypton) {
                LogManager.getLogger().warn("For can't fit X into 3 error fix. Delete Krypton.");
                return false;
            }
        }

        if (mixinClassName.equalsIgnoreCase("net.tonimatasdev.packetfixerforge.mixin.CCustomPayloadPacketMixin")) return !randomPatches;
        
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