package ir.shamspour.presentation.util

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.navigation.NavController
import androidx.navigation.NavDirections

fun <T> LifecycleOwner.observe(liveData: LiveData<T>, action: (t: T) -> Unit) {
    liveData.observe(this, Observer { it?.let { t -> action(t) } })
}

 fun <T : ViewModel> Fragment.obtainViewModel(owner: ViewModelStoreOwner,
                                            viewModelClass: Class<T>,
                                            viewModelFactory: ViewModelProvider.Factory
) =
    ViewModelProvider(owner, viewModelFactory).get(viewModelClass)

