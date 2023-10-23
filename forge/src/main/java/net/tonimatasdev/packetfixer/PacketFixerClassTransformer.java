package net.tonimatasdev.packetfixer;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.Opcodes;
import scala.tools.asm.ClassReader;
import scala.tools.asm.ClassVisitor;
import scala.tools.asm.ClassWriter;
import scala.tools.asm.MethodVisitor;

public class PacketFixerClassTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        PacketFixer.LOGGER.info("Patching: net.minecraft.network.NettyCompressionDecoder");
        if (transformedName.equals("net.minecraft.network.NettyCompressionDecoder")) {
            return patchNettyCompressionDecoder(basicClass);
        }
        PacketFixer.LOGGER.info("Patched");

        PacketFixer.LOGGER.info("Patching: net.minecraft.network.play.client.CPacketCustomPayload");
        if (transformedName.equals("net.minecraft.network.play.client.CPacketCustomPayload")) {
            return patchCPacketCustomPayload(basicClass);
        }
        PacketFixer.LOGGER.info("Patched");

        PacketFixer.LOGGER.info("Patching: net.minecraft.network.play.server.SPacketCustomPayload");
        if (transformedName.equals("net.minecraft.network.play.server.SPacketCustomPayload")) {
            return patchSPacketCustomPayload(basicClass);
        }
        PacketFixer.LOGGER.info("Patched");

        return basicClass;
    }

    private byte[] patchNettyCompressionDecoder(byte[] clazz) {
        ClassReader classReader = new ClassReader(clazz);
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);

                if (name.equals("decoder")) {
                    return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
                        @Override
                        public void visitLdcInsn(Object cst) {
                            if (cst instanceof Integer && (int) cst == 2097152) {
                                super.visitLdcInsn(Integer.MAX_VALUE);
                            } else {
                                super.visitLdcInsn(cst);
                            }
                        }
                    };
                }

                return methodVisitor;
            }
        };

        classReader.accept(classVisitor, 0);
        return classWriter.toByteArray();
    }

    private byte[] patchCPacketCustomPayload(byte[] clazz) {
        ClassReader classReader = new ClassReader(clazz);
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);

                if (name.equals("<init>")) {
                    return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
                        @Override
                        public void visitLdcInsn(Object cst) {
                            if (cst instanceof Integer && (int) cst == 32767) {
                                super.visitLdcInsn(Integer.MAX_VALUE);
                            } else {
                                super.visitLdcInsn(cst);
                            }
                        }
                    };
                }

                if (name.equals("readPacketData")) {
                    return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
                        @Override
                        public void visitLdcInsn(Object cst) {
                            if (cst instanceof Integer && (int) cst == 32767) {
                                super.visitLdcInsn(Integer.MAX_VALUE);
                            } else {
                                super.visitLdcInsn(cst);
                            }
                        }
                    };
                }

                return methodVisitor;
            }
        };

        classReader.accept(classVisitor, 0);
        return classWriter.toByteArray();
    }

    private byte[] patchSPacketCustomPayload(byte[] clazz) {
        ClassReader classReader = new ClassReader(clazz);
        ClassWriter classWriter = new ClassWriter(classReader, ClassWriter.COMPUTE_MAXS);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5, classWriter) {
            @Override
            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                MethodVisitor methodVisitor = super.visitMethod(access, name, desc, signature, exceptions);

                if (name.equals("<init>")) {
                    return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
                        @Override
                        public void visitLdcInsn(Object cst) {
                            if (cst instanceof Integer && (int) cst == 1048576) {
                                super.visitLdcInsn(Integer.MAX_VALUE);
                            } else {
                                super.visitLdcInsn(cst);
                            }
                        }
                    };
                }

                if (name.equals("readPacketData")) {
                    return new MethodVisitor(Opcodes.ASM5, methodVisitor) {
                        @Override
                        public void visitLdcInsn(Object cst) {
                            if (cst instanceof Integer && (int) cst == 1048576) {
                                super.visitLdcInsn(Integer.MAX_VALUE);
                            } else {
                                super.visitLdcInsn(cst);
                            }
                        }
                    };
                }

                return methodVisitor;
            }
        };

        classReader.accept(classVisitor, 0);
        return classWriter.toByteArray();
    }
}
