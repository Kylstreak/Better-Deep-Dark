# Better Deep Dark

**Better Deep Dark** is a Minecraft mod that expands the Deep Dark from a single biome with a scary mob into a **full endgame system** built around sound, pressure, and inevitability.

This mod is not about adding “stronger gear and bigger numbers.”  
It is about introducing a **new axis of gameplay** where sound, silence, and environmental awareness matter just as much as damage output.

The Deep Dark is no longer a place you visit once.  
It is a place that **pushes back**.

---

## Design Philosophy

- Sound is a mechanic, not flavor
- Silence is power, but never free
- The environment is hostile, adaptive, and systemic
- The player is not the first to encounter this threat
- The Warden is not the boss — it is a containment tool

This mod aims to feel:
- oppressive, not flashy
- dangerous, not unfair
- learnable, not brute-forceable

---

## Currently Implemented Features

### Key Altar
An ancient altar structure used to interact with sculk systems.

- Accepts items placed directly onto the altar
- Holds items temporarily before resolving their outcome
- Rejects invalid items after a delay
- Acts as the primary interface between the player and sculk infrastructure

---

### Sculk Conduit
A foundational block in Deep Dark infrastructure.

- Activated by sacrificing a **Warden’s Heart** at a Key Altar placed above it
- Once activated, conduits:
  - emit light
  - propagate activation to connected conduits
  - permanently change the surrounding environment
- Conduits form **networks**, not isolated blocks

> Development-only reset commands exist for testing  
> In normal gameplay, activation is intended to be permanent

---

### Warden’s Heart
A rare drop tied directly to high-risk gameplay.

- Beats audibly when carried
- Interacts with Key Altars and Conduits
- Required for progression into deeper systems

---

### Warden Weapons

#### Warden Sword (Partially Implemented)
**Wardens Cry**

- Damage dealt is accumulated across hits
- After reaching a threshold:
  - the next attack releases a **radial sonic shockwave**
  - nearby entities are violently displaced
- Wolves are excluded to preserve vanilla strategies
- Crouching cancels the ability
- Using the ability near a shrieker can **bypass cooldowns and immediately summon a Warden**

This is not a free AOE — it is a risk amplifier.

---

#### Warden Axe
**Sonic Blast**

- Damage dealt is accumulated
- On a **critical hit**, releases a focused sonic blast
- Knockback strength scales with damage accumulated
- Rewards precision, timing, and positioning

---

## Planned Features

### Portal Pylon
A massive structure found in ancient Deep Dark cities.

- Built atop enormous tower formations
- Requires an active Sculk Conduit beneath it
- Serves as the gateway to a new dimension
- Activation is loud, irreversible, and dangerous

---

## Warden Armor Set

Each piece introduces a **systemic advantage** rather than raw stats.

### Helmet — *Echolocation*
- Counteracts the Darkness effect
- Allows partial spatial awareness even in sensory-deprived environments

---

### Chestplate — *All Consuming*
- When the player takes damage:
  - 50% of the damage is absorbed
  - the absorbed energy is emitted as sound
- Sound strength scales with damage taken
- Can be toggled via keybind (default: `J`)

Power at the cost of attention.

---

### Leggings — *Abyssal Fortitude*
- Grants permanent Resistance II:
  - below Y=0
  - or while inside the Deep Dark dimension

---

### Boots — *Abyssal Veil*
- Allows silent movement:
  - no sound-triggering blocks
  - no sound-based entity detection
- Does **not** prevent:
  - sniff-based detection
  - sounds from combat or interaction

Silence is not invisibility.

---

## Warden Shield

### *Echo’s Ward*
- Blocks sound-based attacks
- Absorbed sound is redistributed into the environment
- Nearby shriekers and sensors may be triggered as a result
- Ability has a cooldown — cannot be held indefinitely

Defense that shifts danger rather than removing it.

---

## Warden Tools

### Depth’s Whisper
(Applies to hoe, pickaxe, shovel)

- Significantly reduces the distance at which block interactions can be heard
- Enables safer interaction near shriekers and sensors
- Still risky — mistakes compound quickly

---

## Enchantments

### Chasm’s Embrace (Boots)
- Increases safe fall distance
- Only active in the Deep Dark

---

### Sculk Synergy (Tools)
- Mining speed increases based on nearby sculk density
- Accepts:
  - sculk blocks
  - sculk veins
  - sensors
  - catalysts
- Stacks with Efficiency

---

### Echo of Catalysis (Sword)
- Killing mobs spreads sculk
- Functions without a catalyst present

Power that reshapes the world.

---

## Consumables

### Sculk Apple
- Grants all standard Golden Apple effects
- Adds **Whisper’s Embrace**:
  - temporarily allows actions without triggering sound-sensitive blocks or entities

A brief taste of true silence.

---

## New Dimension & Boss Encounter

### The Deep Dark Dimension
Accessed via the Portal Pylon.

- Hostile, oppressive, and reactive
- Built around verticality, sound pressure, and limited safety
- Not meant to be explored casually

---

### Dimension Boss — *The Convergence*

This is not a traditional boss.

- It is a **system**
- A centralized consciousness that feeds on sound
- The Warden was created to contain it — not defeat it

#### Arena Mechanics
- Massive core chamber
- Shrieker Chests embedded throughout the environment
- Sound feeds the boss and empowers the arena

#### Shrieker Chests
- Contain active shriekers
- Open and close dynamically
- Emit directional sonic pressure
- Must be disabled to weaken the boss
- Destroying one causes a massive retaliation

#### Boss Phases
1. **Awakening** — silence, pressure, inevitability
2. **Environmental Control** — sound pulses, movement suppression
3. **System Dismantling** — disabling shrieker infrastructure
4. **Escalation** — arena actively fights the player
5. **Final Exposure** — brief DPS window, overwhelming force

Victory does not destroy the Deep Dark.

It **disconnects it**.

---

## World Consequences

- The Deep Dark stops expanding
- Sculk begins retracting from ancient cities
- The world reflects the player’s actions over time

This is not a loot fight.  
It is a **world-altering event**.

---

## Modpack Compatibility

- Uses tags instead of hard-coded checks
- Modpack authors can:
  - add new sculk items
  - integrate custom systems via datapacks
- Designed to play well with other mods

---

## Final Notes

Better Deep Dark is designed for players who want:
- tension over power fantasy
- systems over gimmicks
- consequence over convenience

This mod assumes you are willing to learn.

The Deep Dark already did.
