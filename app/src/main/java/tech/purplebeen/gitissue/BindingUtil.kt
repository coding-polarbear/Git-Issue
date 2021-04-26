package tech.purplebeen.gitissue

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImageByUrl(imageView: ImageView, url: String?) {
    url?.let {
        Glide.with(imageView).load(it).into(imageView)
    }
}