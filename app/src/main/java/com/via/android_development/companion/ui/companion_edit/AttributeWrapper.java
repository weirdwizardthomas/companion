package com.via.android_development.companion.ui.companion_edit;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.shawnlin.numberpicker.NumberPicker;
import com.via.android_development.companion.R;
import com.via.android_development.companion.persistence.local.Companion;
import com.via.android_development.companion.utility.StatCalculator;

public class AttributeWrapper {
    private final TextView label;
    private final ImageButton minusButton;
    private final NumberPicker picker;
    private final ImageButton plusButton;
    private final TextView modifier;

    @SuppressLint("WrongConstant")
    public AttributeWrapper(View parent, String labelText) {
        label = parent.findViewById(R.id.attributeLabel);
        minusButton = parent.findViewById(R.id.attributeMinus);
        picker = parent.findViewById(R.id.attributeSpinner);
        plusButton = parent.findViewById(R.id.attributePlus);
        modifier = parent.findViewById(R.id.attributeModifier);

        label.setText(labelText);

        picker.setMinValue(Companion.MIN_ATTRIBUTE_LEVEL);
        picker.setMaxValue(Companion.MAX_ATTRIBUTE_LEVEL);
        picker.setValue(Companion.DEFAULT_ATTRIBUTE_VALUE);
        picker.setOrientation(NumberPicker.HORIZONTAL);
        picker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                updateModifier(newVal);
            }
        });

        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newValue = picker.getValue() - 1;
                picker.setValue(newValue);
                updateModifier(newValue);
            }
        });

        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newValue = picker.getValue() + 1;
                picker.setValue(newValue);
                updateModifier(newValue);
            }
        });
    }

    private void updateModifier(int newVal) {
        int modifierValue = StatCalculator.abilityModifier(newVal);
        String newModifierText = (modifierValue >= 0 ? "+" : "") + modifierValue;
        modifier.setText(newModifierText);
    }

    public void setPickerValue(int newValue) {
        this.picker.setValue(newValue);
        updateModifier(newValue);
    }

    public int getValue() {
        return picker.getValue();
    }
}
