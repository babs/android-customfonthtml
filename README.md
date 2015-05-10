# CustomFontHtml

Custom font html is an Android library to handle custom font, via custom loaders, with a Html.fromHtml like function.

#### CustomHtml.fromHtml(String string, ICustomFontLoader fontLoader)

##### Example
```java

CaseInsensitiveAssetFontLoader fontLoader = new CaseInsensitiveAssetFontLoader(
    getActivity().getApplicationContext());

maintv.setText(CustomHtml
    .fromHtml(
        "Example using font awesome:<br/>"
            + "<font face='fontawesome'>&#xf17b;</font> Android<br/>"
            + "<font face='fontawesome'>&#xf024; <font color='red'>&#xf024;</font></font> flag and red flag<br/>"
            + "<font face='fontawesome'>&#xf1b9;</font> car<br/>"
            + "<font face='fontawesome'>&#xf07c;</font> folder<br/>"
            + "<font face='libertine'>LinLibertine example</font>",
        fontLoader));

// All caps mess rendering.
disableAllCaps(mainbuton);
mainbuton.setText(CustomHtml.fromHtml(
    "<font face='fontawesome'>&#xf17b;</font> Android button",
    fontLoader));
    
@TargetApi(14)
public void disableAllCaps(TextView v) {
    v.setTransformationMethod(null);
    if (Build.VERSION.SDK_INT >= 14) {
        v.setAllCaps(false);
    }
}   
```
##### Result

![](misc/example.png?raw=true)

####

## Font loaders

#### ICustomFontLoader

All font loaders implements ```ICustomFontLoader```.


#### SimpleAssetFontLoader(Context context)

```SimpleAssetFontLoader``` loads font from asset using face attribute of the font tag and a given extension in a given directory ("otf" and "assets" by default)

Search is case sensitive so to match FontAwesome.otf, tag MUST had face attribute strictly equal to FontAwesome or you can use ```CaseInsensitiveAssetFontLoader```

```setFolder(String folder)```

Set the folder to search in

```setExtension(String extension)```

Set the extension to look for. Supported by Android are EXT_OTF and EXT_TTF (otf and ttf respectively)

#### CaseInsensitiveAssetFontLoader(Context context, String folder)

```CaseInsensitiveAssetFontLoader``` works like ```SimpleAssetFontLoader``` but in a case insensitive manner. That's why the folder has to be given at the creation to list and normalize file names for further use.
This loader loads both otf and ttf and in case of duplication, last seen is kept.

#### CaseInsensitiveAssetFontLoader(Context context)

Same as ```CaseInsensitiveAssetFontLoader(Context context, "")```.
This assume that fonts are in the root of the ```assets``` folder


#### Notes
Both implementation caches the font once loaded in an LRU to spare resources.
When font is unavailable, it fallbacks to system default.


## License

    Copyright (c) 2015 Damien Degois

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

