package com.example.aayush.aburrido;

/**
 * Created by Ayushgarg on 12-Jun-16.
 */
public class Sentiment {
    public class Aggregate{
        double score;

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
    Aggregate aggregate;

    public Aggregate getAggregate() {
        return aggregate;
    }

    public void setAggregate(Aggregate aggregate) {
        this.aggregate = aggregate;
    }
}
