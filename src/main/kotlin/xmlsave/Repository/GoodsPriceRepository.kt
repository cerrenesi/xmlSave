package xmlsave.Repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import xmlsave.entity.GoodsPrice

@Repository
interface GoodsPriceRepository: JpaRepository<GoodsPrice, Long> {

    @Modifying
    @Query("""
    insert into dbo.GoodsPrice(
	GoodsId,
	Price,
	PriceDate
	)
	select res.*
	from dbo.SaveBuffer buffer
		cross apply(select temp.n.value('goodsId[1]', 'int') as GoodsId
			, temp.n.value('price[1]', 'numeric(18, 2)') as Price
			, temp.n.value('priceDate[1]', 'nvarchar(10)') as PriceDate
			from buffer.xmlData.nodes('/ArrayList/item') temp(n)) res
			where buffer.UUID = :uuid
    """, nativeQuery = true)
    fun saveViaBuffer(uuid: String)

}