package com.example.kodecokmp.di

import com.example.kodecokmp.TimeZoneHelper
import com.example.kodecokmp.TimeZoneHelperImpl
import org.koin.dsl.module

actual fun timezoneModule() = module {
    single<TimeZoneHelper> { TimeZoneHelperImpl() }
}