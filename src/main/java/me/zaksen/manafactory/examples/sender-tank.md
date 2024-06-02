# Sender and tank examples

## Sender

```java
public class TestManaSenderEntity extends AbstractManaBlockEntity {

    private BlockPos toSend;

    private final int maxTickSend = 40;
    private int tickSend = 0;

    public TestManaSenderEntity(BlockPos pos, BlockState state) {
        super(MFBlockEntities.TEST_MANA_SENDER, pos, state, 512);
        setCapacity(512);
    }

    @Override
    public void tick(World world, BlockPos pos, BlockState state) {
        if(world.isClient()) {
            return;
        }

        tickSend++;
        if(tickSend >= maxTickSend) {
            tickSend = 0;

            if(toSend == null) {
                return;
            }

            if(world.getBlockEntity(toSend) instanceof AbstractManaBlockEntity manaContainer) {
                send(32, manaContainer);
            }
        }
    }

    protected void setToSend(BlockPos pos) {
        toSend = pos;
        markDirty();
    }

    @Override
    public ActionResult onConfiguratorUsed(ItemUsageContext context) {
        ItemStack usedStack = context.getStack();
        NbtCompound stackNbt = usedStack.getNbt();

        if(stackNbt == null || stackNbt.isEmpty()) {
            System.out.println("Mana focus is empty!");
            return ActionResult.PASS;
        }

        int posX = stackNbt.getInt("pos_x");
        int posY = stackNbt.getInt("pos_y");
        int posZ = stackNbt.getInt("pos_z");

        setToSend(new BlockPos(posX, posY, posZ));

        return ActionResult.SUCCESS;
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if(toSend != null) {
            nbt.putInt("send_pos_x", toSend.getX());
            nbt.putInt("send_pos_y", toSend.getY());
            nbt.putInt("send_pos_z", toSend.getZ());
        }
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if(nbt.contains("send_pos_x")) {
            int posX = nbt.getInt("send_pos_x");
            int posY = nbt.getInt("send_pos_y");
            int posZ = nbt.getInt("send_pos_z");
            toSend = new BlockPos(posX, posY, posZ);
        }
    }
}
```

## Tank

```java
public class TestManaTankEntity extends AbstractManaBlockEntity {
    public TestManaTankEntity(BlockPos pos, BlockState state, int maxCapacity) {
        super(MFBlockEntities.TEST_MANA_TANK, pos, state, maxCapacity);
    }

    @Override
    public ActionResult onConfiguratorUsed(ItemUsageContext context) {
        ItemStack usedStack = context.getStack();
        NbtCompound stackNbt = usedStack.getNbt();

        if(stackNbt == null) {
            stackNbt = new NbtCompound();
        }

        stackNbt.putInt("pos_x", pos.getX());
        stackNbt.putInt("pos_y", pos.getY());
        stackNbt.putInt("pos_z", pos.getZ());

        usedStack.setNbt(stackNbt);

        return ActionResult.SUCCESS;
    }
}
```