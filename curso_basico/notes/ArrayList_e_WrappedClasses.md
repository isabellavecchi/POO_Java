ARRAYLIST

{
    * Similar to an Array, but more flexible and can be resized

    * Only objects can be stored in an ArrayList

    * An ArrayList implements the list interface

    * You MUST import (java.util.ArrayList and java.util.list) or java.util.*
}

{
    * the add() method with ONE parameter, ALWAYS returns true

    * the add() method with TWO parameters doesn't return anything

    * we can sotre primitive types in an ArrayList by placing them inside a "wrapper class"
}

{
    Traversing:
    
    * We can use either a for loop or a for-eache (enhanced for) loop to traverse an
    ArrayList

    * To calculate the size of an ArrayList we use the size() method

    * If elements are added or deleted during a traversal, objects may be outputted
    twice or missed entirely
}


WRAPPED CLASSES

{
    * We can encapsulate a primitive type in a wrapper cass, so it can be stored in an
    ArrayList

    * For each primitive type, there is a corresponding Object type
        - int -> Integer
        - double -> Double
        - boolean -> Boolean
    
    * Java version 5 or later supports autoboxing, so it can covert between primitive 
    and object type

    * Wrapped classes are immutable (like Strings)
        - mas podemos fazer a reference variable apontar para um novo obj no heap
}