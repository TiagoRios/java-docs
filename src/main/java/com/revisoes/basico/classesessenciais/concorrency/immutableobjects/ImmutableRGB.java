package com.revisoes.basico.classesessenciais.concorrency.immutableobjects;

/**
 * <h2>A Strategy for Defining Immutable Objects</h2>
 * <p>
 * The following rules define a simple strategy for creating immutable objects.
 * Not all classes documented as "immutable" follow these rules. This does not
 * necessarily mean the creators of these classes were sloppy — they may have
 * good reason for believing that instances of their classes never change after
 * construction. However, such strategies require sophisticated analysis and are
 * not for beginners.
 * </p>
 * 
 * <ol>
 * <li>
 * Don't provide "setter" methods — methods that modify fields or objects
 * referred to by fields.
 * <li>
 * Make all fields final and private.
 * <li>
 * Don't allow subclasses to override methods. The simplest way to do this is to
 * declare the class as final. A more sophisticated approach is to make the
 * constructor private and construct instances in factory methods.
 * <li>
 * If the instance fields include references to mutable objects, don't allow
 * those objects to be changed:
 * <ul>
 * <li>
 * Don't provide methods that modify the mutable objects.
 * <li>
 * Don't share references to the mutable objects. Never store references to
 * external, mutable objects passed to the constructor; if necessary, create
 * copies, and store references to the copies. Similarly, create copies of your
 * internal mutable objects when necessary to avoid returning the originals in
 * your methods.
 * </ul>
 * </ol>
 * 
 * Applying this strategy to SynchronizedRGB results in the following steps:
 * 
 * <ol>
 * <li>
 * There are two setter methods in this class. The first one, set, arbitrarily
 * transforms the object, and has no place in an immutable version of the class.
 * The second one, invert, can be adapted by having it create a new object
 * instead of modifying the existing one.
 * 
 * <li>
 * All fields are already private; they are further qualified as final.
 * 
 * <li>
 * The class itself is declared final.
 * 
 * <li>
 * Only one field refers to an object, and that object is itself immutable.
 * Therefore, no safeguards against changing the state of "contained" mutable
 * objects are necessary.
 * </ol>
 */
final public class ImmutableRGB { // added final to class

    // Values must be between 0 and 255.
    final private int red; // added final to methods.
    final private int green;
    final private int blue;
    final private String name;

    private void check(int red,
            int green,
            int blue) {
        if (red < 0 || red > 255
                || green < 0 || green > 255
                || blue < 0 || blue > 255) {
            throw new IllegalArgumentException();
        }
    }

    public ImmutableRGB(int red, int green, int blue, String name) {
        check(red, green, blue);

        this.red = red;
        this.green = green;
        this.blue = blue;
        this.name = name;
    }

    // removed setter method

    // removed synchronized keyword from methods below.
    public int getRGB() {
        return ((red << 16) | (green << 8) | blue);
    }

    public String getName() {
        return name;
    }

    public ImmutableRGB invert() {
        // create a new object instead of modifying the existing one.
        return new ImmutableRGB(255 - red,
                255 - green,
                255 - blue,
                "Inverse of " + name);
    }
}
