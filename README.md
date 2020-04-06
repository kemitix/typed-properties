# TypedProperties

![Sonatype Nexus (Release)](https://img.shields.io/nexus/r/https/oss.sonatype.org/net.kemitix/typed-properties.svg?style=for-the-badge)
![Maven Central](https://img.shields.io/maven-central/v/net.kemitix/typed-properties.svg?style=for-the-badge)

![Java 8](https://img.shields.io/badge/Java-8-success "Compatible with Java 8")
![Java 11](https://img.shields.io/badge/Java-11-success "Compatible with Java 11")
![Java 13](https://img.shields.io/badge/Java-13-success "Compatible with Java 13")
![Java 14](https://img.shields.io/badge/Java-14-important "Unresolved Issues with Java 14")

A strongly-typed, immutable, simple alternative to `Properties` or, sanity-save-you, `Map<String, Object>`.

## Usage

```java
import net.kemitix.properties.typed.TypedProperties;
import net.kemitix.properties.typed.TypedProperty;

class Usage {
  public static void main(String[] args){
    // Create a new instance
    TypedProperties properties = TypedProperties.create();

    // Set a value - creates a new instance - immutability
    // parameters: key, value
    // - key is a class/interface, that extends TypedProperty<T>,
    //   where T is the type of the value.
    // - value is of type T
    TypedProperties updated = properties.with(Name.class, "Name");

    // Retrieve a value
    // parameters: key, value-type
    // - key is same as above
    // - value-type is the type of the value - must match key
    Optional<String> result = updated.find(Name.class, String.class);
  }
}
public interface Name
  extends TypedProperty<String> {}
```

```java

```