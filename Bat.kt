package navin.sample

class Bat(){
    var currencyPair = "";
        private var last = "";
        private var baseVolume = "";
        private var percentChange = "";

constructor(last:String,currencyPair:String,baseVolume: String, percentChange:String):this(){
    this.last = last
    this.currencyPair = currencyPair
    this.baseVolume = baseVolume
    this.percentChange = percentChange // non default constructor
}

fun getBaseVolume():Double{
    return baseVolume.toDouble()*100
}

fun getLast():Double{
    return last.toDouble()
}

fun getPercentChenge():Double{
    return percentChange.toDouble()

    // get set like java
}

}


