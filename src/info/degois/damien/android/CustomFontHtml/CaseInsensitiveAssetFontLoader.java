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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.DataSetObserver;
import android.graphics.Typeface;
import android.sax.StartElementListener;
import android.support.v4.util.LruCache;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

public class CaseInsensitiveAssetFontLoader implements ICustomFontLoader {

	public static final String EXT_OTF = "otf";
	public static final String EXT_TTF = "ttf";
	/** An <code>LruCache</code> for previously loaded typefaces. */
	private static LruCache<String, Typeface> sTypefaceCache = new LruCache<String, Typeface>(
			5);
	private HashMap<String, String> mTypefacenameMapping = new HashMap<String, String>();
	private Context mContext;
	private String mFolder = "";
	private AssetManager mAssetManager;

	public CaseInsensitiveAssetFontLoader(Context context) {
		this(context, "");
	}

	public CaseInsensitiveAssetFontLoader(Context context, String folder) {
		mContext = context;
		mAssetManager = mContext.getApplicationContext().getAssets();
		mFolder = folder;
		if (mFolder == null) {
			mFolder = "";
		}
		mFolder = StringTools.rtrim(mFolder, '/');

		String folderNameForPath = mFolder.length() > 0 ? mFolder + "/" : "";
		String[] files;
		try {
			files = mAssetManager.list(mFolder);
			String lower;
			for (int i = 0; i < files.length; i++) {
				lower = files[i].toLowerCase();
				if (lower.endsWith(EXT_OTF) || lower.endsWith(EXT_TTF)) {
					mTypefacenameMapping.put(lower.substring(
							lower.contains("/") ? lower.lastIndexOf("/") : 0,
							lower.length() - 4), folderNameForPath + files[i]);
				}
			}
		} catch (IOException e) {
			// No directory. no custom typefaces
		}

	}

	@Override
	public Typeface getTypeFace(String typefaceName) {
		String lowerTypefaceName = typefaceName.toLowerCase();
		Typeface typeface = sTypefaceCache.get(lowerTypefaceName);

		if (typeface == null) {
			String fontFile = mTypefacenameMapping.get(lowerTypefaceName);

			try {
				typeface = Typeface.createFromAsset(mAssetManager, fontFile);
				sTypefaceCache.put(lowerTypefaceName, typeface);
			} catch (java.lang.RuntimeException e) {
				return android.graphics.Typeface.DEFAULT;
			}
		}
		return typeface;
	}

}
