package com.yusuferkamozyer.graduation.backend

import android.graphics.drawable.Drawable

class ApplicationInfoWrapper {
    private var applicationName: String
    private var applicationIcon: Drawable

    public constructor(applicationName: String, applicationIcon: Drawable) {
        this.applicationIcon = applicationIcon
        this.applicationName = applicationName
    }

    public fun getApplicationName() : String {
        return this.applicationName
    }
    public fun getApplicationIcon() : Drawable {
        return this.applicationIcon
    }
    public fun setApplicationName(applicationName: String) {
        this.applicationName = applicationName
    }
    public fun setApplicationIcon(applicationIcon: Drawable) {
        this.applicationIcon = applicationIcon
    }

}