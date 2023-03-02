package com.tmmarv.onlinecourseapp.models

class CourseModel(var title: String?, var subtitle: String?, var amount: String?) {
    constructor() : this(null, null, null) {
    }
}