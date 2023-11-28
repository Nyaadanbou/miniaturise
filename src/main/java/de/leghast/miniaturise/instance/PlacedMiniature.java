package de.leghast.miniaturise.instance;

import org.bukkit.Location;
import org.bukkit.entity.BlockDisplay;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Transformation;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.ceil;

public class PlacedMiniature {

    private List<BlockDisplay> blockDisplays;
    private double blockSize;

    public PlacedMiniature(List<MiniatureBlock> blocks, Location origin){
        blockDisplays = new ArrayList<>();
        blockSize = blocks.get(0).getSize();

        for(MiniatureBlock mb : blocks) {
            BlockDisplay bd;
            bd = (BlockDisplay) origin.getWorld().spawnEntity(new Location(
                            origin.getWorld(),
                            mb.getX() + ceil(origin.getX()),
                            mb.getY() + ceil(origin.getY()),
                            mb.getZ() + ceil(origin.getZ())),
                    EntityType.BLOCK_DISPLAY);
            bd.setBlock(mb.getBlockData());
            Transformation transformation = bd.getTransformation();
            transformation.getScale().set(mb.getSize());
            bd.setTransformation(transformation);
            blockDisplays.add(bd);
        }
    }

    public PlacedMiniature(List<BlockDisplay> blockDisplays){
        this.blockDisplays = blockDisplays;
        if(!blockDisplays.isEmpty()){
            blockSize = blockDisplays.get(0).getTransformation().getScale().x;
        }
    }

    public void removePlacedMiniature(){
        for(BlockDisplay bd : blockDisplays){
            bd.remove();
        }
    }

}
