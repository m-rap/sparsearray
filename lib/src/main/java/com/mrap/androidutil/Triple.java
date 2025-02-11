package com.mrap.androidutil;

import android.util.Pair;

public class Triple<A, B, C> extends Pair<A, B> {
    public C third;

    /**
     * Constructor for a Triple.
     *
     * @param first  the first object in the Triple
     * @param second the second object in the Triple
     * @param second the third object in the Triple
     */
    public Triple(A first, B second, C third) {
        super(first, second);
    }
}
