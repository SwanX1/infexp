package com.nekomaster1000.infernalexp.items;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import com.nekomaster1000.infernalexp.capabilities.IWhipUpdate;
import com.nekomaster1000.infernalexp.capabilities.WhipUpdateCapability;
import com.nekomaster1000.infernalexp.network.IENetworkHandler;
import com.nekomaster1000.infernalexp.network.WhipReachPacket;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.Vanishable;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.UseAnim;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.phys.AABB;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;
import java.util.List;

public class WhipItem extends TieredItem implements Vanishable {

    private final float attackDamage;
    private final float attackSpeed;

    public WhipItem(Tier tier, float attackDamageIn, float attackSpeedIn, Item.Properties builderIn) {
        super(tier, builderIn);
        this.attackDamage = attackDamageIn + tier.getAttackDamageBonus();
        this.attackSpeed = attackSpeedIn;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
        tooltip.add(new TextComponent("\u00A76" + "Hold right click to charge, then release to strike!"));
    }

    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof Player) {
            Player playerEntity = (Player) entityLiving;

            setCharging(stack, false);

            int ticksSinceStart = this.getUseDuration(stack) - timeLeft;

            if (ticksSinceStart < 0 || timeLeft > 71985) {
                setTicksSinceAttack(stack, 0);
                return;
            } else {
                setAttacking(stack, true);
                setTicksSinceAttack(stack, 36);
            }

            playerEntity.getCommandSenderWorld().playSound(playerEntity, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F));
            playerEntity.awardStat(Stats.ITEM_USED.get(this));

            if (worldIn.isClientSide()) {
                handleExtendedReach(playerEntity);
            }
        }
    }

    @OnlyIn(Dist.CLIENT)
    private boolean handleExtendedReach(Player player) {

        // Change the value added here to adjust the reach of the charge attack of the whip, must also be changed in WhipReachPacket
        double reach = player.getAttribute(ForgeMod.REACH_DISTANCE.get()).getValue() + 1.0D;

        Vec3 eyePos = player.getEyePosition(1.0F);
        Vec3 lookVec = player.getLookAngle();
        Vec3 reachVec = eyePos.add(lookVec.multiply(reach, reach, reach));

        AABB playerBox = player.getBoundingBox().expandTowards(lookVec.scale(reach)).inflate(1.0D, 1.0D, 1.0D);
        EntityHitResult traceResult = ProjectileUtil.getEntityHitResult(player, eyePos, reachVec, playerBox, (target) -> !target.isSpectator() && target.showVehicleHealth(), reach * reach);

        if (traceResult != null) {
            double distance = eyePos.distanceToSqr(traceResult.getLocation());

            if (distance < reach * reach) {
                player.attackStrengthTicker = (int) player.getCurrentItemAttackStrengthDelay();
                IENetworkHandler.sendToServer(new WhipReachPacket(player.getUUID(), traceResult.getEntity().getId()));

                return true;
            }
        }

        return false;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }

    @Override
    public int getUseDuration(ItemStack stack) {
        return 72000;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level worldIn, Player playerIn, InteractionHand handIn) {
        ItemStack itemstack = playerIn.getItemInHand(handIn);
        playerIn.startUsingItem(handIn);
        return InteractionResultHolder.pass(itemstack);
    }

    @Override
    public void onUsingTick(ItemStack stack, LivingEntity player, int count) {
        if (getAttacking(stack)) {
            setTicksSinceAttack(stack, 0);
            setAttacking(stack, false);
        }

        setCharging(stack, true);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
        if ((getCharging(stack) && getTicksSinceAttack(stack) <= 30) || getAttacking(stack)) {
            setTicksSinceAttack(stack, getTicksSinceAttack(stack) + 1);
        }

        if (getTicksSinceAttack(stack) >= 60 || (!isSelected && entityIn instanceof Player && ((Player) entityIn).getOffhandItem() != stack)) {
            setTicksSinceAttack(stack, 0);
            setAttacking(stack, false);
            setCharging(stack, false);
        }
    }

    @Override
    public boolean canAttackBlock(BlockState state, Level worldIn, BlockPos pos, Player player) {
        return !player.isCreative();
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        super.hurtEnemy(stack, target, attacker);

        stack.hurtAndBreak(1, attacker, (entity) -> {
            entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level worldIn, BlockState state, BlockPos pos, LivingEntity entityLiving) {
        if (state.getDestroySpeed(worldIn, pos) != 0.0F) {
            stack.hurtAndBreak(2, entityLiving, (entity) -> {
                entity.broadcastBreakEvent(EquipmentSlot.MAINHAND);
            });
        }

        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        Material material = state.getMaterial();
        return material != Material.PLANT && material != Material.REPLACEABLE_PLANT && material != Material.CORAL && !state.is(BlockTags.LEAVES) && material != Material.VEGETABLE ? 1.0F : 1.5F;
    }

    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment) {
        return enchantment.category == (EnchantmentCategory.WEAPON);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack itemStack) {
        ImmutableMultimap.Builder<Attribute, AttributeModifier> attributeBuilder = ImmutableMultimap.builder();
        attributeBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", this.attackDamage, AttributeModifier.Operation.ADDITION));
        attributeBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", this.attackSpeed, AttributeModifier.Operation.ADDITION));
        Multimap<Attribute, AttributeModifier> attributes = attributeBuilder.build();
        return equipmentSlot == EquipmentSlot.MAINHAND ? attributes : super.getAttributeModifiers(equipmentSlot, itemStack);
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        if (WhipUpdateCapability.INSTANCE == null) return null;

        return WhipUpdateCapability.createProvider();
    }

    public void setTicksSinceAttack(ItemStack itemStack, int ticksSinceAttack) {
        WhipUpdateCapability.getWhipUpdate(itemStack).ifPresent(whipUpdate -> whipUpdate.setTicksSinceAttack(ticksSinceAttack));
    }

    public int getTicksSinceAttack(ItemStack itemStack) {
        IWhipUpdate whipUpdate = WhipUpdateCapability.getWhipUpdate(itemStack).orElse(null);
        return whipUpdate == null ? 0 : whipUpdate.getTicksSinceAttack();
    }

    public void setAttacking(ItemStack itemStack, boolean attacking) {
        WhipUpdateCapability.getWhipUpdate(itemStack).ifPresent(whipUpdate -> whipUpdate.setAttacking(attacking));
    }

    public boolean getAttacking(ItemStack itemStack) {
        IWhipUpdate whipUpdate = WhipUpdateCapability.getWhipUpdate(itemStack).orElse(null);
        return whipUpdate == null ? false : whipUpdate.getAttacking();
    }

    public void setCharging(ItemStack itemStack, boolean charging) {
        WhipUpdateCapability.getWhipUpdate(itemStack).ifPresent(whipUpdate -> whipUpdate.setCharging(charging));
    }

    public boolean getCharging(ItemStack itemStack) {
        IWhipUpdate whipUpdate = WhipUpdateCapability.getWhipUpdate(itemStack).orElse(null);
        return whipUpdate == null ? false : whipUpdate.getCharging();
    }
}
