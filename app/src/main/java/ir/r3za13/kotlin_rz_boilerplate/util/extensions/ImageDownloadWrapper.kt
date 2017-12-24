package ir.r3za13.kotlin_rz_boilerplate.util.extensions

import android.widget.ImageView
import com.bumptech.glide.DrawableTypeRequest
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.drawable.GlideDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target

import java.lang.Exception

/**
 * @author vahab ghadiri
 */


/**
 * load image by url with picasso or glide downloaders
 * @param T one of downloaders , PicassoDownloader or GlideDownloader
 * @param downloader all settings and callbacks you need. such as size and caching modes
 */
inline fun <reified T : Downloader> ImageView.loadImage(url: String?,noinline downloader: T.() -> Unit={}) {

    val instance = T::class.java.newInstance()
    instance.downloader()
    if(url!=null)
        instance.doIT(this, url)
    else
        instance.doIT(this, 0)
}

inline fun <reified T : Downloader> ImageView.loadImage(id: Int,noinline downloader: T.() -> Unit={}) {

    val instance = T::class.java.newInstance()
    instance.downloader()
    instance.doIT(this, id)
}

interface Downloader {
    //    fun doIT(imageView: ImageView, url: String)
    fun doIT(imageView: ImageView, url: Any)
//    fun doIT(imageView: ImageView, id: Int)
}
//
//class PicassoDownloader : Downloader {
//    var errorDrawable: Int = 0
//    var placeHolderDrawable: Int = 0
//    var caching = true
//    var size: Int = 0
//    private var onSuccessfun: () -> Unit = {}
//    private var onFailedfun: () -> Unit = {}
//    private var reqFunction: RequestCreator.() -> Unit = {}
//
//    fun options(f: RequestCreator.() -> Unit) {
//        reqFunction = f
//    }
//
//    fun onLoadFail(f: () -> Unit) {
//        onFailedfun = f
//    }
//
//    fun onLoadSuccess(f: () -> Unit) {
//        onSuccessfun = f
//    }
//
//    override fun doIT(imageView: ImageView, url: String) {
//
//        val picassoReq: RequestCreator = Picasso.with(imageView.context).load(url)
//
//        if (placeHolderDrawable > 0) {
//            picassoReq.placeholder(placeHolderDrawable)
//        }
//        if (errorDrawable > 0) {
//            picassoReq.error(errorDrawable)
//        }
//        if (!caching) {
//            picassoReq.networkPolicy(NetworkPolicy.NO_CACHE)
//        }
//        if (size > 0) {
//            picassoReq.resize(size, size)
//        }
//        picassoReq.reqFunction()
//        picassoReq.into(imageView, object : Callback {
//            override fun onSuccess() {
//                onSuccessfun()
//            }
//
//            override fun onError() {
//                onFailedfun()
//            }
//        })
//    }
//}

class GlideDownloader : Downloader {

    var errorDrawable: Int = 0
    var placeHolderDrawable: Int = 0
    var caching = true
    var size: Int = 0
    private var onSuccessfun: () -> Unit = {}
    private var onFailedfun: () -> Unit = {}
    private var reqFunction: DrawableTypeRequest<Any>.() -> Unit = {}

    fun options(f: DrawableTypeRequest<Any>.() -> Unit) {
        reqFunction = f
    }

    fun onLoadFail(f: () -> Unit) {
        onFailedfun = f
    }

    fun onLoadSuccess(f: () -> Unit) {
        onSuccessfun = f
    }

    override fun doIT(imageView: ImageView, url: Any) {

        val glideReq = Glide.with(imageView.context).load(url)

        if (placeHolderDrawable > 0) {
            glideReq.placeholder(placeHolderDrawable)
        }
        if (errorDrawable > 0) {
            glideReq.error(errorDrawable)
        }
        if (size > 0) {
            glideReq.override(size, size)
        }
        if (!caching) {
            glideReq.diskCacheStrategy(DiskCacheStrategy.NONE)
        }
        glideReq.listener(object : RequestListener<Any?, GlideDrawable> {
            override fun onException(e: Exception?, model: Any?, target: Target<GlideDrawable>?, isFirstResource: Boolean): Boolean {
                onFailedfun()
                return false
            }

            override fun onResourceReady(resource: GlideDrawable?, model: Any?, target: Target<GlideDrawable>?,
                                         isFromMemoryCache: Boolean, isFirstResource: Boolean): Boolean {
                onSuccessfun()
                return false
            }

        })
        glideReq.reqFunction()
        glideReq.into(imageView)
    }
}


















