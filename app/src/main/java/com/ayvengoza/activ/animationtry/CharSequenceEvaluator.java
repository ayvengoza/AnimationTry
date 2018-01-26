package com.ayvengoza.activ.animationtry;

import android.animation.TypeEvaluator;

/**
 * Created by ang on 25.01.18.
 */

public class CharSequenceEvaluator implements TypeEvaluator<CharSequence> {
    @Override
    public CharSequence evaluate(float fraction, CharSequence startValue, CharSequence endValue) {
        final int initialTextLength = startValue == null ? 0 : startValue.length();
        final int finalTextLength = endValue == null ? 0 : endValue.length();

        if(initialTextLength == 0 && finalTextLength == 0){
            return endValue;
        }

        if(fraction <= 0){
            return startValue;
        }

        if(fraction >= 1f){
            return endValue;
        }

        final float maxLength = Math.max(initialTextLength, finalTextLength);
        final int charactersChanged = (int) (maxLength*fraction);

        if(charactersChanged == 0){
            return startValue;
        }

        if(finalTextLength < charactersChanged){
            if(finalTextLength == 0){
                return startValue.subSequence(charactersChanged, initialTextLength);
            }

            if(initialTextLength <= charactersChanged){
                return endValue.subSequence(0, charactersChanged);
            }

            return endValue + startValue.subSequence(charactersChanged, initialTextLength).toString();
        }

        if(initialTextLength <= charactersChanged){
            return endValue.subSequence(0, charactersChanged);
        }

        CharSequence cs = endValue.subSequence(0,charactersChanged).toString() +
                startValue.subSequence(charactersChanged, initialTextLength);

        return cs;

    }
}
