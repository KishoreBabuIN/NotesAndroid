package com.kishorebabu.android.notes

import android.app.Application
import com.kishorebabu.android.notes.module.dbModule
import com.kishorebabu.android.notes.module.repositoryModule
import com.kishorebabu.android.notes.module.useCaseModule
import com.kishorebabu.android.notes.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class NotesApp : Application() {
  override fun onCreate() {
    super.onCreate()
    Timber.plant(Timber.DebugTree())
    startKoin {
      androidLogger()
      androidContext(this@NotesApp)
      modules(
          listOf(
              dbModule, viewModelModule, useCaseModule, repositoryModule
          )
      )
    }
  }
}