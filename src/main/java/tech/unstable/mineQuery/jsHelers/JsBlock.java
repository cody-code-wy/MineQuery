package tech.unstable.mineQuery.jsHelers;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class JsBlock{

  final public Block _block;
  final public IBlockState _state;
  final public World _world;
  final public BlockPos _pos;

  final public int x;
  final public int y;
  final public int z;

  final public int meta;

  final public String registryName;

  public JsBlock(IBlockState state, World world, BlockPos pos){
    this(state.getBlock(), state, world, pos);
  }

  public JsBlock(Block block, IBlockState state, World world, BlockPos pos){
    _block = block;
    _state = state;
    _world = world;
    _pos = pos;

    x = pos.getX();
    y = pos.getY();
    z = pos.getZ();

    meta = block.getMetaFromState(state);

    registryName = block.getRegistryName().toString();
  }

  public void destroy(boolean drop){
    _world.destroyBlock(_pos, drop);
  }

  public String toString(){
    return "[" + x + "," + y + "," + z + "] @" + registryName + ":" + meta;
  }

}
