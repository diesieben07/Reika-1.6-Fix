package de.take_weiland.mods.reikaupdatefix;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;

import static org.objectweb.asm.ClassWriter.COMPUTE_FRAMES;
import static org.objectweb.asm.Opcodes.*;

/**
 * @author diesieben07
 */
public final class Transformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] bytes) {
        if (bytes == null || !name.equals("Reika.DragonAPI.Auxiliary.Trackers.CommandableUpdateChecker$UpdateChecker")) {
            return bytes;
        }

        ClassReader cr = new ClassReader(bytes);
        ClassWriter cw = new ClassWriter(cr, COMPUTE_FRAMES);
        ClassVisitor cv = new VersionCheckDisabler(cw);
        cr.accept(cv, 0);
        return cw.toByteArray();
    }

    private static final class VersionCheckDisabler extends ClassVisitor {

        VersionCheckDisabler(ClassVisitor cv) {
            super(ASM4, cv);
        }

        @Override
        public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
            super.visit(V1_7, access, name, signature, superName, interfaces);
        }

        @Override
        public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
            MethodVisitor mv = super.visitMethod(access, name, desc, signature, exceptions);

            if (name.equals("getLatestVersion")) {
                if (mv != null) {
                    mv.visitCode();
                    mv.visitInsn(ACONST_NULL);
                    mv.visitInsn(ARETURN);
                    mv.visitMaxs(0, 0);
                    mv.visitEnd();
                }
                return null;
            }

            return mv;
        }
    }


}
