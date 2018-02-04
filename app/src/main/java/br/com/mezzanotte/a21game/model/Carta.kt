package br.com.mezzanotte.a21game.model

import android.os.Parcel
import android.os.Parcelable


data class Carta(var resourceId: Int, var pontuacao: Int) : Parcelable {

    constructor(parcel: Parcel) : this(
            parcel.readInt(),
            parcel.readInt()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(resourceId)
        parcel.writeInt(pontuacao)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Carta> {
        override fun createFromParcel(parcel: Parcel): Carta {
            return Carta(parcel)
        }

        override fun newArray(size: Int): Array<Carta?> {
            return arrayOfNulls(size)
        }
    }
}
