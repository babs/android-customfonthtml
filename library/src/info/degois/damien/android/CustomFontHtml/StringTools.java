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

public class StringTools {
	public static String ltrim(String s) {
		int i = 0;
		while (i < s.length() && Character.isWhitespace(s.charAt(i))) {
			i++;
		}
		return s.substring(i);
	}

	public static String rtrim(String s) {
		int i = s.length() - 1;
		while (i >= 0 && Character.isWhitespace(s.charAt(i))) {
			i--;
		}
		return s.substring(0, i + 1);
	}

	public static String ltrim(String s, char c) {
		int i = 0;
		while (i < s.length() && c == s.charAt(i)) {
			i++;
		}
		return s.substring(i);
	}

	public static String rtrim(String s, char c) {
		int i = s.length() - 1;
		while (i >= 0 && c == s.charAt(i)) {
			i--;
		}
		return s.substring(0, i + 1);
	}
}
