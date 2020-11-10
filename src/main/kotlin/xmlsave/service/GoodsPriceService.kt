package xmlsave.service

import com.fasterxml.jackson.dataformat.xml.XmlMapper
import com.google.common.collect.Lists
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import xmlsave.Repository.BufferRepository
import xmlsave.Repository.GoodsPriceRepository
import xmlsave.dto.BufferDTO
import xmlsave.entity.GoodsPrice
import xmlsave.entity.SaveBuffer
import java.time.format.DateTimeFormatter
import java.util.*

@Service
class GoodsPriceService(
        private val bufferRepository: BufferRepository,
        private val goodsPriceRepository: GoodsPriceRepository
) {

    private val xmlMapper: XmlMapper = XmlMapper()

    fun saveAll(prices: List<GoodsPrice>) {
        goodsPriceRepository.saveAll(prices)
    }


    @Transactional
    fun saveViaBuffer(prices: List<GoodsPrice>) {
        val uuid = UUID.randomUUID().toString()
        val values = prices.map {
            BufferDTO(
                    goodsId = it.goodsId,
                    priceDate = it.priceDate.format(DateTimeFormatter.ISO_DATE),
                    price = it.price.stripTrailingZeros().toPlainString()
            )
        }

        bufferRepository.save(
                    SaveBuffer(
                            id = 0L,
                            uuid = uuid,
                            xmlData = xmlMapper.writeValueAsString(values)
                    )
            )

        goodsPriceRepository.saveViaBuffer(uuid)
        bufferRepository.deleteAllByUuid(uuid)

    }

}