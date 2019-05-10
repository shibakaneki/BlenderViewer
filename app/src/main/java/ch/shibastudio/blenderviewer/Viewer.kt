package ch.shibastudio.blenderviewer

import com.badlogic.gdx.ApplicationListener
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.PerspectiveCamera
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder

import com.badlogic.gdx.graphics.VertexAttributes.Usage
import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.graphics.g3d.*
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController


class Viewer : ApplicationListener {

    var cameraController : CameraInputController? = null
    val environment = Environment()
    lateinit var camera : PerspectiveCamera
    var model : Model? = null
    var modelBatch : ModelBatch? = null
    lateinit var modelInstance : ModelInstance

    override fun pause() { }

    override fun resume() { }

    override fun resize(width: Int, height: Int) { }

    override fun create() {
        // -- Environment --
        environment.set(ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f))
        environment.add(DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.2f))

        // -- Init the camera --
        camera = PerspectiveCamera(67F, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
        camera.position.set(10F, 10F, 10F)
        camera.lookAt(0F, 0F, 0F)
        camera.near = 1F
        camera.far = 300F
        camera.update()

        cameraController = CameraInputController(camera)
        Gdx.input.inputProcessor = cameraController

        // -- Create the model --
        model = ModelBuilder().createBox(
            5F,
            5F,
            5F,
            Material(ColorAttribute.createDiffuse(Color.GREEN)),
            Usage.Position.toLong() or Usage.Normal.toLong()
        )

        modelInstance = ModelInstance(model)

        modelBatch = ModelBatch()
    }

    override fun dispose() {
        modelBatch!!.dispose()
        model!!.dispose()
    }

    override fun render() {
        Gdx.gl.glViewport(0, 0, Gdx.graphics.width, Gdx.graphics.height)
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT or GL20.GL_DEPTH_BUFFER_BIT)

        cameraController!!.update()

        modelBatch!!.begin(camera)
        modelBatch!!.render(modelInstance, environment)
        modelBatch!!.end()
    }

}