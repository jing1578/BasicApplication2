package org.jing1578.basicapplication.packing;

import android.view.View;

import java.io.Serializable;

/**
 * Created by jing1578 on 2017/9/22 10:16.
 */

public class DialogParams implements Serializable {
    public int mIconId = 0;
    public int themeResId;
    public CharSequence title;
    public CharSequence message;
    public CharSequence positiveText;
    public CharSequence neutralText;
    public CharSequence negativeText;
    public CharSequence[] items;
    public boolean[] checkedItems;
    public boolean isMultiChoice;
    public boolean isSingleChoice;
    public int checkedItem;
    public int layoutResId;
    public View view;

    private DialogParams(DialogParamBuilder dialogParamBuilder) {
        mIconId = dialogParamBuilder.mIconId;
        themeResId = dialogParamBuilder.themeResId;
        title = dialogParamBuilder.title;
        message = dialogParamBuilder.message;
        positiveText = dialogParamBuilder.positiveText;
        neutralText = dialogParamBuilder.neutralText;
        negativeText = dialogParamBuilder.negativeText;
        items = dialogParamBuilder.items;
        checkedItems = dialogParamBuilder.checkedItems;
        isMultiChoice = dialogParamBuilder.isMultiChoice;
        isSingleChoice = dialogParamBuilder.isSingleChoice;
        checkedItem = dialogParamBuilder.checkedItem;
        layoutResId =dialogParamBuilder.layoutResId;
        view = dialogParamBuilder.view;
    }

     static class DialogParamBuilder implements Serializable {
        private int mIconId = 0;
        private int themeResId;
        private CharSequence title;
        private CharSequence message;
        private CharSequence positiveText;
        private CharSequence neutralText;
        private CharSequence negativeText;
        private CharSequence[] items;
        private boolean[] checkedItems;
        private boolean isMultiChoice;
        private boolean isSingleChoice;
        private int checkedItem;
         private View view;
         private int layoutResId;

        public DialogParamBuilder mIconId(int mIconId) {
            this.mIconId = mIconId;
            return this;
        }

        public DialogParamBuilder themeResId(int themeResId) {
            this.themeResId = themeResId;
            return this;
        }

        public DialogParamBuilder title(CharSequence title) {
            this.title = title;
            return this;
        }

        public DialogParamBuilder message(CharSequence message) {
            this.message = message;
            return this;
        }

        public DialogParamBuilder positiveText(CharSequence positiveText) {
            this.positiveText = positiveText;
            return this;
        }

        public DialogParamBuilder neutralText(CharSequence neutralText) {
            this.neutralText = neutralText;
            return this;
        }

        public DialogParamBuilder negativeText(CharSequence negativeText) {
            this.negativeText = negativeText;
            return this;
        }

        public DialogParamBuilder items(CharSequence[] items) {
            this.items = items;
            return this;
        }

        public DialogParamBuilder setCheckedItems(boolean[] checkedItems) {
            this.checkedItems = checkedItems;
            return this;
        }

        public void setMultiChoice(boolean multiChoice) {
            isMultiChoice = multiChoice;
        }

        public DialogParamBuilder singleChoice(boolean singleChoice) {
            isSingleChoice = singleChoice;
            return this;
        }

        public DialogParamBuilder checkedItem(int checkedItem) {
            this.checkedItem = checkedItem;
            return this;
        }

         public DialogParamBuilder view(View view) {
             this.view = view;
             return this;
         }

         public DialogParamBuilder layoutResId(int layoutResId) {
             this.layoutResId = layoutResId;
             return this;
         }

         public DialogParams build() {
            return new DialogParams(this);
        }
    }

}
