package de.take_weiland.mods.reika16fix;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

import java.util.Map;

/**
 * @author diesieben07
 */
@IFMLLoadingPlugin.SortingIndex(1001)
@IFMLLoadingPlugin.MCVersion("1.6.4")
@IFMLLoadingPlugin.TransformerExclusions("de.take_weiland.mods.reika16fix.")
public final class ReikaFix implements IFMLLoadingPlugin {
    @Override
    public String[] getASMTransformerClass() {
        return new String[] {
                "de.take_weiland.mods.reika16fix.Transformer"
        };
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> map) {

    }
}
