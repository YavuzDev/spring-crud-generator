package com.generator.property;

import com.generator.file.CrudTree;
import com.generator.file.MavenFile;
import org.reflections.Reflections;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Path;
import java.util.Properties;

public class PropertyManager {

    public void readProperties(Path file, CrudTree crudTree, MavenFile mavenFile) throws IOException {
        var properties = new Properties();
        properties.load(new FileInputStream(file.toFile()));

        var reflections = new Reflections("com.generator.property.properties");

        var subTypes = reflections.getSubTypesOf(Property.class);
        subTypes.forEach(c -> {
            try {
                var propertyClass = (Property) c.getDeclaredConstructor().newInstance();
                propertyClass.apply(crudTree, mavenFile, properties.getProperty(propertyClass.property()));
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                e.printStackTrace();
            }
        });
    }
}
