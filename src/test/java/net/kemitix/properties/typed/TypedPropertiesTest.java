package net.kemitix.properties.typed;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TypedPropertiesTest {

    @Test
    @DisplayName("Can set and then find a value")
    public void canSetAndFindValue() {
        //given
        final TypedProperties typedProperties =
                TypedProperties.create()
                        .with(Name.class, "Name");
        //when
        final Optional<String> result =
                typedProperties.find(Name.class, String.class);
        //then
        assertThat(result).contains("Name");
    }

    @Test
    @DisplayName("Find a value not set is empty")
    public void findMissingIsEmpty() {
        //given
        final TypedProperties typedProperties = TypedProperties.create();
        //when
        final Optional<String> result =
                typedProperties.find(Name.class, String.class);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("After replacing a value, find returns the new value")
    public void replaceValueFindIsNewValue() {
        //given
        final TypedProperties typedProperties =
                TypedProperties.create()
                        .with(Name.class, "Original Name")
                        .with(Name.class, "Updated Name");
        //when
        final Optional<String> result =
                typedProperties.find(Name.class, String.class);
        //then
        assertThat(result).contains("Updated Name");
    }

    @Test
    @DisplayName("Immutable - setting value creates new instance")
    public void immutableCreatesNewInstanceOnSet() {
        //given
        final TypedProperties original = TypedProperties.create();
        //when
        final TypedProperties updated = original.with(Name.class, "Name");
        //then
        assertThat(updated).isNotSameAs(original);
    }

    @Test
    @DisplayName("Immutable - original instance lacks new value")
    public void immutableOriginalInstanceUnmodified() {
        //given
        final TypedProperties original = TypedProperties.create();
        final TypedProperties updated = original.with(Name.class, "Name");
        //when
        final Optional<String> result = original.find(Name.class, String.class);
        //then
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Multiple values of same underlying type are unique")
    public void multipleValuesOfSameBaseTypeAreSafe() {
        //given
        final TypedProperties typedProperties =
                TypedProperties.create()
                        .with(Name.class, "Name")
                        .with(Email.class, "name@example.org");
        //when
        final Optional<String> name =
                typedProperties.find(Name.class, String.class);
        final Optional<String> email =
                typedProperties.find(Email.class, String.class);
        //then
        assertThat(name).contains("Name");
        assertThat(email).contains("name@example.org");
    }

//    @Test
//    @DisplayName("Attempt to find a value by wrong type is a compilation error")
//    public void findWithWrongType() {
//        TypedProperties.create()
//                .set(Name.class, "Original Name")
//                .find(Name.class, Integer.class);
//    }

//    @Test
//    @DisplayName("Attempt to set a value by wrong type is a compilation error")
//    public void setWithWrongType() {
//        TypedProperties.create()
//                .set(Name.class, 42);
//    }

    private interface Name
            extends TypedProperty<String> {
    }

    private interface Email
            extends TypedProperty<String> {
    }
}