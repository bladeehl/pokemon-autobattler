package com.github.bladeehl.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.NonNull;

import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;

@Entity
@DiscriminatorValue("Fire")
@Data
@NoArgsConstructor
@SuperBuilder
public class FirePokemon extends Pokemon {
    @Nullable Integer fireResistance;
    @Nullable Integer firePower;

    @Override
    public int specialAttack(@NonNull final Pokemon target) {
        val hpBefore = target.getHealth();
        target.takeDamage(firePower + 20);

        return hpBefore - target.getHealth();
    }

    @Override
    public void defensiveAbility() {
        fireResistance += 20;
    }

    @Override
    public int attack(@NonNull final Pokemon target) {
        val hpBefore = target.getHealth();
        target.takeDamage(getDamage());

        return hpBefore - target.getHealth();
    }

    @Override
    public void defend() {
        setDamage(getDamage() - fireResistance / 3);
    }

    @Override
    public void evolve() {
        setDamage((int) (getDamage() * 1.5));
    }

    @Override
    public int ability() {
        setDamage(getDamage() + fireResistance / 5);
        return 0;
    }

    @Override
    public String toString() {
        return "Fire  | %-10s | HP:%-4d | DMG:%-3d | FireRes:%-3d | FirePwr:%-3d"
            .formatted(getName(),
                getHealth(),
                getDamage(),
                fireResistance,
                firePower);
    }
}
