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

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v4.util.LruCache;

public class SimpleAssetFontLoader implements ICustomFontLoader {

	public static final String EXT_OTF = "otf";
	public static final String EXT_TTF = "ttf";
	/** An <code>LruCache</code> for previously loaded typefaces. */
	private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(
			5);
	private Context mContext;
	private String mExtension = EXT_OTF;
	private String mFolder = "";
	private String mFolderForPath = "";
	private AssetManager mAssetManager;

	public SimpleAssetFontLoader(Context context) {
		mContext = context;
		mAssetManager = mContext.getApplicationContext().getAssets();

	}

	public SimpleAssetFontLoader setExtension(String extension) {
		mExtension = extension;
		return this;
	}

	public SimpleAssetFontLoader setFolder(String folder) {
		mFolder = folder;
		if (mFolder == null) {
			mFolder = "";
		}

		mFolder = StringTools.rtrim(mFolder, '/');
		if (mFolder.length() > 0) {
			mFolderForPath = mFolder + "/";
		} else {
			mFolderForPath = mFolder;
		}
		return this;
	}

	@Override
	public Typeface getTypeFace(String typefaceName) {
		Typeface typeface = sTypefaceCache.get(typefaceName);

		if (typeface == null) {
			String fontFile = mFolderForPath + typefaceName + "." + mExtension;

			try {
				typeface = Typeface.createFromAsset(mAssetManager, fontFile);
				sTypefaceCache.put(typefaceName, typeface);
			} catch (java.lang.RuntimeException e) {
				return android.graphics.Typeface.DEFAULT;
			}
		}
		return typeface;
	}
}
