package com.ks12.better_deep_dark.registry;

import com.ks12.better_deep_dark.BetterDeepDark;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class RegistryEntryConstructor<V, T extends V> {
    private final List<Consumer<RegistryEntryConstructor<V, T>>> clientRegistrationParams = new ArrayList<>();
    public final String name;
    public final Registry<V> registry;
    public final T entry;
    public Identifier id;

    private boolean followsStandardRegistration = true;
    private Consumer<RegistryEntryConstructor<V, T>> registrationMethod = null;


    public RegistryEntryConstructor(String name, @Nullable Registry<V> registry, T entry) {
        this.name = name;
        this.id = new Identifier(BetterDeepDark.MOD_ID, name);
        this.registry = registry;
        this.entry = entry;
    }

    public RegistryEntryConstructor(Identifier id, @Nullable Registry<V> registry, T entry) {
        this.id = id;
        this.name = id.getPath();
        this.registry = registry;
        this.entry = entry;
    }

    public RegistryEntryConstructor<V, T> addClientRegistrationParameter(Consumer<RegistryEntryConstructor<V, T>> param) {
        clientRegistrationParams.add(param);
        return this;
    }

    public RegistryEntryConstructor<V, T> setRegistrationMethod(Consumer<RegistryEntryConstructor<V, T>> param) {
        followsStandardRegistration = false;
        registrationMethod = param;
        return this;
    }

    public void completeRegistration() {
        if (followsStandardRegistration && this.registry != null) Registry.register(this.registry, this.id, this.entry);
        else if (!followsStandardRegistration) registrationMethod.accept(this);
        else BetterDeepDark.LOGGER.log(Level.ERROR, "Registry left null but no custom constructor was specified for entry: \"%s\". Entry will not be registered".formatted(this.name));
    }

    public void completeClientRegistration() {
        clientRegistrationParams.forEach(param-> param.accept(this));
    }

    public T build() {
        return entry;
    }
}
