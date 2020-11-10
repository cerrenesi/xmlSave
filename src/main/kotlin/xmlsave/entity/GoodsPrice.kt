package xmlsave.entity

import java.math.BigDecimal
import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(schema = BaseEntity.schemaName, name = GoodsPrice.tableName)
data class GoodsPrice(

        @Id
        @Column(name = "GoodsPriceId")
        @GeneratedValue(strategy =  GenerationType.IDENTITY)
        override val id: Long,

        @Column(name = "GoodsId")
        val goodsId: Long,

        @Column(name = "Price")
        val price: BigDecimal,

        @Column(name = "PriceDate")
        val priceDate: LocalDate


): BaseEntity(id) {


        companion object {
                const val tableName: String = "GoodsPrice"
        }

}