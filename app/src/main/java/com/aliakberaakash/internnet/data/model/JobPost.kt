package com.aliakberaakash.internnet.data.model

import com.google.firebase.Timestamp

data class JobPost(
    var id : String = "",
    var createdAt : Timestamp = Timestamp.now(),
    var jobTitle : String = "",
    var jobType : String = "",
    var jobDescription : String = "",
    var jobRequirements : String = "",
    var jobBenefits : String = "",
    var startingSalary : String = "",
    var endingSalary : String = "",
    var deadline : String = "",
)