/*
 * Copyright (C) 2015 Damien Degois
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package info.degois.damien.android.CustomFontHtml;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;

public class CustomTypefaceSpan extends MetricAffectingSpan {
	private Typeface mTypeface = android.graphics.Typeface.DEFAULT;
	private boolean mHonorStyle = true;

	/**
	 * Load the {@link Typeface} and apply to a spannable.
	 */
	public CustomTypefaceSpan(String typefaceName, ICustomFontLoader fontLoader) {
		if ("monospace".equals(typefaceName)) {
			mTypeface = Typeface.create(typefaceName, 0);
		} else if ("serif".equals(typefaceName)) {
			mTypeface = Typeface.create(typefaceName, 0);
		} else if ("sans-serif".equals(typefaceName)) {
			mTypeface = Typeface.create(typefaceName, 0);
		} else if (fontLoader != null) {
			mTypeface = fontLoader.getTypeFace(typefaceName);
		}
	}

	public CustomTypefaceSpan setHonorStyle(boolean honorStyle) {
		mHonorStyle = honorStyle;
		return this;
	}

	@Override
	public void updateMeasureState(TextPaint tp) {
		apply(tp);
	}

	@Override
	public void updateDrawState(TextPaint tp) {
		apply(tp);

	}

	private void apply(Paint paint) {
		if (mHonorStyle) {
			int oldStyle;
			Typeface old = paint.getTypeface();
			if (old == null) {
				oldStyle = 0;
			} else {
				oldStyle = old.getStyle();
			}

			Typeface tf = Typeface.create(mTypeface, oldStyle);
			int fake = oldStyle & ~tf.getStyle();

			if ((fake & Typeface.BOLD) != 0) {
				paint.setFakeBoldText(true);
			}

			if ((fake & Typeface.ITALIC) != 0) {
				paint.setTextSkewX(-0.25f);
			}
		}
		paint.setTypeface(mTypeface);
		// Note: This flag is required for proper typeface rendering
		paint.setFlags(paint.getFlags() | Paint.SUBPIXEL_TEXT_FLAG);
	}

}