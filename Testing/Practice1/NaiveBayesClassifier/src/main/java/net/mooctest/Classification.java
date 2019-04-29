package net.mooctest;

import java.util.Collection;

public class Classification<T, K> {
    
    private Collection<T> featureset;

    private K category;

    private float probability;

    public Classification(Collection<T> featureset, K category) {
        this(featureset, category, 1.0f);
    }

    public Classification(Collection<T> featureset, K category,
            float probability) {
        this.featureset = featureset;
        this.category = category;
        this.probability = probability;
    }

    public Collection<T> getFeatureset() {
        return featureset;
    }

    public float getProbability() {
        return this.probability;
    }

    public K getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Classification [category=" + this.category
                + ", probability=" + this.probability
                + ", featureset=" + this.featureset
                + "]";
    }

}
