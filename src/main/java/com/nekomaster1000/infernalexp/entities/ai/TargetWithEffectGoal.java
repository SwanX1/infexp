package com.nekomaster1000.infernalexp.entities.ai;

import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.effect.MobEffect;

import javax.annotation.Nullable;
import java.util.function.Predicate;

/**
 * This goal allows a Mob to target a Player who has a potion effect. The constructor is similar to the basic
 * TargetNearestAttackable goal, but adds the input of an integer representing the ID number of the potion effect that
 * should be targeted.
 */
public class TargetWithEffectGoal extends NearestAttackableTargetGoal {
    private final MobEffect effect;
    private final Class invalidTarget;

    public TargetWithEffectGoal(Mob goalOwnerIn, Class targetClassIn, boolean checkSight, MobEffect effect, @Nullable Class invalidTargetClassIn) {
        super(goalOwnerIn, targetClassIn, checkSight);
        this.effect = effect;
        this.invalidTarget = invalidTargetClassIn;
    }

    public TargetWithEffectGoal(Mob goalOwnerIn, Class targetClassIn, boolean checkSight, boolean nearbyOnlyIn, MobEffect effect, @Nullable Class invalidTargetClassIn) {
        super(goalOwnerIn, targetClassIn, checkSight, nearbyOnlyIn);
        this.effect = effect;
		this.invalidTarget = invalidTargetClassIn;
    }

    public TargetWithEffectGoal(Mob goalOwnerIn, Class targetClassIn, int targetChanceIn, boolean checkSight, boolean nearbyOnlyIn, Predicate targetPredicate, MobEffect effect, @Nullable Class invalidTargetClassIn) {
        super(goalOwnerIn, targetClassIn, targetChanceIn, checkSight, nearbyOnlyIn, targetPredicate);
        this.effect = effect;
		this.invalidTarget = invalidTargetClassIn;
    }

    @Override
    protected boolean canAttack(@Nullable LivingEntity potentialTarget, TargetingConditions targetPredicate) {
    	if (this.invalidTarget != null && potentialTarget != null && potentialTarget.getClass() == this.invalidTarget) {
    		return false;
		}
        return super.canAttack(potentialTarget, targetPredicate) && potentialTarget.hasEffect(this.effect);
    }

    @Override
    protected void findTarget() {
        super.findTarget();
        if(!canAttack(this.target, this.targetConditions)) {
            this.target = null;
        }
    }
}
