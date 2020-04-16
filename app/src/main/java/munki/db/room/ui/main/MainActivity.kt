package munki.db.room.ui.main

import android.os.Bundle
import androidx.lifecycle.Observer
import munki.db.room.BR
import munki.db.room.R
import munki.db.room.data.dao.Word
import munki.db.room.databinding.ActivityMainBinding
import munki.db.room.ui.base.BaseActivity
import javax.inject.Inject

/**
 * MainActivity
 * @author 나비이쁜이
 * @since 2020.04.16
 */
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() , MainNavigator {

    /**
     * this.binding
     */
    private var mBinding : ActivityMainBinding ? = null

    /**
     * this.viewmodel
     */
    @Inject internal lateinit var mViewModel: MainViewModel

    /************************************************************************************************************************************************/

    /**
     * BR variable
     */
    override
    val bindingVariable : Int get() = BR.main

    /**
     * Resoucres Layout
     */
    override
    val layoutId : Int get() = R.layout.activity_main

    /**
     * ViewModel - ViewModel에 인터페이스를 전달하는 Navigation 설정
     */
    override
    val viewModel: MainViewModel get() {
        mViewModel.setNavigation(this)
        return mViewModel
    }

    /************************************************************************************************************************************************/

    /**
     * onCreate
     */
    override
    fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Databinding & Navigation Binding
        mBinding = viewDataBinding

        // observer init
        init()
    }

    /**
     * Observer & Init
     */
    override fun init() {
        super.init()

        // SetAdapter
        val adapter = MainAdapter(this)
        mBinding!!.searchRecyclerview.adapter = adapter

        // LiveData
        mViewModel.getAllWords()!!.observe(this, Observer { word : List<Word?>? -> adapter.setWords(word) })
    }

    /************************************************************************************************************************************************/

    /**
     * getString
     */
    override fun getString(): String {
        return if (mBinding!!.etSearch.text != null) mBinding!!.etSearch.text.toString() else "null"
    }
}

