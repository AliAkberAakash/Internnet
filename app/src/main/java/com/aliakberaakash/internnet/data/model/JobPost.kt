package com.aliakberaakash.internnet.data.model

data class JobPost(
        var id : String = "",
        var createdAt : String = "",
        var jobTitle : String = "",
        var jobType : String = "",
        var jobDescription : String = "",
        var jobRequirements : String = "",
        var jobBenefits : String = "",
        var startingSalary : String = "",
        var endingSalary : String = "",
        var deadline : String = "",
)