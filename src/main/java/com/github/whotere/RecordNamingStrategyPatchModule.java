package com.github.whotere;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;
import com.fasterxml.jackson.databind.deser.ValueInstantiator;
import com.fasterxml.jackson.databind.deser.ValueInstantiators;
import com.fasterxml.jackson.databind.deser.std.StdValueInstantiator;
import com.fasterxml.jackson.databind.introspect.BeanPropertyDefinition;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class RecordNamingStrategyPatchModule extends SimpleModule {

    @Override
    public void setupModule(Module.SetupContext context) {
        context.addValueInstantiators(new ValueInstantiatorsModifier());
        super.setupModule(context);
    }

    private static class ValueInstantiatorsModifier extends ValueInstantiators.Base {
        @Override
        public ValueInstantiator findValueInstantiator(
                DeserializationConfig config, BeanDescription beanDesc, ValueInstantiator defaultInstantiator
        ) {
            if (!beanDesc.getBeanClass().isRecord()
                    || !(defaultInstantiator instanceof StdValueInstantiator)
                    || !defaultInstantiator.canCreateFromObjectWith()) {
                return defaultInstantiator;
            }
            Map<String, BeanPropertyDefinition> map = beanDesc.findProperties()
                    .stream()
                    .collect(Collectors.toMap(BeanPropertyDefinition::getInternalName, Function.identity()));

            SettableBeanProperty[] renamedConstructorArgs = Arrays.stream(defaultInstantiator.getFromObjectArguments(config))
                    .map(p -> {
                        BeanPropertyDefinition prop = map.get(p.getName());
                        return prop != null ? p.withName(prop.getFullName()) : p;
                    })
                    .toArray(SettableBeanProperty[]::new);

            return new PatchedValueInstantiator((StdValueInstantiator) defaultInstantiator, renamedConstructorArgs);
        }
    }

    private static class PatchedValueInstantiator extends StdValueInstantiator {

        protected PatchedValueInstantiator(StdValueInstantiator src, SettableBeanProperty[] constructorArguments) {
            super(src);
            _constructorArguments = constructorArguments;
        }
    }
}
