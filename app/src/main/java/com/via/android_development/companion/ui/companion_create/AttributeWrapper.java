package com.via.android_development.companion.ui.companion_create;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.shawnlin.numberpicker.NumberPicker;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.utility.StatCalculator;

class AttributeWrapper {

    private final NumberPicker picker;
    private final TextView label;
    private final TextView modifier;

    @SuppressLint("WrongConstant")
    public AttributeWrapper(View parent, String displayedLabel) {
        picker = parent.findViewById(R.id.picker);
        label = parent.findViewById(R.id.levelLabel);
        modifier = parent.findViewById(R.id.modifier);

        label.setText(displayedLabel);

        picker.setMinValue(Companion.MIN_ATTRIBUTE_LEVEL);
        picker.setMaxValue(Companion.MAX_ATTRIBUTE_LEVEL);
        picker.setOrientation(NumberPicker.HORIZONTAL);

        reset();

        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                updateModifier(newVal);
            }
        });
    }

    private void updateModifier(int newVal) {
        int modifierValue = StatCalculator.abilityModifier(newVal);
        String newModifierText = (modifierValue >= 0 ? "+" : "") + modifierValue;
        modifier.setText(newModifierText);
    }

    public int getPickerValue(){
        return picker.getValue();
    }

    public void reset() {
        picker.setValue(Companion.DEFAULT_ATTRIBUTE_VALUE);
    }

    public void setAttributeValue(int newValue) {
        picker.setValue(newValue);
    }
}
