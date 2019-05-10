This project is an example showing how to use libgdx with kotlin on android.

To user your blender model in Android, first create it in Blender. Then:
- Export it in *.FBX format
- Scale it at 0.01 (the Blender unit is 1m and the libgdx unit is 1cm)
- Export it.

Then you need to convert the *.fbx file to a *.g3db file using libgdx converter that you can find there:
https://github.com/libgdx/fbx-conv

using this command: fbx-conv -f <your-file>.fbx

(the -f flag is important to have a good UV map for the texture).

Finally, put the texture file and the *.g3db file in app/src/main/assets/data and load your model like this:

model = modelLoader.loadModel(Gdx.files.getFileHandle("data/dice.g3db", Files.FileType.Internal))
