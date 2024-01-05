package net.technicpack.blightcore.coremod;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import net.technicpack.blightcore.coremod.asm.AWDisableChiselCompatEditor;
import net.technicpack.blightcore.coremod.asm.BlockOceanNodesEditor;
import net.technicpack.blightcore.coremod.asm.BlockTaintedBotaniaFlowerEditor;
import net.technicpack.blightcore.coremod.asm.BlockTaintedSandEditor;
import net.technicpack.blightcore.coremod.asm.DeathsReduceRepEditor;
import net.technicpack.blightcore.coremod.asm.IAsmEditor;

public class BlightCoreCoremod implements IFMLLoadingPlugin {
	public static List<IAsmEditor> editors = new ArrayList<IAsmEditor>(2);

	@Override
	public String[] getASMTransformerClass() {
		return new String[] { "net.technicpack.blightcore.coremod.BlightCoreClassTransformer" };
	}

	@Override
	public String getModContainerClass() { return "net.technicpack.blightcore.BlightCore"; }

	@Override
	public String getSetupClass() { return null; }

	@Override
	public void injectData(Map<String, Object> data) {
		boolean isObfuscated = (Boolean) data.get("runtimeDeobfuscationEnabled");

		editors.clear();
		editors.add(new BlockOceanNodesEditor());
		editors.add(new BlockTaintedSandEditor(isObfuscated));
		editors.add(new BlockTaintedBotaniaFlowerEditor());
		editors.add(new AWDisableChiselCompatEditor());
	}

	@Override
	public String getAccessTransformerClass() {
		return "net.technicpack.blightcore.coremod.BlightCoreAccessTransformer";
	}
}
