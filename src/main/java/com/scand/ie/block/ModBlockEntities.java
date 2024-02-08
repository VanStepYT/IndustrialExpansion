package com.scand.ie.block;

import com.scand.ie.IEMod;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IEMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<QuantumQuarryBlockEntity>> QUANTUM_QUARRY =
            BLOCK_ENTITIES.register("quantum_quarry", ()->
                    BlockEntityType.Builder.of(QuantumQuarryBlockEntity::new, ModBlocks.QUANTUM_QUARRY.get())
                            .build(null));


    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }

}
