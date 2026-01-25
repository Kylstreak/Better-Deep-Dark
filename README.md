# Better Deep Dark

**Better Deep Dark** is a Minecraft mod that expands the Deep Dark from a single biome with a single terrifying mob into a **fully realized endgame ecosystem** built around sound, pressure, inevitability, and consequence.

This mod does not aim to simply add stronger gear.  
It introduces **new systems, new entities, and a new dimension** where sound is currency, silence is power, and mistakes compound rapidly.

The Deep Dark is no longer a place you visit once.  
It is a place that **remembers you**.

---

## Core Design Philosophy

- Sound is a mechanic, not flavor
- Silence is powerful but never free
- The environment is hostile, adaptive, and systemic
- The Warden is not the boss — it is a containment mechanism
- The true threat is older, deeper, and patient

This mod is designed to feel:
- oppressive rather than flashy
- dangerous rather than unfair
- learnable rather than brute-forceable

---

## Currently Implemented Features

### Key Altar
An ancient sculk-bound altar used to interface with Deep Dark systems.

- Items can be placed directly onto the altar
- Items are held for a fixed duration before resolving
- Invalid items are rejected and ejected back into the world
- Serves as the primary interaction point for sculk infrastructure

---

### Sculk Conduit
The backbone of Deep Dark progression.

- Activated by sacrificing a **Warden’s Heart** at a Key Altar placed above it
- Once activated:
  - emits light
  - permanently alters the surrounding area
  - propagates activation to connected conduits
- Conduits function as **networks**, not isolated blocks

> Development-only reset commands exist  
> In intended gameplay, conduit activation is permanent

---

### Warden’s Heart
A rare and ominous drop.

- Beats audibly when carried
- Reacts to Deep Dark structures
- Required to activate conduits and progress deeper
- Acts as a narrative and mechanical key

---

## Weapons

### Warden Sword (Partially Implemented)
**Wardens Cry**

- Tracks cumulative damage dealt
- After reaching a threshold:
  - the next hit releases a **radial sonic shockwave**
  - nearby entities are violently displaced
- Wolves are excluded to preserve vanilla strategies
- Can be canceled by crouching
- Using the ability near a shrieker can:
  - bypass cooldowns
  - immediately summon a Warden

This ability is intentionally dangerous.  
Power invites attention.

---

### Warden Axe
**Sonic Blast**

- Tracks cumulative damage dealt
- On a **critical hit**, releases a focused sonic blast
- Knockback scales with stored damage
- Designed for precision rather than crowd control

---

## Armor Set — Warden Gear

Each piece provides a **systemic advantage**, not raw dominance.

### Helmet — *Echolocation*
- Counteracts the Darkness effect
- Allows limited spatial awareness in sensory-deprived areas

---

### Chestplate — *All Consuming*
- When the player takes damage:
  - 50% is absorbed
  - absorbed damage is emitted as sound
- Sound strength scales with damage taken
- Ability can be toggled via keybind (default: `J`)

Defense that shifts risk rather than removing it.

---

### Leggings — *Abyssal Fortitude*
- Grants permanent Resistance II:
  - below Y = 0
  - or while in the Deep Dark dimension

---

### Boots — *Abyssal Veil*
- Allows silent movement:
  - no triggering of sound-sensitive blocks
  - no sound-based entity detection
- Does **not** prevent:
  - sniff-based detection
  - combat or interaction sounds

Silence is not invisibility.

---

## Shield

### Warden Shield — *Echo’s Ward*
- Blocks sound-based attacks
- Absorbed sound is redistributed into the environment
- Can trigger shriekers and sensors nearby
- Has a cooldown — cannot be held indefinitely

Defense that relocates danger instead of erasing it.

---

## Tools

### Warden Tools (Hoe, Pickaxe, Shovel)
**Depth’s Whisper**

- Greatly reduces the audible range of block interactions
- Enables safer navigation near shriekers and sensors
- Mistakes still escalate rapidly

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
  - sculk sensors
  - sculk catalysts
- Stacks with Efficiency

---

### Echo of Catalysis (Sword)
- Killing mobs spreads sculk
- Works without a catalyst present

Power that reshapes the environment.

---

## Consumables

### Sculk Apple
- Grants all standard Golden Apple effects
- Adds **Whisper’s Embrace**:
  - temporarily allows actions without alerting sound-sensitive systems

A brief taste of true silence.

---

## New Mobs

### Corrupted Villager
A warped remnant of Deep Dark exposure.

- Spawns exclusively in **Mirror Lost Cities**
- Emits sudden screams when detecting players
- Actively follows players at a distance
- Interacting opens a trade interface:
  - only one trade exists
  - sculk for sculk
  - trade is permanently disabled
- Profession text is fully obfuscated

The villager is not broken.  
It is **waiting**.

---

### Additional Sculk Entities (Planned / In Progress)
- Environmental sculk constructs
- Sound-reactive entities tied to the new dimension
- Non-hostile entities that still create danger through noise

---

## Portal Pylon

A massive structure found in Deep Dark megacities.

- Built atop enormous ancient towers
- Requires an active Sculk Conduit beneath it
- Activation is loud, irreversible, and dangerous
- Serves as the gateway to a new dimension

---

## New Dimension — The Deep Dark Beyond

A hostile realm formed entirely around sound pressure and containment failure.

- Vertical, claustrophobic, and reactive
- Environmental hazards replace traditional terrain danger
- Silence becomes a limited resource

This is not a place for exploration.  
It is a place for **resolution**.

---

## Dimension Boss — *The Convergence*

The true source of the Deep Dark.

- A centralized, sentient sound-based entity
- Feeds on noise and disturbance
- The Warden exists to suppress it — not defeat it

---

### Boss Arena Mechanics

- Massive core chamber
- Embedded **Shrieker Chests** throughout the environment
- Sound strengthens the boss and destabilizes the arena

---

### Shrieker Chests

- Contain active shriekers
- Open and close dynamically
- Emit directional sonic pressure
- Must be disabled to weaken the boss
- Destroying one causes violent retaliation

---

### Boss Phases

1. **Dormant Pressure**  
   Silence dominates. The arena watches.

2. **Environmental Control**  
   Sonic pulses, movement denial, sound traps.

3. **Infrastructure Dismantling**  
   Players disable shrieker systems under pressure.

4. **Escalation**  
   Arena itself becomes hostile.

5. **Final Exposure**  
   A brief window where the core can be damaged.

Victory does not destroy the Deep Dark.  
It **disconnects it**.

---

## World Consequences

- Deep Dark expansion halts
- Sculk slowly retracts from ancient cities
- The world permanently reflects the player’s actions

This is not a loot fight.  
It is a **world-altering event**.

---

## Modpack Compatibility

- Uses tags instead of hard-coded checks
- Modpack authors can:
  - add new sculk items
  - extend systems via datapacks
- Designed for deep integration, not isolation

---

## Final Notes

Better Deep Dark is designed for players who want:
- tension over power fantasy
- systems over gimmicks
- consequence over convenience

The Deep Dark was never empty.

It was waiting.
