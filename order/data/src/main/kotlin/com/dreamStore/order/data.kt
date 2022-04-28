package com.dreamStore.order

import com.dreamStore.base.CommonEntity
import com.dreamStore.member.MemberData
import com.dreamStore.product.ProductData
import com.dreamstore.common.Price
import javax.persistence.*

@Entity
@Table(name = "`ORDER`")
class OrderData (
    @Column(name="TOTAL_PRICE")
    val totalPrice: Int = 0,

    @ManyToOne
    @JoinColumn(name="MEMBER_ID")
    val member: MemberData = MemberData(),

    @OneToMany(fetch = FetchType.LAZY)
    var orderEntryList:MutableList<OrderEntryData> = mutableListOf()
): CommonEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long=0

    fun addOrderEntries(orderEntries : MutableList<OrderEntryData>){
        orderEntryList=orderEntries
        orderEntries.forEach { it.order=this }
    }
}

@Entity
@Table(name = "ORDER_ENTRY")
class OrderEntryData (
    @ManyToOne
    @JoinColumn(name="PRODUCT_ID")
    val product: ProductData,

    @Column(name="PRICE")
    val price: Int,

    @Column(name="QUANTITY")
    val quantity: Int,

    ): CommonEntity(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long=0

    @ManyToOne
    @JoinColumn(name="OREDER_ID")
    var order:OrderData?=null

    fun toEntity(): OrderEntry {
        return OrderEntry(price = Price(price),product = product.toEntity(),quantity = Quantity(quantity))
    }
}