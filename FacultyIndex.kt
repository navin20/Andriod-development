package com.shota.androidquiz.aumap.facultyManager

import com.shota.androidquiz.aumap.R

class FacultyIndex(){
    private var fIndex = ArrayList<Faculty>();
    val size:Int
        get() = this.fIndex.count()
    init {
        this.fIndex = ArrayList<Faculty>();
    }

    fun load(){
        this.fIndex.add(Faculty("Vincent Mary School of Science and Technology",
                            "VMS",
                                R.drawable.vms_logo,
                            13.613239, 100.835531,
                            false))

        this.fIndex.add( Faculty("Albert Laurence School of Communication Arts",
                            "CA",
                                R.drawable.ca_logo,
                            13.612227, 100.835039,
                            false))
        this.fIndex.add( Faculty("Montfort Del Rosario School of Architecture and Design",
                    "AR",
                        R.drawable.ar_logo,
                    13.612125, 100.835519,
                    false))

        this.fIndex.add( Faculty("School of Law",
                "LAW",
                R.drawable.law_logo,
                13.611869, 100.937477,
                false))
        this.fIndex.add( Faculty("School of Music",
                "MS",
                R.drawable.ms_logo,
                13.612264, 100.837577,
                false))
        this.fIndex.add( Faculty("Martin De Tours School of Management and Ecomomics",
                "MSME",
                R.drawable.msme_logo,
                13.612958, 100.836499,
                false))
        this.fIndex.add( Faculty("Faculty of Nursing Science",
                "NS",
                R.drawable.ns_logo,
                13.612219, 100.838038,
                false))
        this.fIndex.add( Faculty("Theodore Maria School of Arts",
                "ARTS",
                R.drawable.arts_logo,
                13.611520, 100.837211,
                false))
    }

    fun getFaculty(index:Int):Faculty?{
        var result = Faculty();
        if(0<=index&&index<this.fIndex.count()) {
            result = this.fIndex.get(index);
        }
        return result;
    }
}